package com.ssafy.plan.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.CommentDto;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.util.PageNavigation;

public interface PlanService {

	List<PlanDto> getList(String userId);

	void createPlan(PlanDto planInfo);

	void createJoinPlace(int attrId, int planId);

	List<PlanDto> getPlaceList(int planId);

	void createJoinFriend(String friendName, int planId);

	List<MemberDto> getFriends(int planId);
	
}
