package com.ssafy.attraction.model.service;

import java.util.List;

//import com.ssafy.attraction.model.AttractionDescriptionDto;
import com.ssafy.attraction.model.AttractionInfoDto;

public interface AttractionService {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto, String title);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	
//	List<AttractionDescriptionDto> attractionDesc(AttractionInfoDto attractionInfoDto);
	
}
