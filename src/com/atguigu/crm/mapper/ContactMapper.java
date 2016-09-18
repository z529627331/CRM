package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Contact;

public interface ContactMapper {

	void save(Contact contact);

	List<Contact> getByCustId(Long id);

}
