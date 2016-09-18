package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.mapper.SalesPlanMapper;

@Service
public class SalesPlanService {
	@Autowired
	private SalesPlanMapper mapper;
	
	
	@Transactional(readOnly=true)
	public SalesPlan getById(Integer id) {
		return mapper.getById(id);
	}

	@Transactional(readOnly=true)
	public List<SalesPlan> getListByChance(Long id) {
		// TODO Auto-generated method stub
		return mapper.getListByChance(id);
	}
	@Transactional
	public void save(SalesPlan plan) {
		// TODO Auto-generated method stub
		mapper.save(plan);
		
	}
	@Transactional
	public void update(SalesPlan plan) {
		// TODO Auto-generated method stub
		mapper.update(plan);
	}
	@Transactional
	public void delete(SalesPlan plan) {
		// TODO Auto-generated method stub
		mapper.delete(plan);
	}
	@Transactional
	public void execute(SalesPlan plan) {
		// TODO Auto-generated method stub
		mapper.execute(plan);
	}

	
	
}
