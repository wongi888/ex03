package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {

	private String mid, mpw, mname, grade;
	
	private Date regdate, updatedate;
}
