package com.ssafy.plan.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.mapper.FavoriteMapper;
import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {
	
	private PlanMapper planMapper;
	
	public PlanServiceImpl(PlanMapper planMapper) {
		super();
		this.planMapper = planMapper;
	}

	@Override
	public List<PlanDto> getList(String userId) {
		return planMapper.getList(userId);
	}

	@Override
	public void createPlan(PlanDto planInfo) {
		// TODO Auto-generated method stub
		planMapper.createPlan(planInfo);
	}

	@Override
	public List<PlanDto> getPlaceList(int planId) {
		return planMapper.getPlaceList(planId);
	}

	@Override
	public void createJoinPlace(int attrId, int planId) {
		planMapper.createJoinPlace(attrId, planId);
	}

	@Override
	public void createJoinFriend(String friendName, int planId) {
		planMapper.createJoinFriend(friendName, planId);
	}

	@Override
	public List<MemberDto> getFriends(int planId) {
		return planMapper.getFriends(planId);
	}

	@Override
	public List<BoardDto> getFriendsPost(String userId) {
		return planMapper.getFriendsPost(userId);
	}


	
}
