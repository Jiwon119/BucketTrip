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
	
	private FavoriteMapper mapper;
	
	public FavoriteServiceImpl(FavoriteMapper favoriteMapper) {
		super();
		this.mapper = favoriteMapper;
	}

	
	@Override
	public List<FavoriteDto> getList(String userId) {
		return mapper.getList(userId);
	}
	
}
