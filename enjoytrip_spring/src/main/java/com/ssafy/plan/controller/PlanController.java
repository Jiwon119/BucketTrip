package com.ssafy.plan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.attraction.model.AttractionDescriptionDto;
import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Slf4j
@Api(tags = {"Favorite Controller  API V1"})
public class PlanController {
	private PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@ApiOperation(value = "plan", notes = "여행계획 리스트")
	@GetMapping("/list/{userId}")
	protected ResponseEntity<?> listPlan(
			 @PathVariable String userId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PlanDto> list = planService.getList(userId);
		
		
		
		
		log.debug("1. Plan List : {}", list);
		map.put("planList", list);
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "plan", notes = "여행계획 리스트")
	@GetMapping("/placeList/{planId}")
	protected ResponseEntity<?> listPlace(
			@PathVariable int planId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<PlanDto> list = planService.getPlaceList(planId);
		
		
//		List<AttractionInfoDto> attInfoList = new ArrayList<AttractionInfoDto>();
//		List<AttractionDescriptionDto> attDescList = new ArrayList<AttractionDescriptionDto>();
//		
//		
//		
//		
//		if(list.size()>0)
//			log.debug("Plan LLLL : {}", list.get(0).getAttrInfo());
//		log.debug("Plan List : {}", list);
//		log.debug("Plan List : {}", list);
		map.put("planList", list);

		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "plan", notes = "여행계획 작성")
	@PostMapping("/write")
	protected ResponseEntity<?> createPlan(
			@RequestBody Map<String,Object> map) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		log.debug("---------------------------------------------------map.planInfo : {}", map);
		
		PlanDto planInfo = mapper.convertValue(map.get("planInfo"), PlanDto.class);
		
		planService.createPlan(planInfo);
		log.debug("map.planInfo : {}", planInfo);
		log.debug("dtodtodtodtodtodto : {}", planInfo.getId());
		
		List<Integer> list = (List<Integer>) map.get("planList");
		
		for (int li : list) {
			planService.createJoinPlace(li, planInfo.getId());
		}
		
		log.debug("map.list : {}", list);
		log.debug("listlistlistlist : {}", list.get(1));
		
		List<String> friends = (List<String>) map.get("friends");
		friends.add(planInfo.getUserId());

		for (String li : friends) {
			planService.createJoinFriend(li, planInfo.getId());
		}
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@ApiOperation(value = "plan", notes = "여행계획 친구 목록 검색")
	@GetMapping("/friend/{planId}")
	protected ResponseEntity<?> getFriends(@PathVariable int planId) throws Exception {
		List<MemberDto> list = planService.getFriends(planId);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
//	
//	@ApiOperation(value = "plan", notes = "여행계획 리스트")
//	@PutMapping("/write")
//	protected ResponseEntity<?> updatePlan(
//			 @PathVariable String userId) throws Exception {
//		
//		Map<String, Object> map = new HashMap<String, Object>();
////		List<FavoriteDto> list = favoriteService.getList(userId);
////		
////		log.debug("Favorite List : {}", list);
////		map.put("favoriteList", list);
//		
//		return new ResponseEntity<>(map, HttpStatus.OK);
//	}
//
//
//	
//	@ApiOperation(value = "수정 할 글 얻기", notes = "글번호에 해당하는 여행 정보를 반환한다.")
//	@GetMapping("/modify/{planId}")
//	public ResponseEntity<?> getModifyPlan(
//			@PathVariable("planId") @ApiParam(value = "얻어올 글의 글번호.", required = true) int planId)
//			throws Exception {
//
//		
//		return new ResponseEntity<>(null, HttpStatus.OK);
//	}
//
//	
//	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("/modify")
//	public ResponseEntity<String> modifyArticle(
//			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) PlanDto planDto) throws Exception {
//
//		return new ResponseEntity<>(null, HttpStatus.OK);
//	}
//	
//	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@DeleteMapping("delete/{planId}")
//	public ResponseEntity<String> deleteArticle(@PathVariable("planId") @ApiParam(value = "삭제할 글의 글번호.", required = true) int planId) throws Exception {
//
//		return new ResponseEntity<>(null, HttpStatus.OK);
//	}
	
}
