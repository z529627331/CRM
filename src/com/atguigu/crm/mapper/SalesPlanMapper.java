package com.atguigu.crm.mapper;

import java.util.List;

import com.atguigu.crm.entity.SalesPlan;

public interface SalesPlanMapper {
	SalesPlan getById(int id);

	List<SalesPlan> getListByChance(Long id);

	void save(SalesPlan plan);

	void update(SalesPlan plan);

	void delete(SalesPlan plan);

	void execute(SalesPlan plan);
}
