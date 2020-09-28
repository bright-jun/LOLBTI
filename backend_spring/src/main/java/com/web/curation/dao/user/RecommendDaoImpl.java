package com.web.curation.dao.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.web.curation.model.lol.Champion;

public class RecommendDaoImpl implements RecommendDao {
    static String root = "http://localhost:8081";
    // static String root = "http://j3a109.p.ssafy.io:8081";

    @Override
    public boolean renewalPoint(String summonerName) throws IOException {
        String request = "/update/point?summonerName=" + summonerName;
        String requestUrl = root + request;
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();

        if (result.toString().equals("success"))
            return true;

        return false;
    }

    @Override
    public List<Champion> recommendPoint(String summonerName) throws IOException {
        String request = "/recommend/point?summonerName=" + summonerName;
        String requestUrl = root + request;
        URL url = new URL(requestUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();

        String[] champions = result.toString().split(" ");
        List<Champion> champList = new ArrayList<>();
        for(int i = 0; i < champions.length; i++){
            Champion champ = new Champion();
            champ.setChapionName(champions[i]);
            champList.add(champ);
        }

        return champList;
    }
    
}
