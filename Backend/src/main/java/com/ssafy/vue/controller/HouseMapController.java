package com.ssafy.vue.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
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

import com.ssafy.vue.model.DongCodeDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.service.HouseMapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("/code")
	public ResponseEntity<SidoGugunCodeDto> gugunByname(
			@RequestParam("sido") String sido, @RequestParam("gugun") String gugun) throws Exception {
		logger.info("gugunByname - 호출 {} {}", sido, gugun);
		return new ResponseEntity<SidoGugunCodeDto>(haHouseMapService.getGuguncodeByName(sido, gugun), HttpStatus.OK);
	}

	@ApiOperation(value = "아파트 목록", notes = "지역코드와 매매계약월을 기준으로 아파트 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
	public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd, @PathVariable("deal_ymd") String dealYmd) throws IOException {
		logger.info("sido - 호출");
		String serviceKey = "IlbdlryMGFAzaYSgZCKyBcx1LfTMuoqEL49ras3mJktcR%2BQ03hVzMreBH5jS5iIOTf%2F1vht3Wck4WtuENLCmEA%3D%3D";
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																															 */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=" + serviceKey);
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode("50", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
		urlBuilder.append(
				"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
		JSONObject json = XML.toJSONObject(sb.toString());
		String jsonStr = json.toString();

		return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<DongCodeDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<DongCodeDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("/apt/dong")
	public ResponseEntity<List<HouseInfoDto>> aptByDong(@RequestParam("code") String code) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(code), HttpStatus.OK);
	}
	
	@GetMapping("/apt/gugun/{code}")
	public ResponseEntity<List<HouseInfoDto>> aptByGugun(@PathVariable("code") String code) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInGugun(code), HttpStatus.OK);
	}
	
	@GetMapping("/apt/nearby")
	public ResponseEntity<List<HouseInfoDto>> aptNearbyDong(@RequestParam("lat1") String lat1, @RequestParam("lat2") String lat2, @RequestParam("lng1") String lng1, @RequestParam("lng2") String lng2) throws Exception {
		Map<String , String> param = new HashMap<>();
		param.put("lat1", lat1);
		param.put("lat2", lat2);
		param.put("lng1", lng1);
		param.put("lng2", lng2);
		logger.info("aptNearbyDong - 호출 {} {}", lat1, lng1);
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptNearbyDong(param), HttpStatus.OK);
	}
	
	
//	@GetMapping("/donglist/{guguncode}/{dongname}")
//	public ResponseEntity<List<DongCodeDto>> donglist(@PathVariable("guguncode") String guguncode, @PathVariable("dongname") String dongname) throws Exception {
//		Map<String , String> param = new HashMap<>();
//		param.put("guguncode", guguncode);
//		param.put("dongname", dongname);
//		return new ResponseEntity<List<DongCodeDto>>(haHouseMapService.selectnearbydongBydong(param), HttpStatus.OK);
//	}
	

}
