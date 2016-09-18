package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.SalesPlan;
import com.atguigu.crm.service.SaleChanceService;
import com.atguigu.crm.service.SalesPlanService;
@RequestMapping("/plan")
@Controller
public class SalesPlanHandler {
	@Autowired
	private SalesPlanService service;
	@Autowired
	private SaleChanceService chanceService;
	
	@ResponseBody
	@RequestMapping(value="/execute")
	public String doExecute(SalesPlan plan){
		
		service.execute(plan);
		
		return "1";
	}
	
	@RequestMapping(value="/execute/{chanceId}")
	public String toExecute(@PathVariable("chanceId") Long id,
			Map<String,Object> map){
		SalesChance chance = chanceService.get(id);
		List<SalesPlan> list = service.getListByChance(id);
		map.put("chance", chance);
		map.put("list", list);
		return "plan/execute";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String delete(SalesPlan plan){
		service.delete(plan);
		return "1";
	}
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(SalesPlan plan){
		service.update(plan);
		return "1";
	}
	
	@ResponseBody
	@RequestMapping(value="/make",method=RequestMethod.POST)
	public String newTodo(SalesPlan plan ){
		service.save(plan);
		Long id = plan.getId();
		return id+"";
	}
	
	
	@RequestMapping("/make/{chanceId}")
	public String toMake(@PathVariable("chanceId") Long id,
						Map<String,Object> map){
		SalesChance chance = chanceService.get(id);
		List<SalesPlan> list = service.getListByChance(id);
		map.put("chance", chance);
		map.put("list", list);
		return "plan/make";
	}
}
