package com.atguigu.crm.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.Storage;
import com.atguigu.jpa.dao.StorageDao;

public class JpaTest {
	private ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext-jpa.xml");
	@Test
	public void test(){
		StorageDao dao = ioc.getBean(StorageDao.class);
		List<Storage> list = dao.findAll();
		System.out.println(list);
		System.out.println(222);
		System.out.println("jpa");
	}
}
