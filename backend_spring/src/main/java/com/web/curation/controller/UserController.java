package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.web.curation.dao.user.MbtiDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.Idpass;
import com.web.curation.model.user.Mbti;
import com.web.curation.model.user.User;
import com.web.curation.service.JwtService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RequestMapping("account")
@RestController
public class UserController {

	@Autowired
	MbtiDao mbtiDao;

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;

	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestBody Idpass idpass, HttpServletResponse res) {
		Optional<User> userOpt = userService.findByIdAndPassword(idpass.getId(), idpass.getPassword());
		BasicResponse result = new BasicResponse();
		if (userOpt.isPresent()) {
			result.status = true;
			result.object = userOpt.get();
			// 성공하면 토큰 생성
			String token = jwtService.create(userOpt.get());
			res.setHeader("jwt-auth-token", token);

			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/searchAll")
	@ApiOperation(value = "모든 회원 조회")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> user = userService.findAll();
		// BasicResponse result = new BasicResponse();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@GetMapping("/user/search")
	@ApiOperation(value = "단일 회원 조회")
	public Object getUser(@RequestParam("id") String id) {
		Optional<User> user = userService.findById(id);
		BasicResponse result = new BasicResponse();
		if(user.isPresent()){
			result.status = true;
			Optional<Mbti> mbti = mbtiDao.findById(user.get().getSummonerName());
			Map<String,String> map = new HashMap<String,String>();
			map.put("summonerName",user.get().getSummonerName());
			map.put("mbti",mbti.get().getMbti());
			result.data=map.toString();
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else{
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/user/join")
	@ApiOperation(value = "회원 가입")
	public Object save(@RequestBody User user, @RequestParam(required = false) String mbti) {
		BasicResponse result = new BasicResponse();
		Optional<User> userOpt = userService.findById(user.getId());

		// ID가 이미 존재하지 않을때만 회원가입 가능하다
		if (!userOpt.isPresent()) {
			result.status = true;
			// 소환사 명을 입력한 경우에만 소환사,mbti 추가한다(일단 공백으로 했음 , null이면 바꿀 예정)
			if (user.getSummonerName()!=null && !user.getSummonerName().equals("") && mbti != null) {
				// Mbti 추가 로직 구현해주세요
				String summonerName = user.getSummonerName();
				summonerName = summonerName.replaceAll(" ","");
				Mbti userMbti = new Mbti(summonerName, mbti);
				mbtiDao.save(userMbti);
			}
			userService.save(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result.status = false;
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/user/update")
	@ApiOperation(value = "회원 수정 (바꿀 아이디 값과 , 바꿀 값 User 값)")
	public ResponseEntity<User> updateUser(@RequestParam("summonerName") String summonerName, @RequestParam("id") String id,@RequestParam("mbti") String mbti) {
		Optional<User> user = userService.findById(id);
		user.get().setSummonerName(summonerName);
		userService.updateById(id, user.get());
		// Mbti 수정 로직 구현해주세요
		Mbti userMbti = new Mbti(summonerName,mbti);
		mbtiDao.save(userMbti);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{id}")
	@ApiOperation(value = "회원 삭제 ")
	public ResponseEntity<Void> deleteMember(@PathVariable String id) {
		userService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/token")
	@ApiOperation(value = "인터셉트로 유효성 확인 후 토큰 재갱신")
	public Object getInfo(HttpServletRequest req, @RequestParam("id") String id, HttpServletResponse res) {

		BasicResponse result = new BasicResponse();
		HttpStatus status = null;
		try {
			Optional<User> user = userService.findById(id);
			String token = jwtService.create(user.get());

			// 토큰 정보는 request의 헤더로 보내고 나머지는 result에 담아주자
			res.setHeader("jwt-auth-token", token);
			result.status = true;
			result.object = user.get();
			status = HttpStatus.OK;
		} catch (RuntimeException e) {
			result.status = false;
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<>(result, status);
	}

	@GetMapping("/user/searchmbti")
	@ApiOperation(value = "소환사명으로 mbti 조회")
	public ResponseEntity<Mbti> getMbtiBySummonerName(@RequestParam String summonerName) {
		summonerName = summonerName.replaceAll(" ", "");
		Optional<Mbti> mbti = mbtiDao.findById(summonerName);
		// BasicResponse result = new BasicResponse();
//    		System.out.println(mbti);
		return new ResponseEntity<Mbti>(mbti.get(), HttpStatus.OK);
	}

}
