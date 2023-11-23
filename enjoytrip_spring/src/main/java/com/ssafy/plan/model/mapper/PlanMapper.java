package com.ssafy.plan.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> getList(String userId);

	void createPlan(PlanDto planInfo);
	
	void createJoinPlace(int attrId, int planId);

	List<PlanDto> getPlaceList(int planId);

	void createJoinPlace(String friendName, int planId);

	void createJoinFriend(String friendName, int planId);

	List<MemberDto> getFriends(int planId);
}
