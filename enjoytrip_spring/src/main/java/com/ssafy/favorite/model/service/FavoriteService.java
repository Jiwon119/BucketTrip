package com.ssafy.favorite.model.service;

import java.util.List;

import com.ssafy.favorite.model.FavoriteDto;

public interface FavoriteService {
	
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	
	List<FavoriteDto> getFavoriteList(String userId) throws Exception;
	
}
