
package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ShangjiaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 */
public interface ShangjiaDao extends BaseMapper<ShangjiaEntity> {
	
	List<ShangjiaEntity> selectListView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);

	List<ShangjiaEntity> selectListView(Pagination page,@Param("ew") Wrapper<ShangjiaEntity> wrapper);
	
}
