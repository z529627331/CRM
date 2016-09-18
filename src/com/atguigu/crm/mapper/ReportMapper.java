package com.atguigu.crm.mapper;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

	int getTotalCount( Map<String, Object> params);

	List<Map<String, Object>> getPageContent(Map<String, Object> params);
	
}
