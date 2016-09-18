package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.mapper.AuthMapper;

@Service
public class AuthorityService {
	@Autowired
	private AuthMapper mapper;
	
	@Transactional(readOnly=true)
	public List<Authority> getAll(){
		return mapper.getAll();
	}

	@Transactional(readOnly=true)
	public List<Authority> getAllParents() {
		// TODO Auto-generated method stub
		return mapper.getParentAuthorities();
	}
}
