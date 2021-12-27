package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.config.MemberDetailService;
import com.sample.model.Member;
import com.sample.repository.MemberRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member userMem = memberRepository.findByuserName(username)
				.orElseThrow(() -> 
				new UsernameNotFoundException("User not found with username: " + username));
		
		return new User(userMem.getUserName(), userMem.getPassword(),
				MemberDetailService.createAuthorities(userMem));
	}
}
