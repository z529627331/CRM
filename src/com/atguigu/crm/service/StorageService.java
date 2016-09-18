package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.mapper.StorageMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class StorageService {
	@Autowired
	private StorageMapper mapper;
	
	@Transactional
	public void save(Storage storage){
		mapper.insert(storage);
	}

	@Transactional(readOnly=true)
	public Page<Storage> getPage(int pageNO, Map<String, Object> params) throws ParseException {
		params = CodeUtils.convertToQueryMap(params);
		Page<Storage> page = new Page<>();
		page.setPageNo(pageNO);
		pageNO = page.getPageNo();
		int pageSize = page.getPageSize();
		int totalCount = mapper.getTotalCount(params);
		page.setTotalCount(totalCount);
		int firstIndex = (pageNO-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		params.put("firstIndex", firstIndex);
		params.put("lastIndex", lastIndex);
		List<Storage> list = mapper.getLimitedList(params);
		page.setContent(list);
		return page;
	}
	@Transactional(readOnly=true)
	public Storage getById(Long id) {
		// TODO Auto-generated method stub
		return mapper.getById(id);
	}
	@Transactional
	public void update(Storage storage) {
		// TODO Auto-generated method stub
		mapper.update(storage);
	}
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
}
