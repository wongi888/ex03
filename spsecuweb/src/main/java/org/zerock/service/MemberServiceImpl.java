package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MemberAuthVO;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod = @__({ @Autowired }))
	private MemberMapper mapper;
	
	@Override
	public MemberVO get(String mid) {

		return mapper.read(mid);

	}

	@Transactional
	@Override
	public void add(MemberVO vo) {

		mapper.create(vo);
		
		vo.getAuths().forEach(auth -> mapper.createAuth(auth));
		
	}



}
