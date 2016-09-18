package com.atguigu.crm.handler;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.atguigu.crm.entity.Product;
import com.atguigu.crm.entity.Storage;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.ProductService;
import com.atguigu.crm.service.StorageService;
import com.atuigu.crm.utils.CodeUtils;
@RequestMapping("/storage")
@Controller
public class StorageHandler {
	@Autowired
	private StorageService service;
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String list(Map<String,Object> map,
						HttpServletRequest request,
						@RequestParam(value="pageNo",required=false) String pageNoStr) throws ParseException{
		String prefix = "search_";
		Map<String, Object> params = WebUtils.getParametersStartingWith(request,prefix);
		String queryString = CodeUtils.convertMapToStr(params, prefix);
		int pageNO=1;
		try {
			pageNO = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<Storage> page = service.getPage(pageNO,params);
		map.put("page", page);
		map.put("queryString", queryString);
		return "storage/list";
	}
	@RequestMapping("/create/{id}")
	public String update(@PathVariable("id")Long id,
			Map<String,Object> map){
		Storage storage = service.getById(id);
		List<Product> list = productService.getAll();
		map.put("list", list);
		map.put("storage", storage);
		return "storage/input";
	}
	
	
	@RequestMapping("/create")
	public String create(Map<String,Object> map){
		Storage storage = new Storage();
		List<Product> list = productService.getAll();
		map.put("list", list);
		map.put("storage", storage);
		return "storage/input";
	} 
	@RequestMapping(value="/insert",method=RequestMethod.PUT)
	public String doUpdate(Storage storage){
		service.update(storage);
		return "redirect:/storage/list";
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Long id){
		service.delete(id);
		
		return "redirect:/storage/list";
	}
	
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Storage storage){
		service.save(storage);
		return "redirect:/storage/list";
	}
}
