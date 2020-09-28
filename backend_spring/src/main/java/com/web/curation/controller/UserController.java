package com.web.curation.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Object login(@RequestParam("id") String id, @RequestParam("password")  String password, HttpServletResponse res) {
            Optional<User> userOpt = userService.findByIdAndPassword(id, password);
            BasicResponse result = new BasicResponse(); 
        if (userOpt.isPresent()) {
            result.status = true;
            result.object = userOpt.get();
      
            System.out.println(userOpt.get());
            // 성공하면 토큰 생성
            String token = jwtService.create(userOpt.get());
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
    
    @PostMapping("/user/join")
    @ApiOperation(value = "회원 가입")
    public Object save(@RequestParam("id") String id, @RequestParam("password") String password,
    		@RequestParam("summoner_name") String summoner_name,
    		@RequestParam("mbti") String mbti){
    	BasicResponse result = new BasicResponse();
    	Optional<User> userOpt = userService.findById(id);
    	
    	// ID가 이미 존재하지 않을때만 회원가입 가능하다
    	if (!userOpt.isPresent()) {
    		result.status = true;
    		// 소환사 명을 입력한 경우에만 소환사,mbti 추가한다(일단 공백으로 했음 , null이면 바꿀 예정)
    		if(summoner_name.equals("")) {
    			User user = userService.save(new User(id,password,summoner_name));
    			// Mbti 추가 로직 구현해주세요
    			/*
    			 * 
    			 */
    		}else {
    			User user = userService.save(new User(id,password,null));
    		}
    		return new ResponseEntity<>(result, HttpStatus.OK);
    	}else {
    		result.status = false;
    		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    	}
    }
    
 	@PutMapping("/user/update")
 	@ApiOperation(value = "회원 수정 (바꿀 아이디 값과 , 바꿀 값 User 값)")
 	public ResponseEntity<User> updateUser(User user){
 		userService.updateById(user.getId(), user);
 	   // Mbti 수정 로직 구현해주세요
		/*
		 * 
		 */
 		return new ResponseEntity<User>(user, HttpStatus.OK);
 	}
 	
 	@DeleteMapping("/user/delete/{id}")
 	@ApiOperation(value = "회원 삭제 ")
 	public ResponseEntity<Void> deleteMember(@PathVariable String id){
 		userService.deleteById(id);
 		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
 	}

}
