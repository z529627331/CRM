package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.CustomerService;
import com.atguigu.crm.mapper.CustomerServiceMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class CustomerServiceService {
	
	@Autowired
	private CustomerServiceMapper mapper;
	//新建
	@Transactional
	public void save(com.atguigu.crm.entity.CustomerService bean) {
		mapper.save(bean);
	}
	@Transactional(readOnly=true)
	public Page<CustomerService> getbyState(int pageNo, Map<String, Object> params) throws ParseException {
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		Page<CustomerService> page = new Page<>();
		int totalCount =mapper.getTotalCount(queryMap);
		page.setPageNo(pageNo);
		page.setTotalCount(totalCount);
		pageNo=page.getPageNo();
		int pageSize = page.getPageSize();
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		queryMap.put("pageNo", pageNo);
		List<CustomerService> list = mapper.getLimitedList(queryMap);
		page.setContent(list);
		return page;
	}
	//分配
	@Transactional
	public void allot(com.atguigu.crm.entity.CustomerService bean) {
		bean.setAllotDate(new Date());
		bean.setServiceState("已分配");
		mapper.allot(bean);
		
	}
	@Transactional(readOnly=true)
	public Page<com.atguigu.crm.entity.CustomerService> getUnDealed(int pageNo,
			Map<String, Object> params) throws ParseException {
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		Page<CustomerService> page = new Page<>();
		int totalCount = mapper.getundealedCount(queryMap);
		page.setPageNo(pageNo);
		page.setTotalCount(totalCount);
		pageNo=page.getPageNo();
		int pageSize = page.getPageSize();
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		queryMap.put("pageNo", pageNo);
		List<CustomerService> list = mapper.getUndealed(queryMap);
		page.setContent(list);
		return page;
	}
	@Transactional(readOnly=true)
	public com.atguigu.crm.entity.CustomerService getById(Long id) {
		return mapper.getById(id);
	}
	//处理
	@Transactional
	public void deal(com.atguigu.crm.entity.CustomerService bean) {
		bean.setServiceState("已处理");
		mapper.doDeal(bean);
		
	}
	@Transactional(readOnly=true)
	public Page<com.atguigu.crm.entity.CustomerService> getBack(int pageNo,
			Map<String, Object> params) throws ParseException {
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		Page<CustomerService> page = new Page<>();
		int totalCount = mapper.getFeedbackCount(queryMap);
		page.setPageNo(pageNo);
		page.setTotalCount(totalCount);
		pageNo=page.getPageNo();
		int pageSize = page.getPageSize();
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		queryMap.put("pageNo", pageNo);
		List<CustomerService> list = mapper.getFeedBackList(queryMap);
		page.setContent(list);
		return page;
	}
	//反馈
	@Transactional
	public void DoFeedback(com.atguigu.crm.entity.CustomerService bean) {
		bean.setServiceState("已归档");
		mapper.doFeedback(bean);
	}
	@Transactional(readOnly=true)
	public Page<com.atguigu.crm.entity.CustomerService> getArchive(int pageNo,
			Map<String, Object> params) throws ParseException {
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		Page<CustomerService> page = new Page<>();
		int totalCount = mapper.getArchiveCount(queryMap);
		page.setPageNo(pageNo);
		page.setTotalCount(totalCount);
		pageNo=page.getPageNo();
		int pageSize = page.getPageSize();
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		queryMap.put("pageNo", pageNo);
		List<CustomerService> list = mapper.getArchiveList(queryMap);
		page.setContent(list);
		return page;
	}
}
