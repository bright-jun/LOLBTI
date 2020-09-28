package com.web.curation.dao.user;

import java.util.Optional;

import com.web.curation.model.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByIdAndPassword(String id, String password);
    Optional<User> findById(String id);
    Optional<User> findBySummonerName(String summonerName);
}