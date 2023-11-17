package com.ssafy.member.model.service;

import java.util.List;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	
	public int regist(MemberDto member);
	public MemberDto login(String id, String password);
	public MemberDto selectMember(String id);
	public List<MemberDto> selectAllMember();
	public int updateMember(MemberDto member);
	public int deleteMember(String id);
	public MemberDto searchPass(String id, String eamil, String name);
	
	void saveRefreshToken(String id, String refreshToken) throws Exception;
	Object getRefreshToken(String id) throws Exception;
	void deleRefreshToken(String id) throws Exception;

}
