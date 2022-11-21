package com.ssafy.vue.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.RentDto;
import com.ssafy.vue.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/housedeal")
@Api("housedeal 컨트롤러  API V1")
public class HouseDealController {

	private static final Logger logger = LoggerFactory.getLogger(HouseDealController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseDealService houseDealService;

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/privatehousedeal")
	public ResponseEntity<List<HousedealDto>> listArticle() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<HousedealDto>>(houseDealService.listPrivateHouseDeal(), HttpStatus.OK);
	}
	
	
	
}