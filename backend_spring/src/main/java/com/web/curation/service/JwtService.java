package com.web.curation.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.web.curation.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtService {
	
	// 솔트값
	@Value("${jwt.salt}")
	private String salt;
	
	// 생명주기(분단위)
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	/**
	 * 로그인 성공시 사용자 정보를 기반으로 JWTTOKEN을 생성해서 반환 한다.
	 * @param user
	 * @return
	 */
	public String create(final User user) {
		log.trace("time: {}", expireMin);
		final JwtBuilder builder = Jwts.builder();
		// JWT token = header(헤더) + Payload(내용) + Sign(서명)
		// Header 설정
		builder.setHeaderParam("typ", "JWT"); // 토근의 타입으로 고정값
		
		//payload(토큰에 담을 정보) 설정 - claim 정보 포함(정보 한조각을 claim이라고 한다)
		builder.setSubject("로그인토큰") // 토큰 제목 설정
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*expireMin))
				.claim("User",user);
		
		// signature - secret Key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		final String jwt = builder.compact();
		log.debug("토큰 발행: {}",jwt);
		return jwt;
	}
	
	/**
	 * 전달받은 토큰이 제대로 생성된 것인지 확인하고 문제가 있다면 Runtime에러를 발생시킨다
	 * @param jwt
	 */
	public void checkVaild(final String jwt) {
		log.trace("토큰 점검: {}",jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	/**
	 * jwt 토큰을 분석해서 필요한 정보를 반환한다
	 * @param jwt
	 * @return
	 */
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch(final Exception e){
			throw new RuntimeException();
		}
		log.trace("claims:{}",claims);
		return claims.getBody();
	}
}
