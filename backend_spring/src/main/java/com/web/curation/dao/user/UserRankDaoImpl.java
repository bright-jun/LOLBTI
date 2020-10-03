package com.web.curation.dao.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Repository;

@Repository
public class UserRankDaoImpl implements UserRankDao {
//    static String root = "https://localhost:8081";
    static String root = "https://j3a109.p.ssafy.io:8081";

    @Override
    public String userRank(String summonerName) throws IOException {
        String request = "/userGameData/userinfo/";
        String requestUrl = root + request;
        // String summon= URLEncoder.encode(summonerName, "UTF-8");
        // URL url = new URL(requestUrl+summon);
        URL url = new URL(requestUrl+summonerName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        System.out.println("하이");

        String returnLine;
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();

        return result.toString();
    }
    
    @Override
    public String userFreqChamp(String summonerName) throws IOException {
        String request = "/userGameData/recommend/mastery/freqchamp/";
        String requestUrl = root + request;
        // String summon= URLEncoder.encode(summonerName, "UTF-8");
        // URL url = new URL(requestUrl+summon);
        URL url = new URL(requestUrl+summonerName);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();

        return result.toString();
    }
    @Override
    public String userFreqLane(String summonerName) throws IOException {
    	String request = "/userGameData/recommend/freqlane/";
    	String requestUrl = root + request;
        // String summon= URLEncoder.encode(summonerName, "UTF-8");
        // URL url = new URL(requestUrl+summon);
        URL url = new URL(requestUrl+summonerName);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    	
    	String returnLine;
    	StringBuffer result = new StringBuffer();
    	while ((returnLine = br.readLine()) != null) {
    		result.append(returnLine + "\n");
    	}
    	conn.disconnect();
    	
    	return result.toString();
    }
    
}
