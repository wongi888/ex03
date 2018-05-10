package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberAuthVO;
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
			
			mapper.create(member);
			
			
			if(true) {
			
				MemberAuthVO vo = new MemberAuthVO();
				vo.setMid("user" +i);
				vo.setAuth("ROLE_USER");
				mapper.createAuth(vo);

			}
			
			if(i >= 80) {
				
				MemberAuthVO vo = new MemberAuthVO();
				vo.setMid("user" +i);
				vo.setAuth("ROLE_MANGER");
				mapper.createAuth(vo);
			}
			
			if(i >= 90) {
				
				MemberAuthVO vo = new MemberAuthVO();
				vo.setMid("user" +i);
				vo.setAuth("ROLE_ADMIN");
				mapper.createAuth(vo);
			}
		}
	}
	
	@Test
	public void testRead() {
		
		log.info(mapper.read("user99"));
	}
	
	@Test
	public void testUpdate() {
		
		for(int i = 0; i < 100; i++) {
		
			String mid = "user" +i;
			String enpw = pwEncoder.encode("pw" + i);
			
			mapper.updatePassword(mid, enpw);
			
			
		}
		
	}
	
}
