package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {
	
	public int regist(MemberDto member);
	public MemberDto login(String id, String password);
	public MemberDto selectMember(String id, String eamil, String name);
	public List<MemberDto> selectAllMember();
	public int updateMember(MemberDto member);
	public int deleteMember(String id);
	
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String id) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	

//	public MemberDto searchPass(String id, String eamil, String name);
	
	
	
}
