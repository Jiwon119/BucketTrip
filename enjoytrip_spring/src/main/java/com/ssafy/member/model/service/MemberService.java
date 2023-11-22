package com.ssafy.member.model.service;

import java.util.List;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	
	public int regist(MemberDto member);
	public MemberDto login(String id, String password);
	public MemberDto selectMember(String id);
	public List<MemberDto> selectMembers(MemberDto member);
	public List<MemberDto> selectAllMember();
	public int updateMember(MemberDto member);
	public int deleteMember(String id);
	public MemberDto searchPass(String id, String eamil, String name);
	
	void saveRefreshToken(String id, String refreshToken) throws Exception;
	Object getRefreshToken(String id) throws Exception;
	void deleRefreshToken(String id) throws Exception;
	
	public List<MemberDto> getFriends(String id, String status) throws Exception;
	public List<MemberDto> getFriendsRequest(String id) throws Exception;
	public void addFriends(String id, String friend, int status) throws Exception;
	public void updateFriendState(String userId, String friendId) throws Exception;
}