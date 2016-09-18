package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.SalesChanceMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;
import com.atuigu.crm.utils.NormalUtils;

@Service
public class SaleChanceService {
	@Autowired
	private SalesChanceMapper mapper;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ContactService contactService;
	
	@Transactional
	public void save(SalesChance chance){
		chance.setStatus(1);
		mapper.saveChance(chance);
	}
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, User user, Map<String, Object> params) throws ParseException{
		params.put("createBy", user);
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		Page<SalesChance> page =  new Page<>();
		int pageSize = page.getPageSize();
		page.setPageNo(pageNo);
		long totalCount = getTotalCount(queryMap);
		page.setTotalCount(totalCount);
		int firstIndex = (page.getPageNo()-1)*pageSize;
		int lastIndex=firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		List<SalesChance> list=mapper.getLimitedList(queryMap);
		page.setContent(list);
		return page;
	}
	
	@Transactional(readOnly=true)
	public long getTotalCount(Map<String, Object> queryMap){
		return mapper.getTotalCount(queryMap);
	}
	@Transactional(readOnly=true)
	public SalesChance get(Long id) {
		// TODO Auto-generated method stub
		return mapper.get(id);
	}
	@Transactional
	public void update(SalesChance chance) {
		// TODO Auto-generated method stub
		mapper.update(chance);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
	@Transactional
	public void finish(Long id) {
		
		//2. stauts 字段的值修改为 3
		SalesChance chance = mapper.get(id);
		chance.setStatus(3);
		mapper.update(chance);
		//3. 向 customers 和 contacts 数据表中各插入一条记录。
		//Cusomers 数据表中插入 3 个字段：name，no（随机字符串） 和 state（正常）
		Customer customer = new Customer();
		String no = NormalUtils.randomString();
		customer.setNo(no);
		customer.setName(chance.getCustName());
		customer.setState("正常");
		customerService.save(customer);
		
		
		//向 contacts 数据表也插入 3 个字段：name、tel、customer_id
		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		contact.setCustomer(customer);
		contactService.save(contact);
		//注意：在插入 contacts 记录时，需要用到插入到 customers 数据表中的 id，
		//使用 mybatis 的 selectKey 来获取 customers 中的 id
	}

	public void stop(Long id) {
		//2.status 修改为 4
		SalesChance chance = mapper.get(id);
		chance.setStatus(4);
		mapper.update(chance);
	}
}
