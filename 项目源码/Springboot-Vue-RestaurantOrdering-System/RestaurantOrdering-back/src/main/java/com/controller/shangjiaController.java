
package com.controller;


import com.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.ShangjiaEntity;
import com.service.ShangjiaService;
import com.service.TokenService;
import com.utils.MPUtil;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * 登录相关
 */
@RequestMapping("shangjia")
@RestController
public class shangjiaController {
	
	@Autowired
	private ShangjiaService shangjiaService;
	
	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@PostMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ShangjiaEntity user = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		if(!user.getShenhe().equals("通过")) {
			return R.error("未审核通过");
		}
		String token = tokenService.generateToken(user.getId(),username, "shangjia", user.getRole());
		return R.ok().put("token", token);
	}
	
	/**
	 * 注册
	 */
	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody ShangjiaEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("用户已存在");
    	}
		user.setRole("商家");
		shangjiaService.insert(user);
        return R.ok();
    }

	/**
	 * 退出
	 */
	@GetMapping(value = "logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ShangjiaEntity user = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
    	user.setPassword("123456");
		shangjiaService.update(user,null);
        return R.ok("密码已重置为：123456");
    }
	
	/**
     * 列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShangjiaEntity user){
        EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
    	PageUtils page = shangjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/list")
    public R list( ShangjiaEntity user){
       	EntityWrapper<ShangjiaEntity> ew = new EntityWrapper<ShangjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user")); 
        return R.ok().put("data", shangjiaService.selectListView(ew));
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        ShangjiaEntity user = shangjiaService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ShangjiaEntity user = shangjiaService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody ShangjiaEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("用户已存在");
    	}
        shangjiaService.insert(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShangjiaEntity user){
//        ValidatorUtils.validateEntity(user);
    	ShangjiaEntity u = shangjiaService.selectOne(new EntityWrapper<ShangjiaEntity>().eq("username", user.getUsername()));
    	if(u!=null && !u.getId().equals(user.getId()) && u.getUsername().equals(user.getUsername())) {
    		return R.error("用户名已存在。");
    	}
        shangjiaService.updateById(user);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shangjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
