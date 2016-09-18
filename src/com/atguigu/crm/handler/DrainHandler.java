package com.atguigu.crm.handler;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.CustomerDrain;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.DrainService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/drain")
@Controller
public class DrainHandler {
	@Autowired
	private DrainService service;
	@RequestMapping("/delay")
	public String doDelay(@RequestParam("id")Long id,
			@RequestParam(value="delay",required=false)String delay,
			@RequestParam(value="reason",required=false)String reason,
			Map<String, Object> map){
		if(delay!=null){
			CustomerDrain drain = service.getById(id);
			String delay2 = drain.getDelay();
			delay2=delay2+delay+"`";
			drain.setDelay(delay2);
			service.updateDelay(drain);
			map.put("drain", drain);
			return "redirect:/drain/delay/"+id;
		}else{
			CustomerDrain drain = service.getById(id);
			drain.setReason(reason);
			drain.setDrainDate(new Date());
			drain.setStatus("流失");
			service.confirm(drain);
			
			return "redirect:/drain/list";
		}
		
	}
	
	@RequestMapping("/delay/{id}")
	public String toDelay(@PathVariable("id")Long id,
					Map<String, Object> map){
		CustomerDrain drain = service.getById(id);
		map.put("drain", drain);
		return "drain/delay";
	}
	@RequestMapping("/list")
	public String drainList(@RequestParam(value="pageNo",required=false) String pageNoStr,
			HttpServletRequest request,
			Map<String, Object> map){
		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryStr = CodeUtils.convertMapToStr(params,prefix);
		map.put("queryString", queryStr);
		Page<CustomerDrain> page = service.getDrainPage(pageNoStr, params);
		map.put("page", page);
		return "drain/list";
	}
}
