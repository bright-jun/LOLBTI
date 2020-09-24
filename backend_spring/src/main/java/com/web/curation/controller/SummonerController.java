package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.lol.Champion;
import com.web.curation.model.lol.Item;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

// @CrossOrigin(origins = { "https://i3a310.p.ssafy.io:80", "http://localhost:3000" })
@CrossOrigin(origins = { "*" })
@RestController
public class SummonerController {
    
    @GetMapping("/summoner/recentchamp")
    @ApiOperation(value = "summoner 정보 가져오기 ")
    public Object recommendChampion(@RequestParam String summonerName) {
     
    	return null;
    }
}
