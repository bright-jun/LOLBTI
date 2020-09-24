package com.web.curation.dao.user;

import java.io.IOException;
import java.util.List;

import com.web.curation.model.lol.Champion;

public interface RecommendDao {
    boolean renewalPoint(String summonerName) throws IOException;
    List<Champion> recommendPoint(String summonerName) throws IOException;
}