package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.crm.entity.Product;

public interface ProductMapper {
	public List<Product> getAll();
	
	public List<Product> getLimitedList(Map<String,Object> map);
	
	public int getTotalCount(Map<String, Object> params);

	public void save(Product product);

	public Product getById(Integer id);

	public void update(Product product);

	public void delete(Integer id);
}
