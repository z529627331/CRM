package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.CustomerDrain;

public interface DrainMapper {

	int getDrainCount(Map<String, Object> rightParams);

	List<CustomerDrain> getDrainListInPage(Map<String, Object> rightParams);

	CustomerDrain getById(Long id);

	void updateDelay(CustomerDrain drain);

	void confirm(CustomerDrain drain);
	
}
