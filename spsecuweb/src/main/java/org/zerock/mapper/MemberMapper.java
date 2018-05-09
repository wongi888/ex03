package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.MemberVO;

public interface MemberMapper {

	
	public MemberVO read(String mid);
	
	public void create(MemberVO vo);
	
	public void crpytPassword(@Param("mid")String mid, @Param("cryptPw") String cryptPw);
	
}
