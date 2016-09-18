package com.atguigu.crm.orm;

import java.util.ArrayList;
import java.util.List;

public class Navigation {
	private Long id;
	private String text;
	private String url;
	private List<Navigation> children= new ArrayList<>();
	private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Navigation> getchildren() {
		return children;
	}
	public void setchildren(List<Navigation> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Navigation(Long id, String text, String url,
			List<Navigation> children, String state) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.children = children;
		this.state = state;
	}
	public Navigation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Navigation(Long id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
}
