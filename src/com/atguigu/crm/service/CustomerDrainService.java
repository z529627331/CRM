package com.atguigu.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mapper.CustomerDrainMapper;

@Service
public class CustomerDrainService {
	@Autowired
	private CustomerDrainMapper mapper;
	@Transactional
	void callDrain(){
		mapper.callCheckCustomerDrain();
	}
}
