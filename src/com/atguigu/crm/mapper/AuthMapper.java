package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Authority;

public interface AuthMapper {
	
	List<Authority> getAll();
	List<Authority> getParentAuthorities();
	
}	
