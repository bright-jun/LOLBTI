package com.web.curation.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class SummonerService {
	final static String API_KEY = "RGAPI-3cfc6308-c239-46c3-b1a5-f0aacc4fe2e8";

	// 소환사 이름으로 소환사 id 찾기 (리그정보, 최근 챔프 조회용)
	public String[] findRiotIdBySummonerName(String summonerName) {
		BufferedReader br = null;
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
					+ "?api_key=" + API_KEY;
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8")); // 여기에 문자열을 받아와라.
			String result = "";
			String line;
			while ((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
				result = result + line;
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject k = (JsonObject) jsonParser.parse(result);
			String id = k.get("id").getAsString();
			String accountId = k.get("accountId").getAsString();
			String[] ids = { id, accountId };
			return ids;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// 소환사 id로 리그 정보 조회용 (승수, 패 , 티어 등)
	public Map<String, String> findLeagueInfoById(String id) {
		BufferedReader br = null;
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + id + "?api_key="
					+ API_KEY;
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8")); // 여기에 문자열을 받아와라.
			String result = "";
			String line;
			while ((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
				result = result + line;
			}
			JsonParser jsonParser = new JsonParser();
			JsonArray league = (JsonArray) jsonParser.parse(result);
			String tier = "", leaguePoints = "", wins = "", losses = "";
			for (int i = 0; i < league.size(); i++) {
				JsonObject tmp = (JsonObject) league.get(i);
				tier = tmp.get("tier").getAsString();
				leaguePoints = tmp.get("leaguePoints").getAsString();
				wins = tmp.get("wins").getAsString();
				losses = tmp.get("losses").getAsString();
			}
			Map<String, String> leagueInfo = new HashMap<String, String>();
			leagueInfo.put("tier", tier);
			leagueInfo.put("leaguePoints", leaguePoints);
			leagueInfo.put("wins", wins);
			leagueInfo.put("losses", losses);
			return leagueInfo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// 소환사 accountId로 매치기록 조회 최근 5ㄱ10
	public List<Map<String, String>> findMatchListByAccountId(String accountId) {
		BufferedReader br = null;
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/" + accountId +"?endIndex=10&beginIndex=0" +"&api_key="
					+ API_KEY;
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8")); // 여기에 문자열을 받아와라.
			String result = "";
			String line;
			while ((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
				result = result + line;
			}
			JsonParser jsonParser = new JsonParser();
			JsonObject matches = (JsonObject) jsonParser.parse(result);
			
			JsonArray champs = matches.get("matches").getAsJsonArray();
			
			List<Map<String, String>> recentChamp = new ArrayList<Map<String, String>>();
			
			for (int i = 0; i < champs.size(); i++) {
				Map<String, String> match = new HashMap<String, String>();
				JsonObject tmp = (JsonObject) champs.get(i);
				match.put("champion",  tmp.get("champion").getAsString());
				match.put("role", tmp.get("role").getAsString());
				match.put("lane", tmp.get("lane").getAsString());
				recentChamp.add(match);
			}
			return recentChamp;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
