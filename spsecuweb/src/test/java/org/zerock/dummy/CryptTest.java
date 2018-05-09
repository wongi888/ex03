package org.zerock.dummy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class CryptTest {

	BCryptPasswordEncoder pwEncoder;
	
	@org.junit.Before
	public void setUp() {
		
		pwEncoder = new BCryptPasswordEncoder(6);
	}
	
	@Test
	public void testCrypt() {
			
		
		String str = "pw99";
		
		String enStr = pwEncoder.encode(str);
		
		log.info(enStr);
		
		
	}
}










