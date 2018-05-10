package org.zerock.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j

public class CustomUserDetailService implements UserDetailsService {
	
	@Setter(onMethod = @__({ @Autowired }))
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		log.info("loadUserByUsername: " + username);
		
		
		log.info(mapper);
		
		MemberVO vo = mapper.read(username);
		
		CustomUser user = new CustomUser(vo.getMid(), vo.getMpw(), 
				vo.getAuths().stream().map(ele -> new SimpleGrantedAuthority(ele.getAuth())).collect(Collectors.toList()) );

		return user;
	}

}
