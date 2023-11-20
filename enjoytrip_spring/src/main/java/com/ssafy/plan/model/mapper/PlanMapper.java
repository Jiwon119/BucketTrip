package com.ssafy.plan.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.plan.model.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> getList(String userId);

	void createPlan(PlanDto planInfo);
	
	void createJoinPlace(int attrId, int planId);

	
}
