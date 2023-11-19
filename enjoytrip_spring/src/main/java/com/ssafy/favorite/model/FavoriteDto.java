package com.ssafy.favorite.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 디폴트 생성자 생성
@ApiModel(value = "FavoriteDto (즐겨찾기 정보)")
public class FavoriteDto {
	private int id;
	private String memberId;
	private String destinationId;
}
