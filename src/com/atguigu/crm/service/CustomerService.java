package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.mapper.CustomerMapper;
import com.atguigu.crm.mapper.DictMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class CustomerService {
	@Autowired
	private CustomerMapper mapper;
	@Autowired
	private DictMapper dictMapper;
	
	@Transactional(readOnly=true)
	public Page<Customer> getPage(String pageNoStr,Map<String, Object> params){
		
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
		
		
		int totalCount = mapper.getTotalCount(rightParams);
		
		Page<Customer> page = new Page<>();
		page.setPageNo(pageNo);
		page.setTotalCount(totalCount);
		
		int firstIndex = (page.getPageNo()-1)*page.getPageSize();
		int endIndex = firstIndex + page.getPageSize();
		
		rightParams.put("firstIndex", firstIndex);
		rightParams.put("endIndex", endIndex);
		
		
		List<Customer> listInPage = mapper.getListInPage(rightParams);
		page.setContent(listInPage);
		
		return page;
	}

	@Transactional
	public void save(Customer customer) {
		mapper.save(customer);
		
	}

	@Transactional(readOnly=true)
	public List<Dict> getAllregion() {
		
		return dictMapper.getByType("地区");
	}

	@Transactional(readOnly=true)
	public Customer getById(Long id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}
	@Transactional
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		mapper.update(customer);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
	@Transactional(readOnly=true)
	public List<Customer> getAllCust() {
		// TODO Auto-generated method stub
		return mapper.getAllCust();
	}
	
}
