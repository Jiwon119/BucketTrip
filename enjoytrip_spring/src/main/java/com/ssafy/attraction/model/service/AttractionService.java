package com.ssafy.attraction.model.service;

import java.util.List;

//import com.ssafy.attraction.model.AttractionDescriptionDto;
import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

public interface AttractionService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto, String title);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

	AttractionInfoDto searchAttractionId(String id);
	
	List<SidoDto> sidoList();

	List<GugunDto> gugunList(String sidoCode);
	
//	List<AttractionDescriptionDto> attractionDesc(AttractionInfoDto attractionInfoDto);
	
}
