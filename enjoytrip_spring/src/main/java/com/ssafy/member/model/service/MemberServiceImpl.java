package com.ssafy.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.member.model.MemberDto;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int regist(MemberDto member) {
		return memberMapper.regist(member);
	}

	@Override
	public MemberDto login(String id, String password) {
		return memberMapper.login(id, password);
	}

	@Override
	public MemberDto selectMember(String id) {
		return memberMapper.selectMember(id, null, null);
	}
	
	@Override
	public int updateMember(MemberDto member) {
		return memberMapper.updateMember(member);
	}
	

	@Override
	public int deleteMember(String id) {
		return memberMapper.deleteMember(id);
	}

	@Override
	public MemberDto searchPass(String id, String email, String name) {
		return memberMapper.selectMember(id, email, name);
	}

	@Override
	public List<MemberDto> selectAllMember() {
		return memberMapper.selectAllMember();
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return memberMapper.getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}



}
