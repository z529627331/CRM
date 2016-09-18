package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Role;
import com.atguigu.crm.mapper.RoleMapper;

@Service
public class RoleService {
	@Autowired
	private RoleMapper mapper;
	
	
	@Transactional(readOnly=true)
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return mapper.getAll();
	}

	@Transactional
	public void save(Role role) {
		// TODO Auto-generated method stub
		mapper.save(role);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
	@Transactional(readOnly=true)
	public Role getById(Long id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}
	@Transactional
	public void update(Role role) {
		mapper.removeRelationShip(role);
		mapper.bacthupdate(role);
		
	}
}
