package com.atguigu.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.mapper.ContactMapper;

@Service
public class ContactService {

	@Autowired
	private ContactMapper contactMapper;
	
	@Transactional
	public void save(Contact contact) {
		// TODO Auto-generated method stub
		contactMapper.save(contact);
	}
	@Transactional(readOnly=true)
	public List<Contact> getByCustId(Long id) {
		// TODO Auto-generated method stub
		return contactMapper.getByCustId(id);
	}

	
}
