package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.mapper.ProductMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class ProductService {
	@Autowired
	private ProductMapper mapper;
	
	@Transactional(readOnly=true)
	public List<Product> getAll(){
		return mapper.getAll();
	}
	
	@Transactional(readOnly=true)
	public Page<Product> getPage(int pageNo, Map<String, Object> params) throws ParseException{
		params = CodeUtils.convertToQueryMap(params);
		Page<Product> page = new Page<>();
		int totalCount = mapper.getTotalCount(params);
		page.setTotalCount(totalCount);
		page.setPageNo(pageNo);
		pageNo=page.getPageNo();
		int pageSize = page.getPageSize();
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		params.put("firstIndex", firstIndex);
		params.put("lastIndex", lastIndex);
		List<Product> list = mapper.getLimitedList(params);
		page.setContent(list);
		return page;
	}
	@Transactional
	public void save(Product product) {
		// TODO Auto-generated method stub
		mapper.save(product);
	}
	@Transactional(readOnly=true)
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}
	@Transactional
	public void update(Product product) {
		// TODO Auto-generated method stub
		mapper.update(product);
	}
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
}
