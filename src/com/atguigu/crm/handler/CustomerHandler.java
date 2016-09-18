package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Contact;
import com.atguigu.crm.entity.Customer;
import com.atguigu.crm.entity.Dict;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ContactService;
import com.atguigu.crm.service.CustomerService;
import com.atguigu.crm.service.DictService;
import com.atuigu.crm.utils.CodeUtils;

@RequestMapping("/customer")
@Controller
public class CustomerHandler {
	
	@Autowired
	private DictService dictService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNo",required=false) String pageNo,
				HttpServletRequest request,
				Map<String, Object> map){
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		String queryString = CodeUtils.convertMapToStr(params, "search_");
		
		Page<Customer> page = customerService.getPage(pageNo,params);
		
		List<Dict> regions = customerService.getAllregion();
		
		
		map.put("queryString", queryString);
		map.put("page", page);
		map.put("regions", regions);
		
		return "customer/list";
	}
	@ResponseBody
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id){
		customerService.delete(id);
		return "1";
	}
	@RequestMapping(value="/list",method=RequestMethod.PUT)
	public String edit(Customer customer,Map<String,Object> map){
		customerService.update(customer);
		map.put("message", "修改成功");
		return "customer/input";
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String toEdit(Map<String,Object> map,
					@PathVariable("id") Long id){
		//查询id对应的cust
		Customer cust = customerService.getById(id);
		List<Dict> satifies = dictService.getByType("满意度");
		List<Dict> credits = dictService.getByType("信用度");
		List<Dict> regions =dictService.getByType("地区");
		List<Dict> levels = dictService.getByType("客户等级");
		List<Contact> contacts = contactService.getByCustId(id);
		map.put("customer", cust);
		map.put("satifies", satifies);
		map.put("credits", credits);
		map.put("regions", regions);
		map.put("levels", levels);
		map.put("contacts", contacts);
		return "customer/input";
	}
	
}
