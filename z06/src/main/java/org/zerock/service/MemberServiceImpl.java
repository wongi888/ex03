package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod = @__({ @Autowired }))
	private MemberMapper mapper;
	
	@Override
	public MemberVO login(String mid, String mpw) {

		return mapper.get(mid, mpw);

	}

}
