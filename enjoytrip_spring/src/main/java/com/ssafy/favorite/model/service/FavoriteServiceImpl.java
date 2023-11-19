package com.ssafy.favorite.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

private FavoriteMapper favoriteMapper;
	
	public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
		super();
		this.favoriteMapper = favoriteMapper;
	}
	
	@Override
	public void addFavorite(FavoriteDto favoriteDto) throws Exception {
		favoriteMapper.addFavorite(favoriteDto);
	}

	@Override
	public List<FavoriteDto> getFavoriteList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
