package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.Dict;


public interface DictMapper {

	List<Dict> getByType(String string);

	

}
