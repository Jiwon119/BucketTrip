package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@Slf4j
@Api(tags = { "Member Controller  API V1" })
public class MemberController {

	private MemberService memberService;
	private JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@ApiOperation(value = "regist", notes = "회원가입")
	@PostMapping("/regist")
	protected ResponseEntity<?> regist(@RequestBody MemberDto member) throws Exception {
		System.out.println(member);
		Map<String, String> map = new HashMap<String, String>();
		memberService.regist(member);
		map.put("msg", "회원가입 성공");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "login", notes = "로그인")
	@PostMapping("/login")
	protected ResponseEntity<?> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		Map<String, String> resultMap = new HashMap<String, String>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.login(memberDto.getId(), memberDto.getPassword());
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getPassword());

				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

				log.debug("loginUser: {}", loginUser);
//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getId(), refreshToken);
				log.debug("토근 저장");

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;

			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "userInfo", notes = "유저 정보 페이지")
	@GetMapping("/info/{userId}")
	public ResponseEntity<MemberDto> userInfo(@PathVariable("userId") String id) throws Exception {
		MemberDto member = memberService.selectMember(id);
		return new ResponseEntity<MemberDto>(member, HttpStatus.OK);
	}
	
	@ApiOperation(value = "findUser", notes = "유저 검색")
	@PostMapping("/info")
	public ResponseEntity<List<MemberDto>> findUser(@RequestBody MemberDto member) throws Exception {
		List<MemberDto> user = memberService.selectMembers(member);
		return new ResponseEntity<List<MemberDto>>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "deleteMember", notes = "유저 탈퇴")
	@DeleteMapping("/delete/{userId}")
	protected ResponseEntity<?> deleteMember(HttpSession session, @PathVariable("userId") String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		log.debug("gdgdgd");
		memberService.deleteMember(id);
		map.put("msg", "회원 탈퇴 성공");
		session.invalidate();

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "logout", notes = "로그아웃")
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.debug(id);
			memberService.deleRefreshToken(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "searchPass", notes = "비밀번호 찾기_회원 정보 일치 검사")
	@PostMapping("/searchPass")
	private ResponseEntity<?> searchPass(@RequestParam("id") String id, @RequestParam("email") String email,
			@RequestParam("name") String name) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		MemberDto member = memberService.searchPass(id, email, name);
		if (member != null) {
			map.put("member", member);
			map.put("msg", "회원정보 일치");
		} else {
			map.put("msg", "회원정보 없음");
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "updateMember", notes = "회원 정보 수정")
	@PutMapping("/updateMember")
	private ResponseEntity<?> updateMember(@RequestBody MemberDto member) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(member);
		int state = memberService.updateMember(member);
		if (state == 1) {
			map.put("msg", "회원정보 수정 성공");
		} else {
			map.put("msg", "회원정보 수정 실패");
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "selectAllMember", notes = "회원 조회")
	@GetMapping("/selectAllMember")
	private ResponseEntity<?> selectAllMember() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MemberDto> members = memberService.selectAllMember();
		map.put("members", members);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@ApiOperation(value = "getFriends", notes = "친구 조회")
	@GetMapping("/friend/{userId}/{status}")
	private ResponseEntity<?> getFriends(@PathVariable("userId") String id,
			@PathVariable("status") String status) throws Exception {
		List<MemberDto> friends = memberService.getFriends(id, status);

		return new ResponseEntity<>(friends, HttpStatus.OK);
	}
	
	@ApiOperation(value = "getFriends", notes = "친구 조회")
	@GetMapping("/userfriend/{userId}/")
	private ResponseEntity<?> getUserFriends(@PathVariable("userId") String id) throws Exception {
		List<MemberDto> friends = memberService.getUserFriends(id);

		return new ResponseEntity<>(friends, HttpStatus.OK);
	}
	
	@ApiOperation(value = "requestFriend", notes = "친구 요청 조회")
	@GetMapping("/requestFriend/{userId}")
	private ResponseEntity<?> getFriendsRequest(@PathVariable("userId") String id) throws Exception {
		List<MemberDto> request = memberService.getFriendsRequest(id);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@ApiOperation(value = "addFriend", notes = "친구 추가")
	@GetMapping("/addFriend/{userId}/{friend}")
	private ResponseEntity<?> addFriend(@PathVariable("userId") String id,
			@PathVariable("friend") String friend) throws Exception {
		System.out.println(id + friend);
		memberService.addFriends(id, friend, 0);

		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	@ApiOperation(value = "addFriend", notes = "친구 수락")
	@GetMapping("/acceptFriendRequest/{userId}/{friend}")
	private ResponseEntity<?> acceptFriendRequest(@PathVariable("userId") String id,
			@PathVariable("friend") String friend) throws Exception {
		memberService.addFriends(id, friend, 1);
		memberService.updateFriendState(friend, id);
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}