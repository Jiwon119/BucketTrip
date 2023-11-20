package com.ssafy.favorite.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.mapper.FavoriteMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	public List<FavoriteDto> getList(String userId) {
		return favoriteMapper.getList(userId);
	}

	@Override
	public void removeFavorite(FavoriteDto favoriteDto) throws Exception {
		favoriteMapper.removeFavorite(favoriteDto);
		
	}

	@Override
	public boolean isFavorited(String userId, int destinationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId); map.put("destinationId", destinationId);
		return favoriteMapper.isFavorited(map);
	}
	
}
