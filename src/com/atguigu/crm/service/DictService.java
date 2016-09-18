package com.atguigu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.DictMapper;

@Service
public class DictService {
	@Autowired
	private DictMapper mapper;
	@Transactional(readOnly=true)
	public List<Dict> getByType(String str) {
		// TODO Auto-generated method stub
		return mapper.getByType(str);
	}
	
	
	
}
