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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.AmtDto;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.DetailDto;
import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.MarkerApartDeal;
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
	
	
//	아파트
	@ApiOperation(value = "아파트 동별 목록", notes = "아파트 동별 정보를 반환한다.", response = List.class)
	@GetMapping("/apart")
	public ResponseEntity<List<HouseDealDto>> getApartDeal(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getApartDeal - 호출 : ");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "sgd_cd", param.get("sgd_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getApartDeal(resultMap), HttpStatus.OK);
	}
	

	
	
	@ApiOperation(value = "아파트 상세조회 목록", notes = "정확히 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/apartDetail")
	public ResponseEntity<DetailDto> getApartDealDetail(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getApartDealDetail - 호출 : " );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "sgdbb_cd", param.get("sgdbb_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		List<HouseDealDto> dto1=houseDealService.getApartDealDetail(resultMap);
		
		DetailDto detailDto = new DetailDto(); 
		detailDto.setHousedealDto(dto1);
		
		DetailDto temp = houseDealService.getApartAvg(param.get("sgdbb_cd"));
		detailDto.setAvg_amt(temp.getAvg_amt());
		detailDto.setMax_amt(temp.getMax_amt());
		detailDto.setMax_gtn(temp.getMax_gtn());
		detailDto.setAvg_gtn(temp.getAvg_gtn());
		
		List<AmtDto> temp2 = houseDealService.getAmt(param.get("sgdbb_cd"));
		detailDto.setAmtDto(temp2);
		return new ResponseEntity<DetailDto>(detailDto, HttpStatus.OK);
	}
	
//	다세대주택
	@ApiOperation(value = "동으로 검색한 다세대주택 목록", notes = "동에 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/multiplex")
	public ResponseEntity<List<HouseDealDto>> getMultiplexHouseDeal(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : ");
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("di");
		resultMap.put( "sgd_cd", param.get("sgd_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getMultiplexHouseDeal(resultMap), HttpStatus.OK);

	}
	
	@ApiOperation(value = "Detail 다세대주택 목록", notes = "정확히 해당하는 다세대주택의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/multiplexDetail")
	public ResponseEntity<List<HouseDealDto>> getMultiplexHouseDealDetail(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getApartDealDetail - 호출 : " );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "sgdbb_cd", param.get("sgdbb_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getMultiplexHouseDealDetail(resultMap), HttpStatus.OK);
	}
	
	
//	오피스텔
	@ApiOperation(value = "동으로 검색한 오피스텔 목록", notes = "동에 해당하는 오피스텔의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/officetel")
	public ResponseEntity<List<HouseDealDto>> getOfficetelDeal(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getPrivateHouseDeal - 호출 : ");
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println("di");
		resultMap.put( "sgd_cd", param.get("sgd_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getOfficetelDeal(resultMap), HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Detail 오피스텔 목록", notes = "정확히 해당하는 오피스텔의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/officetelDetail")
	public ResponseEntity<List<HouseDealDto>> getOfficetelDealDetail(@RequestParam HashMap<String, String> param) throws Exception {
		logger.info("getApartDealDetail - 호출 : " );
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put( "sgdbb_cd", param.get("sgdbb_cd") );
		if(param.containsKey("sell")) {
			resultMap.put("sell", param.get("sell"));
		}
		if(param.containsKey("year")) {
			resultMap.put("year", param.get("year"));
		}
		if(param.containsKey("month")) {
			resultMap.put("month", param.get("month"));
		}
		return new ResponseEntity<List<HouseDealDto>>(houseDealService.getOfficetelDealDetail(resultMap), HttpStatus.OK);
	}
	
	@GetMapping("/markerApt/{aptCode}")
	public ResponseEntity<List<MarkerApartDeal>> getMarkerApartList(@PathVariable("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<List<MarkerApartDeal>>(houseDealService.getMarkerApartDeal(aptCode), HttpStatus.OK);
	}
	
	
}