package com.web.curation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.user.User;

@Service
public class UserService {
	
	@Autowired
    UserDao userDao;
	
	
	// Id, Password 확인
	public Optional<User> findByIdAndPassword(String id, String password){
		Optional<User> userOpt = userDao.findByIdAndPassword(id, password);
		return userOpt;
	}
	
	
	// 전체 회원 조회
	public List<User> findAll(){
		List<User> userList = new ArrayList<>();
		userDao.findAll().forEach(e -> userList.add(e));
		return userList;
	}
	// 단일 회원 조회
		public Optional<User> findById(String id){
			Optional<User> user = userDao.findById(id);
			return user;
	}
	// 회원 가입
	public User save(User user){
		userDao.save(user);
		return user;
	}
	
	// 회원 수정
	public void updateById(String id, User user){
		Optional<User> u = userDao.findById(id);
		if(u.isPresent()) {
			u.get().setId(user.getId());
			u.get().setPassword(user.getPassword());
			u.get().setSummoner_name(user.getSummoner_name());
			userDao.save(u.get());
		}
	}
	
	// 회원 삭제
	public void deleteById(String id){
		userDao.deleteById(id);
	}

	



}
