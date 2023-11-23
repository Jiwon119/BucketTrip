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
	public MemberDto selectMember(String id, String email, String name);
	public List<MemberDto> selectMembers(String id, String eamil, String name);
	public List<MemberDto> selectAllMember();
	public int updateMember(MemberDto member);
	public int deleteMember(String id);
	
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	Object getRefreshToken(String id) throws SQLException;
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public List<MemberDto> getFriends(String id, String status) throws SQLException;
	public List<MemberDto> getFriendsRequest(String id) throws SQLException;
	public void addFriends(String id, String friend, int status) throws SQLException;
	public void updateFriendState(String userId, String friendId) throws SQLException;
	public List<MemberDto> getUserFriends(String id) throws SQLException;;
	

//	public MemberDto searchPass(String id, String eamil, String name);
	
	
	
}
