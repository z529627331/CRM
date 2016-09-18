package com.atguigu.crm.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;


public abstract class IdEntity {

	protected Long id;
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
