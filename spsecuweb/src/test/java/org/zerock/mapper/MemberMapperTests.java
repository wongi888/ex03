package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class MemberMapperTests {

	@Setter(onMethod = @__({ @Autowired }))
	private MemberMapper mapper;

	@Setter(onMethod = @__({ @Autowired }))
	private PasswordEncoder pwEncoder;
	
	@Test
	public void testDummyMember() {
		
		for(int i = 0; i < 100; i++) {
		
			MemberVO member = new MemberVO();
			
			member.setMid("user" + i);
			member.setMpw("pw" + i);
			member.setMname("USER" + i);
			
			if(i < 80) {
				member.setGrade("ROLE_USER");
			}else if(i < 90) {
				member.setGrade("ROLE_MANAGER");
			}else {
				member.setGrade("ROLE_ADMIN");
			}
			
			mapper.create(member);
			
		}
	}
	
	@Test
	public void testCryptPassword() {
		
		for(int i = 0; i < 100; i++) {
			
			mapper.crpytPassword("user"+i, pwEncoder.encode("pw"+i));
			
		}
		
	}
	
	
}
