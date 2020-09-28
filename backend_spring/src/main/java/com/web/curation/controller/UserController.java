package com.web.curation.controller;

import java.util.List;
import java.util.Optional;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.User;
import com.web.curation.service.JwtService;
import com.web.curation.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RequestMapping("account")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
	JwtService jwtService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public Object login(@RequestBody User user, HttpServletResponse res) {
            Optional<User> userOpt = userService.findByIdAndPassword(user);
            BasicResponse result = new BasicResponse(); 
        if (userOpt.isPresent()) {
            result.status = true;
            result.object = userOpt.get();
            
            // 성공하면 토큰 생성
            String token = jwtService.create(user);
            res.setHeader("jwt-auth-token", token);
            
            return new ResponseEntity<> (result, HttpStatus.OK);
        } else {
            result.status = false;
            return new ResponseEntity<> (result, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/user/searchAll")
    @ApiOperation(value = "모든 회원 조회")
    public ResponseEntity<List<User>> getAllUsers(){
            List<User> user = userService.findAll();
            //BasicResponse result = new BasicResponse(); 
            return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
    
    @GetMapping("/user/search/{id}")
    @ApiOperation(value = "단일 회원 조회")
    public ResponseEntity<User> getUser(@PathVariable String id){
    		Optional<User> user = userService.findById(id);
            //BasicResponse result = new BasicResponse(); 
    		System.out.println(user);
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }
    
    @PostMapping("/user/insert")
    @ApiOperation(value = "회원 가입")
    public Object save(User user){
    	BasicResponse result = new BasicResponse();
    	Optional<User> userOpt = userService.findById(user.getId());
    	
    	// ID가 이미 존재하지 않을때만 회원가입 가능?
    	if (!userOpt.isPresent()) {
    		result.status = true;
            result.object = userOpt.get();
    		return new ResponseEntity<>(result, HttpStatus.OK);
    	}else {
    		result.status = false;
    		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    	}
    }
    
 	@PutMapping("/user/update/{id}")
 	@ApiOperation(value = "회원 수정 (바꿀 아이디 값과 , 바꿀 값 User 값)")
 	public ResponseEntity<User> updateUser(@PathVariable String id, User user){
 		userService.updateById(id, user);
 		return new ResponseEntity<User>(user, HttpStatus.OK);
 	}
 	
 	@DeleteMapping("/user/delete/{id}")
 	@ApiOperation(value = "회원 삭제 ")
 	public ResponseEntity<Void> deleteMember(@PathVariable String id){
 		userService.deleteById(id);
 		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
 	}

 	

    
}
