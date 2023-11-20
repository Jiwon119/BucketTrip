package com.ssafy.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionDescriptionDto;
import com.ssafy.attraction.model.AttractionInfoDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

@Mapper
public interface AttractionMapper {

	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto, String title);

	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);

	AttractionInfoDto searchById(String id);

	List<SidoDto> sidoList();

	List<GugunDto> gugunList(String sidoCode);

}
