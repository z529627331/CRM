package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Role;

public interface RoleMapper {

	List<Role> getAll();

	void save(Role role);

	void delete(Long id);

	Role getById(Long id);

	void removeRelationShip(Role role);

	void bacthupdate(Role role);

}
