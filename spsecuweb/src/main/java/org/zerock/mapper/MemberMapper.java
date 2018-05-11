package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.MemberAuthVO;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String mid);
	
	public void create(MemberVO vo);
	
	public void createAuth(MemberAuthVO auth);
	
	public void updatePassword(@Param("mid")String mid, @Param("enpw")String encryptedPw);
	
}
