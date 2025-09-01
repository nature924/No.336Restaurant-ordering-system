
package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.ShangjiaEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 */
public interface ShangjiaService extends IService<ShangjiaEntity> {
 	PageUtils queryPage(Map<String, Object> params);
    
   	List<ShangjiaEntity> selectListView(Wrapper<ShangjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShangjiaEntity> wrapper);
	   	
}
