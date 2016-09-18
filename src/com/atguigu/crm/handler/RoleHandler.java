package com.atguigu.crm.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.service.AuthorityService;
import com.atguigu.crm.service.RoleService;
@RequestMapping("/role")
@Controller
public class RoleHandler {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthorityService authService;
	
	
	@RequestMapping("/assign")
	public String assign(Role role){
		roleService.update(role);
		
		return "redirect:/role/list";
	}
	@RequestMapping("/input")
	public String toAdd(){
		return "role/input";
	}
	@RequestMapping("/assign/{id}")
	public String toAssign(@PathVariable("id") Long id,
							Map<String,Object> map){
		//查id对应的role
		Role role=roleService.getById(id);
		//得到所有父权限
		List<Authority> list = authService.getAllParents();
		map.put("role", role);
		map.put("parentList", list);
		return "role/assign";
	}
	@RequestMapping("/create")
	public String add(Role role){
		roleService.save(role);

		return "redirect:/role/list";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id){
		roleService.delete(id);
		return "redirect:/role/list";
	}
	
	
	
	
	
	@RequestMapping("/list")
	private String getAll(Map<String,Object> map){
		List<Role> list = roleService.getAll();
		map.put("list", list);
		return "role/list";
	}
	
	
}
