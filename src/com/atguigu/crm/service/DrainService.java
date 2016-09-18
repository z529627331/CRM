package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.mapper.DrainMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class DrainService {
	@Autowired
	private DrainMapper mapper;
	@Transactional(readOnly=true)
	public Page<CustomerDrain> getDrainPage(String pageNoStr,
			Map<String, Object> params) {
		int pageNo = 1;
		try{
			pageNo = Integer.parseInt(pageNoStr);
			
		}catch(Exception e){}
		//{LIKE_contact=, LIKE_custName=, LIKE_title=, status=2}
		
		Map<String, Object> rightParams = null;
		try {
			rightParams = CodeUtils.convertToQueryMap(params);
			System.out.println(rightParams);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int totalCount = mapper.getDrainCount(rightParams);
		
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		page.setTotalCount(totalCount);
		int firstIndex = (pageNo-1)*pageSize+1;
		int endIndex = firstIndex + pageSize;
		rightParams.put("firstIndex", firstIndex);
		rightParams.put("endIndex", endIndex);
		List<CustomerDrain> listInPage = mapper.getDrainListInPage(rightParams);
		page.setContent(listInPage);
		return page;
	}
	@Transactional(readOnly=true)
	public CustomerDrain getById(Long id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}
	@Transactional
	public void updateDelay(CustomerDrain drain) {
		// TODO Auto-generated method stub
		mapper.updateDelay(drain);
	}
	@Transactional
	public void confirm(CustomerDrain drain) {
		// TODO Auto-generated method stub
		mapper.confirm(drain);
	}
}
