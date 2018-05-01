package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {

	public MemberVO login(String mid, String mpw);
}
