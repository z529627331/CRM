package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.crm.entity.Storage;

public interface StorageMapper {
	public List<Storage> getList();
	
	public int getTotalCount(Map<String, Object> params);

	public List<Storage> getLimitedList(Map<String, Object> map);
	
	public void insert(Storage storage);

	public Storage getById(@Param("id") Long id);

	public void update(Storage storage);

	public void delete(@Param("id")Long id);
}
