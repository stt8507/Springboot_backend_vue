package com.sample.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.sample.model.Member;

public class MemberDetailService {

	private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils
			.createAuthorityList("USER");
	
	public static Collection<? extends GrantedAuthority> createAuthorities(
			Member member) {
		return USER_ROLES; //否則則為一般使用者
	}
}
