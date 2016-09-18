package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.User;

public interface UserMapper {
	User getByName(@Param("name") String name);

	int getTotalCount();

	List<User> getLimitedList(Map<String, Object> map);

	void save(User user);

	User getById(Integer id);

	void update(User user);

	void delete(Integer id);

	User getById(Long id);

	List<User> getAll();
}
