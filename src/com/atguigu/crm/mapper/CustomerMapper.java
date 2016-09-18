package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;

public interface CustomerMapper {
	List<String> getAllCredit();
	List<Customer> getListInPage(Map<String,Object> map);
	int getTotalCount(Map<String, Object> rightParams);
	void save(Customer customer);
	Customer getById(Long id);
	void update(Customer customer);
	void delete(Long id);
	List<Customer> getAllCust();

}
