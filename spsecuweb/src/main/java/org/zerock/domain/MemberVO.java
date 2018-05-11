package org.zerock.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String mid, mpw, mname;
	
	private Date regdate;
	
	private List<MemberAuthVO> auths;
}
