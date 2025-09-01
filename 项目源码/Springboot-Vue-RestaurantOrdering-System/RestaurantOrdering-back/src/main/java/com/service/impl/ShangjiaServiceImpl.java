
package com.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ShangjiaDao;
import com.dao.UserDao;
import com.entity.ShangjiaEntity;
import com.service.ShangjiaService;
import com.service.UserService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 */
@Service("shangjiaService")
public class ShangjiaServiceImpl extends ServiceImpl<ShangjiaDao, ShangjiaEntity> implements ShangjiaService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<ShangjiaEntity> page = this.selectPage(
                new Query<ShangjiaEntity>(params).getPage(),
                new EntityWrapper<ShangjiaEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<ShangjiaEntity> selectListView(Wrapper<ShangjiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			Wrapper<ShangjiaEntity> wrapper) {
		 Page<ShangjiaEntity> page =new Query<ShangjiaEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}
}
