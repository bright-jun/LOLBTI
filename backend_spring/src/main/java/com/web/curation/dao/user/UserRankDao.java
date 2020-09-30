package com.web.curation.dao.user;

import java.io.IOException;

public interface UserRankDao {
    public String userRank(String summonerName) throws IOException;
    public String userFreqChamp(String summonerName) throws IOException;
}
