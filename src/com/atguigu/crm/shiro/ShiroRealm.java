package com.atguigu.crm.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atguigu.crm.entity.Authority;
import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;
@Component
public class ShiroRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper=null;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		User user = (User) pc.getPrimaryPrincipal();
		Set<String> roles = new HashSet<>();
		List<Authority> authorities = user.getRole().getAuthorities();
		for (Authority authority : authorities) {
			roles.add(authority.getName());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		User user = userMapper.getByName(username);
		if(user == null){
			throw new UnknownAccountException();
		}
		if(user.getEnabled() != 1){
			throw new DisabledAccountException();
		}
		Object principal = user;
		Object hashedCredentials=user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, 
				credentialsSalt, getName());
		return info;
	}
	@PostConstruct //相当于配置 bean 节点时的 init-method
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		
		setCredentialsMatcher(matcher);
	}
}
