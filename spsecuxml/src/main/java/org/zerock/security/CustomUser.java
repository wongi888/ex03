package org.zerock.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Log4j

public class CustomUser extends User {

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		log.info("username: " + username);
		log.info("password: " + password);
	}
	
	public CustomUser(MemberVO member) {
		super(member.getMid(), member.getMpw(), Collections.singletonList(new SimpleGrantedAuthority(member.getGrade())) );
	}

}
