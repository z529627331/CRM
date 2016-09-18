package com.atguigu.crm.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.mapper.UserMapper;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.service.DrainService;
import com.atguigu.crm.service.SaleChanceService;


public class TestHandler {
	private ApplicationContext ioc= new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void test06(){
		ReportMapper mapper = ioc.getBean(ReportMapper.class);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("firstIndex", 1);
		map.put("lastIndex", 6);
		int count = mapper.getTotalCount(map);
		System.out.println(count);
	}
	
	@Test
	public void test05(){
		UserMapper mapper = ioc.getBean(UserMapper.class);
		User user = mapper.getByName("bcde");
		Role role = user.getRole();
		System.out.println(role.getName());
		List<Authority> authorities = role.getAuthorities();
		for (Authority a : authorities) {
			System.out.println(a.getDisplayName());
		}
	}
	
	@Test
	public void test99(){
		DrainService service = ioc.getBean(DrainService.class);
		CustomerDrain drain = service.getById(204L);
		System.out.println(drain.getCustomer().getName());
		 String name = drain.getCustomer().getManager().getName();
		 System.out.println(name);
		
//		List<String> list = drain.getDelayStr();
//		System.out.println(list);
	}
	@Test
	public void test(){
		ContactService service = ioc.getBean(ContactService.class);
		List<Contact> list = service.getByCustId(141L);
		for (Contact c : list) {
			
			System.out.println(c.getId()+"-"+c.getName());
		}
		DictService dictService = ioc.getBean(DictService.class);
		List<Dict> list2 = dictService.getByType("满意度");
		
		System.out.println(list2);
	}
	@Test
	public void testFinish(){
		
		SaleChanceService saleChanceService = ioc.getBean(SaleChanceService.class);
		saleChanceService.finish(2023l);
	}
	
	@Test
	public void test03(){
		StorageMapper mapper = ioc.getBean(StorageMapper.class);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("firstIndex", 1);
		map.put("lastIndex", 6);
		map.put("product.name", "%T430%");
		int totalCount = mapper.getTotalCount(map);
		System.out.println(totalCount);
	}
	
	@Test
	public void test061(){
		DrainService service = ioc.getBean(DrainService.class);
		Map<String,Object> params = new HashMap<>();
		Page<CustomerDrain> page = service.getDrainPage("2", params);
		List<CustomerDrain> content = page.getContent();
		for (CustomerDrain d : content) {
			
			System.out.println(d);
		}
	}
	@Test
	public void test02(){
		CustomerService service = ioc.getBean(CustomerService.class);
		System.out.println(service);
		
	}
}
