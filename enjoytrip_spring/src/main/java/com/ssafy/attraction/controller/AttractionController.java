package com.ssafy.attraction.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.service.AttractionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
@Slf4j
@Api(tags = {"Attraction Controller  API V1"})
public class AttractionController {

	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping("/mapList")
	public ResponseEntity<?> list() {
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, "");

		return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>검색 결과</big>을 반환해 줍니다.")
	@GetMapping("/mapSearch")
	public ResponseEntity<List<AttractionInfoDto>> search(@RequestParam(value = "area", required = false) String area,
			@RequestParam(value = "area2", required = false) String area2,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword) {

		log.debug("area : {}, area2 : {}, type: {}, keyword: {}", area, area2, type, keyword);
		AttractionInfoDto attractionInfoDto = new AttractionInfoDto();
		if(!"".equals(area)) attractionInfoDto.setSidoCode(Integer.parseInt(area));
		if(!"".equals(area2)) attractionInfoDto.setGugunCode(Integer.parseInt(area2));
		if(!"".equals(type)) attractionInfoDto.setContentTypeId(Integer.parseInt(type));
		attractionInfoDto.setTitle(keyword);
		List<AttractionInfoDto> list = attractionService.attractionList(attractionInfoDto, keyword);

		return new ResponseEntity<List<AttractionInfoDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>검색 결과</big>을 반환해 줍니다.")
	@GetMapping("/mapSearchId")
	public ResponseEntity<AttractionInfoDto> searchId(
			@RequestParam(value = "id", required = false) String id) {
		log.debug("id : {}", id);
		AttractionInfoDto attractionInfoDto = attractionService.searchAttractionId(id);

		return new ResponseEntity<AttractionInfoDto>(attractionInfoDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>시도 리스트</big>를 반환해 줍니다.")
	@GetMapping("/sidoList")
	public ResponseEntity<List<SidoDto>> sidoList() {
		List<SidoDto> sidoList = attractionService.sidoList();
		return new ResponseEntity<List<SidoDto>>(sidoList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "map", notes = "지도의 <big>구군 리스트</big>를 반환해 줍니다.")
	@GetMapping("/gugunList")
	public ResponseEntity<List<GugunDto>> guGunList(
			@RequestParam(value = "area", required = false) String sidoCode
			) {
		log.debug("gdgdgdgd {}", sidoCode);
		List<GugunDto> gugunList = attractionService.gugunList(sidoCode);
		return new ResponseEntity<List<GugunDto>>(gugunList, HttpStatus.OK);
	}
	
}
