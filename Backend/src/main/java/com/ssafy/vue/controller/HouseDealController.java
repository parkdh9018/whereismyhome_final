package com.ssafy.vue.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.ApartDealDto;
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
	
	@ApiOperation(value = "아파트 상세조회", notes = "아파트 상세조회 정보를 반환한다.", response = List.class)
	@GetMapping("/apartdeal/{aptCode}")
	public ResponseEntity<List<ApartDealDto>> getApartDealByaptCode(@PathVariable("aptCode") String aptCode) throws Exception {
		System.out.println(aptCode);
		return new ResponseEntity<List<ApartDealDto>>(houseDealService.getAptByaptCode(aptCode),HttpStatus.OK);
	}
	
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
	@GetMapping("/privatehousedeal/{sgg_cd}/{bjdong_cd}")
	public ResponseEntity<List<HouseDealDto>> getPrivateHouseDeal(@PathVariable("bjdong_cd") @ApiParam(value = "얻어올 동코드.", required = true) String bjdong_cd, @PathVariable("sgg_cd") @ApiParam(value = "얻어올 시구군 코드", required = true) String sgg_cd) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_cd);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getPrivateHouseDeal(sgg_cd, bjdong_cd ), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동으로 검색한 다세대주택 목록", notes = "동에 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/multiplexhousedeal/{sgg_cd}/{bjdong_cd}")
	public ResponseEntity<List<HouseDealDto>> getMultiplexHouseDeal(@PathVariable("bjdong_cd") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_cd, @PathVariable("sgg_cd") @ApiParam(value = "얻어올 시구군 코드", required = true) String sgg_cd) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_cd);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getMultiplexHouseDeal(sgg_cd, bjdong_cd), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동으로 검색한 다세대주택 목록", notes = "동에 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/multiplexhousedealdetail/{sgg_cd}/{bjdong_cd}/{bubn}")
	public ResponseEntity<List<HouseDealDto>> getMultiplexHouseDealDetail(@PathVariable("bjdong_cd") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_cd, @PathVariable("sgg_cd") @ApiParam(value = "얻어올 시구군 코드", required = true) String sgg_cd, @PathVariable("bubn") @ApiParam(value = "얻어올 글의 부번.", required = true) String bubn) throws Exception {
		logger.info("getMultiplexHouseDealDetail - 호출 : " );
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getMultiplexHouseDealDetail(sgg_cd, bjdong_cd, bubn), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "동으로 검색한 오피스텔 목록", notes = "동에 해당하는 오피스텔의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/officetelhousedeal/{sgg_cd}/{bjdong_cd}")
	public ResponseEntity<List<HouseDealDto>> getOfiicetelHouseDeal(@PathVariable("bjdong_cd") @ApiParam(value = "얻어올 글의 동이름.", required = true) String bjdong_cd, @PathVariable("sgg_cd") @ApiParam(value = "얻어올 시구군 코드", required = true) String sgg_cd) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : " + bjdong_cd);
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getOfficetelHouseDeal(sgg_cd, bjdong_cd), HttpStatus.OK);
	}
	
	
	

	

	
}