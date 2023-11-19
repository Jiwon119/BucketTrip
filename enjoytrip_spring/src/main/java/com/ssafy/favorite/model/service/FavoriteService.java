package com.ssafy.favorite.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.CommentDto;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.util.PageNavigation;

public interface FavoriteService {
	
	List<FavoriteDto> getList(String userId);
	
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	
}
