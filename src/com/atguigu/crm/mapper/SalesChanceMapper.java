package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;

public interface SalesChanceMapper {
	public long getTotalCount(Map<String, Object> queryMap);

	public List<SalesChance> getLimitedList(Map<String, Object> param); 
	
	public void saveChance(SalesChance chance);

	public SalesChance get(@Param("id")Long id);

	public void update(SalesChance chance);

	public void delete(@Param("id")Long id);
}
