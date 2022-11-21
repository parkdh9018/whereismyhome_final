package com.ssafy.vue.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.service.HouseDealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

	@ApiOperation(value = "단독다가구 목록", notes = "모든 단독다가구의 정보를 반환한다.", response = List.class)
	@GetMapping("/privatehousedeal")
	public ResponseEntity<List<HouseDealDto>> privatehousedeal() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.listPrivateHouseDeal(), HttpStatus.OK);
	}

	@ApiOperation(value = "다세대주택 목록", notes = "모든 다세대주택의 정보를 반환한다.", response = List.class)
	@GetMapping("/multiplexhousedeal")
	public ResponseEntity<List<HouseDealDto>> multiplexhousedeal() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.listMultiplexHouseDeal(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "오피스텔 목록", notes = "모든 오피스텔의 정보를 반환한다.", response = List.class)
	@GetMapping("/officetelhousedeal")
	public ResponseEntity<List<HouseDealDto>> officetelhousedeal() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.listOfiicetelHouseDeal(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동으로 검색한 단독다가구 목록", notes = "동에 해당하는 단독다가구의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/privatehousedeal/{bjdong_nm}")
	public ResponseEntity<List<HouseDealDto>> getPrivateHouseDeal(@PathVariable("bjdong_nm") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_nm) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_nm);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getPrivateHouseDeal(bjdong_nm), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동으로 검색한 다세대주택 목록", notes = "동에 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/multiplexhousedeal/{bjdong_nm}")
	public ResponseEntity<List<HouseDealDto>> getMultiplexHouseDeal(@PathVariable("bjdong_nm") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_nm) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_nm);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getMultiplexHouseDeal(bjdong_nm), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동으로 검색한 오피스텔 목록", notes = "동에 해당하는 오피스텔의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/officetelhousedeal/{bjdong_nm}")
	public ResponseEntity<List<HouseDealDto>> getOfiicetelHouseDeal(@PathVariable("bjdong_nm") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_nm) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_nm);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getOfficetelHouseDeal(bjdong_nm), HttpStatus.OK);
	}
	
}