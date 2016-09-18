package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.CustomerServiceService;
import com.atguigu.crm.service.DictService;
import com.atguigu.crm.service.UserService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/service")
@Controller
public class ServiceHandler {
	@Autowired
	private CustomerServiceService service;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DictService dictService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/archive")
	public  String archive(@RequestParam("id") Long id,Map<String,Object> map){
		com.atguigu.crm.entity.CustomerService bean = service.getById(id);
		map.put("service", bean);
		return "service/archive/archive";
	}
	
	@RequestMapping("/archive/list")
	public String archiveList(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String,Object> map,
			HttpServletRequest request) throws ParseException{
		String prefix= "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<com.atguigu.crm.entity.CustomerService> page = service.getArchive(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "service/archive/list";
	
	}
	@RequestMapping("/do")
	public  String doDeal(com.atguigu.crm.entity.CustomerService bean){
		
		service.deal(bean);
		return "redirect:/service/deal/list";
	}
	
	@RequestMapping("/deal")
	public  String toDeal(@RequestParam("id") Long id,Map<String,Object> map){
		com.atguigu.crm.entity.CustomerService bean = service.getById(id);
		map.put("service", bean);
		return "service/deal/deal";
	}
	@RequestMapping("/doFeedback")
	public String doFeedback(com.atguigu.crm.entity.CustomerService bean){
		service.DoFeedback(bean);
		
		return "redirect:/service/feedback/list";
	}
	@RequestMapping("/feedback")
	public  String feedback(@RequestParam("id") Long id,Map<String,Object> map){
		com.atguigu.crm.entity.CustomerService bean = service.getById(id);
		map.put("service", bean);
		return "service/feedback/feedback";
	}
	@RequestMapping("/feedback/list")
	public String feedbackList(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String,Object> map,
			HttpServletRequest request) throws ParseException{
		String prefix= "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<com.atguigu.crm.entity.CustomerService> page = service.getBack(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "service/feedback/list";
	
	}
	
	@RequestMapping("/deal/list")
	public String dealList(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String,Object> map,
			HttpServletRequest request) throws ParseException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String prefix= "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		params.put("allotToId", user.getId());
		Page<com.atguigu.crm.entity.CustomerService> page = service.getUnDealed(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "service/deal/list";
	
	}
	
	@ResponseBody
	@RequestMapping("/allot")
	private String allot(com.atguigu.crm.entity.CustomerService bean){
		service.allot(bean);
		return "1";
	}
	
	
	@RequestMapping("/allot/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String,Object> map,
			HttpServletRequest request) throws ParseException{
		String prefix= "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<com.atguigu.crm.entity.CustomerService> page = service.getbyState(pageNo,params);
		map.put("page", page);
		List<User> users = userService.getAll();
		map.put("users", users);
		map.put("queryString", queryString);
		return "service/allot/list";
	}
	
	
	@RequestMapping("/create")
	private String create(Map<String,Object> map){
		com.atguigu.crm.entity.CustomerService service = new com.atguigu.crm.entity.CustomerService();
		List<Dict> serviceTypes = dictService.getByType("服务类型");
		List<Customer> custs = customerService.getAllCust();
		map.put("serviceTypes", serviceTypes);
		map.put("custs", custs);
		map.put("service", service);
		
		return "service/input";
	}
	
	@RequestMapping("/save")
	public String save(com.atguigu.crm.entity.CustomerService bean){
		service.save(bean);
		return "redirect:/service/create";
	}
	
}
