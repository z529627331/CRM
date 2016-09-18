package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ReportService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/report")
@Controller
public class ReportHandler {
	@Autowired
	private ReportService reportService;
	
	
	@RequestMapping("/pay")
	public String pay(@RequestParam(value="pageNo",required=false) String pageNoStr,
						Map<String,Object> map,
						HttpServletRequest request) throws ParseException{
		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<Map<String,Object>> page = reportService.getPage(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "report/pay";
	}
}
