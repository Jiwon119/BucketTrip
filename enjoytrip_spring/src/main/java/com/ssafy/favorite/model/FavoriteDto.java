package com.ssafy.favorite.model;

import com.ssafy.attraction.model.AttractionDescriptionDto;
import com.ssafy.attraction.model.AttractionInfoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "FavoriteDto (즐겨찾기 정보)")
public class FavoriteDto {
	
	@ApiModelProperty(value = "즐겨찾기 id")
	private int id;
	
	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	
	@ApiModelProperty(value = "장소 아이디")
	private int destinationId;
	
	// mapper에서 join 후 저장할 장소 정보
	@ApiModelProperty(value = "장소 정보")
	private AttractionInfoDto attrInfo;
	
	private AttractionDescriptionDto attrDesc;

}
