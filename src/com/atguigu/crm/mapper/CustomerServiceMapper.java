package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.CustomerService;

public interface CustomerServiceMapper {

	void save(CustomerService bean);

	List<CustomerService> getByState();

	int getTotalCount(Map<String, Object> queryMap);

	List<CustomerService> getLimitedList(Map<String, Object> queryMap);

	void allot(CustomerService bean);

	int getundealedCount(Map<String, Object> params);

	List<CustomerService> getUndealed(Map<String, Object> queryMap);

	CustomerService getById(Long id);

	void doDeal(CustomerService bean);

	int getFeedbackCount(Map<String, Object> queryMap);

	List<CustomerService> getFeedBackList(Map<String, Object> queryMap);

	void doFeedback(CustomerService bean);

	int getArchiveCount(Map<String, Object> queryMap);

	List<CustomerService> getArchiveList(Map<String, Object> queryMap);

}
