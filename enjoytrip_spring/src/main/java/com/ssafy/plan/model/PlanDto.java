package com.ssafy.plan.model;

import java.util.List;

import com.ssafy.board.model.FileInfoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "FavoriteDto (여행 계획 정보)")
public class PlanDto {
	
	@ApiModelProperty(value = "id")
	private int id;
	
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	
	@ApiModelProperty(value = "제목")
	private String title;
	
	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "작성일")
	private String creationDate;

}
