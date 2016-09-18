package com.atguigu.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.NormalUtils;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	public User getByName(String name,String password){
		User user = userMapper.getByName(name);
		if(user!=null && password.equals(user.getPassword())
					&& user.getEnabled()==1){
			return user;
		}
		return null;
	}
	@Transactional(readOnly=true)
	public Page<User> getPage(int pageNo) {
		Page<User> page = new Page<>();
		int pageSize = page.getPageSize();
		page.setPageNo(pageNo);
		pageNo = page.getPageNo();
		int totalCount = userMapper.getTotalCount();
		page.setTotalCount(totalCount);
		int firstIndex = (pageNo-1)*pageSize;
		int lastIndex = firstIndex + pageSize;
		Map<String,Object> map = new HashMap<>();
		map.put("firstIndex", firstIndex);
		map.put("lastIndex", lastIndex);
		List<User> list =userMapper.getLimitedList(map);
		page.setContent(list);
		return page;
	}
	
	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		String salt = NormalUtils.randomString();
		user.setSalt(salt);
		userMapper.save(user);
	}
	@Transactional(readOnly=true)
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userMapper.getById(id);
	}
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		userMapper.update(user);
	}
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userMapper.delete(id);
	}
	@Transactional(readOnly=true)
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.getAll();
	}
}
