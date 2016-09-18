package com.atguigu.crm.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.Role;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.orm.Navigation;
import com.atguigu.crm.orm.Page;
import com.atguigu.crm.service.RoleService;
import com.atguigu.crm.service.UserService;
@RequestMapping("/user")
@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/index.jsp";
	}
	@RequestMapping("/list")
	public String list(Map<String,Object> map,
			@RequestParam(value="pageNo",required=false) String pageNoStr){
		int pageNo = 1;
		try {
			Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		Page<User> page=userService.getPage(pageNo);
		map.put("page", page);
		return "user/list";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="name",required=false) String name,
				@RequestParam(value="password",required=false) String password,
				HttpSession session,
				RedirectAttributes attribute,
				Locale locale){
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(name, password);
            token.setRememberMe(true);
            try{
            	currentUser.login(token);
            }catch(AuthenticationException ae){
            	String code="error.user.login";
        		String message=messageSource.getMessage(code, null,  locale);
        		attribute.addFlashAttribute("message",message);
        		return "redirect:/index.jsp";
            }
		}
		Object user = currentUser.getPrincipals().getPrimaryPrincipal();
		session.setAttribute("user", user);
		return "redirect:/success";
		
	}
//	@RequestMapping(value="/login",method=RequestMethod.POST)
//	public String login(@RequestParam(value="name",required=false) String name,
//				@RequestParam(value="password",required=false) String password,
//				HttpSession session,
//				RedirectAttributes attribute,
//				Locale locale){
//		User user = userService.getByName(name, password);
//		if(user!=null){
//			session.setAttribute("user", user);
//			return "redirect:/success";
//		}
//		String code="error.user.login";
//		String message=messageSource.getMessage(code, null,  locale);
//		attribute.addFlashAttribute("message",message);
//		return "redirect:/index.jsp";
//	}
	@RequestMapping("/create/{id}")
	public String toUpdate(@PathVariable("id") Long id,
							Map<String,Object> map){
		User user = userService.getById(id);
		List<Role> roles = roleService.getAll();
		map.put("user", user);
		map.put("roles", roles);
		return "user/input";
	}
	@RequestMapping("/create")
	public String toAdd(Map<String,Object> map){
		User user = new User();
		List<Role> roles = roleService.getAll();
		map.put("user", user);
		map.put("roles", roles);
		return "user/input";
	}
	@RequestMapping(value="/input",method=RequestMethod.POST)
	public String save(User user,Map<String,Object> map){
		userService.save(user);
		return "redirect:/user/list";
	}
	@RequestMapping(value="/input",method=RequestMethod.PUT)
	public String update(User user,Map<String,Object> map){
		userService.update(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		userService.delete(id);
		return  "redirect:/user/list";
	}
	
	
	@ResponseBody
	@RequestMapping("/navigate")
	public List<Navigation> navigate(HttpSession session){
		ServletContext context = session.getServletContext();
		String contextPath = context.getContextPath();
		User user = (User) session.getAttribute("user");
		Role role = user.getRole();
		List<Navigation> navigations = new ArrayList<>();
		Navigation navi = new Navigation(Long.MAX_VALUE, "客户信息管理系统");
		navigations.add(navi);
		Map<Long,Navigation> par = new HashMap<Long, Navigation>();
		List<Authority> authorities = role.getAuthorities();
		for (Authority subAuth : authorities) {
			Navigation subNavi = new Navigation(subAuth.getId(), subAuth.getDisplayName());
			subNavi.setUrl(contextPath+subAuth.getUrl());
			Authority pa = subAuth.getParentAuthority();
			Navigation pn = par.get(pa.getId());
			if(pn==null){
				pn = new Navigation(pa.getId(),pa.getDisplayName());
				navi.getchildren().add(pn);
				pn.setState("closed");
				par.put(pa.getId(), pn);
			}
			pn.getchildren().add(subNavi);
		}
		
		
		return navigations;
	}
	
}
