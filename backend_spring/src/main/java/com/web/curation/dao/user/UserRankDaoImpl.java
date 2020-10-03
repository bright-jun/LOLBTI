package com.web.curation.dao.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Repository;

@Repository
public class UserRankDaoImpl implements UserRankDao {
<<<<<<< HEAD
    static String root = "http://localhost:8081";
    // static String root = "http://j3a109.p.ssafy.io:8081";
=======
//    static String root = "http://localhost:8081";
    static String root = "http://j3a109.p.ssafy.io:8081";
>>>>>>> bf605a7ef42c400d84165c9cfc555ae2c9e76ffd

    @Override
    public String userRank(String summonerName) throws IOException {
        String request = "/userGameData/userinfo/";
<<<<<<< HEAD
        String requestUrl = root + request + summonerName;
        URL url = new URL(requestUrl);
=======
        String requestUrl = root + request;
        // String summon= URLEncoder.encode(summonerName, "UTF-8");
        // URL url = new URL(requestUrl+summon);
        URL url = new URL(requestUrl+summonerName);
>>>>>>> bf605a7ef42c400d84165c9cfc555ae2c9e76ffd
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

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
