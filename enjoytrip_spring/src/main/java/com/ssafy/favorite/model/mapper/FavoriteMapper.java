package com.ssafy.favorite.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favorite.model.FavoriteDto;

@Mapper
public interface FavoriteMapper {

	List<FavoriteDto> getList(String userId);
	
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	
}
