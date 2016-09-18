package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.SalesChance;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.SaleChanceService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/chance")
@Controller
public class SaleChancehandler {
	@Autowired
	private SaleChanceService service;
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes attribute){
		service.delete(id);
		String message="删除成功";
		attribute.addAttribute("message", message);
		return "redirect:/chance/list";
	}
	@RequestMapping(value="/save",method=RequestMethod.PUT)
	public String update(SalesChance chance,
			HttpSession session){
		User user = (User) session.getAttribute("user");
		chance.setCreateBy(user);
		service.update(chance);
		return "redirect:/chance/list";
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(SalesChance chance,
			HttpSession session){
		User user = (User) session.getAttribute("user");
		chance.setCreateBy(user);
		service.save(chance);
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/save/{id}",method=RequestMethod.GET)
	public String input2(@PathVariable("id") Long id,
			Map<String,Object> map){
		SalesChance chance=service.get(id);
		map.put("chance", chance);
		return "chance/input";
	}
	
	@RequestMapping("/input")
	public String input(Map<String,Object> map){
		SalesChance chance = new SalesChance();
		chance.setCreateDate(new Date());
		map.put("chance", chance);
		return "chance/input";
	}
	
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String, Object> map,
			HttpServletRequest request,
			HttpSession session) throws ParseException{
		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryStr = CodeUtils.convertMapToStr(params,prefix);
		map.put("queryString", queryStr);
		User user =(User) session.getAttribute("user");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
		Page<SalesChance> page = service.getPage(pageNo, user,params);
		map.put("page", page);
		return "chance/list";
	}
	
//	@RequestMapping("/list")
//	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
//			Map<String, Object> map,
//			HttpSession session){
//		User user =(User) session.getAttribute("user");
//		int pageNo = 1;
//		try {
//			pageNo = Integer.parseInt(pageNoStr);
//		} catch (Exception e) {}
//		Page<SalesChance> page = service.getPage(pageNo, user);
//		map.put("page", page);
//		return "chance/list";
//	}
	@RequestMapping(value="/finish/{chanceId}",method=RequestMethod.PUT)
	public String finish(@PathVariable("id") Long id){
		service.finish(id);
		return "redirect:/plan/chance/list";
	}
	
	@RequestMapping(value="/stop/{chanceId}",method=RequestMethod.PUT)
	public String stop(@PathVariable("id") Long id){
		service.stop(id);
		return "redirect:/plan/chance/list";
	}
	
	@RequestMapping(value="/plan/details/{chanceId}",method=RequestMethod.GET)
	public String details(@PathVariable("id") Long id){
		//service.details(id);
		return "redirect:/plan/chance/list";
	}
}
