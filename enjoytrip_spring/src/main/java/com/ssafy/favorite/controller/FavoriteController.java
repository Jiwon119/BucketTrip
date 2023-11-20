package com.ssafy.favorite.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.service.FavoriteService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.ParameterCheck;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/favorite")
@CrossOrigin("*")
@Slf4j
@Api(tags = {"Favorite Controller  API V1"})
public class FavoriteController {
	private FavoriteService favoriteService;

	public FavoriteController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}
	
	@ApiOperation(value = "favorite", notes = "즐겨찾기 리스트")
	@GetMapping("/list/{userId}")
	protected ResponseEntity<?> listFavorite(
			 @PathVariable String userId) throws Exception {
		System.out.println(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		List<FavoriteDto> list = favoriteService.getList(userId);
		
		log.debug("Favorite List : {}", list);
		map.put("favoriteList", list);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "즐겨찾기 추가 및 삭제", notes = "즐찾 추가")
	@PostMapping("/favoriteAdd")
	public ResponseEntity<?> addFavorite(
			@RequestBody 	@ApiParam(value = "즐겨찾기 정보.", required = true) FavoriteDto favoriteDto,
	        @RequestParam(value = "isFavorite", required = false) boolean isFavorite
	) {
	    try {
	        log.debug(favoriteDto.toString());
	        log.debug("{} {} {}", favoriteDto.getDestinationId(), favoriteDto.getUserId(), isFavorite);

	        if (isFavorite) {
	            favoriteService.addFavorite(favoriteDto);
	            return new ResponseEntity<Void>(HttpStatus.CREATED);
	        }
	        else {
	        	favoriteService.removeFavorite(favoriteDto);
	        	return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	        }
	    } catch (Exception e) {
	        return exceptionHandling(e);
	    }
	}
	
	@ApiOperation(value = "즐겨찾기 여부 확인", notes = "즐겨찾기 여부 확인")
    @GetMapping("/isFavorited")
    public ResponseEntity<?> isFavorited(
    		@RequestParam(value = "userId", required = false) String userId,
    		@RequestParam(value = "destinationId", required = false) int destinationId
            ) {
        try {
        	log.debug("{} {}", userId, destinationId);
            boolean isFavorited = favoriteService.isFavorited(userId, destinationId);
            return new ResponseEntity<>(isFavorited, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
