package org.zerock.service;

import java.util.List;

import org.zerock.domain.MemberAuthVO;
import org.zerock.domain.MemberVO;

public interface MemberService {

	public MemberVO get(String mid);
	
	public void add(MemberVO vo);

}
