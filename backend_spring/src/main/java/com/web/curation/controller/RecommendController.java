package com.web.curation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.net.URLEncoder;


import com.web.curation.dao.user.RecommendDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.lol.Champion;
import com.web.curation.model.lol.Item;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RestController
public class RecommendController {

	@Autowired
	RecommendDao recommendDao;

	@GetMapping("/recommend/champion")
	@ApiOperation(value = "user가 플레이한 챔피언 성향에 따른 추천")
	public Object recommendChampion(@RequestParam String summonerName, @RequestParam int type) throws IOException {
		String recommend = null;
		summonerName = summonerName.replaceAll(" ","");
		summonerName = URLEncoder.encode(summonerName, "UTF-8");
		System.out.println(summonerName);
		if (type == 0) { // 숙련도
			recommend = recommendDao.recommendPoint(summonerName);
		} else if (type == 1) { // 챔피언 매치기록

		}
		if (recommend != null)
			return new ResponseEntity<>(recommend, HttpStatus.OK);
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/recommend/mbti")
	@ApiOperation(value = "user MBTI 성향에 따른 챔피언 추천")
	public Object recommendChampionByMBTI(@RequestParam String summonerName, @RequestParam String mbti) {
		List<Champion> champList = new ArrayList<>();
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.object = champList;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/recommend/item")
	@ApiOperation(value = "챔피언 매치에 따른 아이템 템트리 추천")
	public Object recommendItem(@RequestParam String myChampion, @RequestParam String opponentChampion) {
		List<Item> itemList = new ArrayList<>();
		BasicResponse result = new BasicResponse();

		result.status = true;
		result.object = itemList;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/update/gamedata")
	@ApiOperation(value = "갱신하기 버튼 클릭 이벤트")
	public Object renewalGameData(@RequestParam String summonerName) throws IOException {
		boolean recommend = false;
		summonerName = summonerName.replaceAll(" ","");
		summonerName = URLEncoder.encode(summonerName, "UTF-8");
		recommend = recommendDao.renewalPoint(summonerName);

		if (recommend != false)
			return new ResponseEntity<>(recommend, HttpStatus.OK);
		return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/test")
	@ApiOperation(value = "test")
	public Object test() throws IOException {
		String res = recommendDao.test();
		BasicResponse result = new BasicResponse();
		result.data = res;
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
}
