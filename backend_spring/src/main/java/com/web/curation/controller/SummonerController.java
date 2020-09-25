package com.web.curation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.service.SummonerService;

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
public class SummonerController {

	@Autowired
	SummonerService summonerService;
	
	@GetMapping("/summoner/recentchamp")
	@ApiOperation(value = "summoner 최근 챔프 기록 가져오기 ")
	public List<Map<String,String>> getSummonerRecentChamp(@RequestParam String summonerName) {
		try {
			summonerName = summonerName.replaceAll(" ","%20");
			String[] ids = summonerService.findRiotIdBySummonerName(summonerName);
			String accountId = ids[1];
			List<Map<String,String>> recentChamp = summonerService.findMatchListByAccountId(accountId);
			return recentChamp;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@GetMapping("/summoner/leagueinfo")
	@ApiOperation(value = "summoner league 정보 가져오기 ")
	public Map<String, String> getSummonerRankInfo(@RequestParam String summonerName) {

		try {
			summonerName = summonerName.replaceAll(" ","%20");
			String[] ids = summonerService.findRiotIdBySummonerName(summonerName);
			String id = ids[0];
			return summonerService.findLeagueInfoById(id);  //ids 인덱스 0번은 id , 1번은 accountId
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
}
