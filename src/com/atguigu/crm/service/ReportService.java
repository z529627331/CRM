package com.atguigu.crm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.mapper.ReportMapper;
import com.atguigu.crm.orm.Page;
import com.atuigu.crm.utils.CodeUtils;

@Service
public class ReportService {
	@Autowired
	private ReportMapper mapper;
	@Transactional(readOnly=true)
	public Page<Map<String, Object>> getPage(int pageNo,
			Map<String, Object> params) throws ParseException {
		Page<Map<String, Object>> page = new Page<>();
		Map<String, Object> queryMap = CodeUtils.convertToQueryMap(params);
		page.setPageNo(pageNo);
		pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		queryMap.put("pageNo", pageNo);
		int totalCount = mapper.getTotalCount(queryMap);
		page.setTotalCount(totalCount);
		int firstIndex = (pageNo-1)*pageSize+1;
		int lastIndex = firstIndex+pageSize;
		queryMap.put("firstIndex", firstIndex);
		queryMap.put("lastIndex", lastIndex);
		List<Map<String,Object>> content = mapper.getPageContent(queryMap);
		page.setContent(content);
		return page;
	}
}
