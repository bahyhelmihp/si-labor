//package com.apap.silabor.security;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.apap.silabor.model.UserRoleModel;
//import com.apap.silabor.repository.UserRoleDb;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	private UserRoleDb userRoleDb;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		UserRoleModel user = userRoleDb.findByUsername(username);
//		
//		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
//		
//		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//	}
//}
