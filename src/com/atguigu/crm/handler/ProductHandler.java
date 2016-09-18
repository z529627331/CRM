package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/product")
@Controller
public class ProductHandler {
	@Autowired
	private ProductService service;
	//带查询条件
	@RequestMapping("/list")
	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
			Map<String, Object> map,
			HttpServletRequest request) throws ParseException{
		String prefix= "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, prefix);
		//转换为查询条件发给浏览器
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNo=1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<Product> page = service.getPage(pageNo,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "product/list";
	}
//	@RequestMapping("/list")
//	public String list(@RequestParam(value="pageNo", required=false) String pageNoStr,
//			Map<String, Object> map){
//		int pageNo=1;
//		try {
//			pageNo=Integer.parseInt(pageNoStr);
//		} catch (NumberFormatException e) {}
//		Page<Product> page = service.getPage(pageNo);
//		map.put("page", page);
//		return "product/list";
//	}
	@RequestMapping("/create/{id}")
	public String toUpdate(@PathVariable("id") Integer id,
							Map<String, Object> map){
		Product product=service.getById(id);
		map.put("product", product);
		System.out.println("update");
		return "product/input";
	}
	
	
	@RequestMapping("/create")
	public String toAddNew(Map<String, Object> map){
		Product product = new Product();
		map.put("product", product);
		return "product/input";
	}
	@RequestMapping(value="/input",method=RequestMethod.PUT)
	public String update(Product product){
		service.update(product);
		return "redirect:/product/list";
	}
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String addNew(Product product){
		service.save(product);
		return "redirect:/product/list";
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		service.delete(id);
		return "redirect:/product/list";
	}
	
}
