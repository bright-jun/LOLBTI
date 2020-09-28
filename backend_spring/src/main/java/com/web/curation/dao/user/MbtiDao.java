package com.web.curation.dao.user;

import com.web.curation.model.user.Mbti;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiDao extends JpaRepository<Mbti, String> {
    
}