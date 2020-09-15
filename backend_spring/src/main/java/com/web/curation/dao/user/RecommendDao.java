package com.web.curation.dao.user;

import java.io.IOException;

public interface RecommendDao {
    boolean renewalPoint(String summonerName) throws IOException;
}
