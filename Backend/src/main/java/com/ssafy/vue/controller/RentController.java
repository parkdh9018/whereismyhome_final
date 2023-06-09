package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.RentDto;
import com.ssafy.vue.model.service.BoardService;
import com.ssafy.vue.model.service.RentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/rent")
@Api("rent 컨트롤러  API V1")
public class RentController {

	private static final Logger logger = LoggerFactory.getLogger(RentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private RentService rentService;

	@ApiOperation(value = "rent 목록", notes = "모든 rent의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<RentDto>> listRent() throws Exception {
		logger.info("listRent - 호출");
		return new ResponseEntity<List<RentDto>>(rentService.listRent(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "rent 목록", notes = "모든 rent의 정보를 반환한다.", response = List.class)
	@PostMapping
	public ResponseEntity<List<RentDto>> listDongRent(@RequestBody @ApiParam(value = "rent정보를 얻기위한 부가정보.", required = true) RentDto rentDto) throws Exception {
		logger.info("listDongRent - 호출");
		String house_gbn_nm = rentDto.getHouse_gbn_nm();
		String bjdong_cd = rentDto.getBjdong_cd();
		String rent_division = rentDto.getRent_division();
		String sgg_cd = rentDto.getSgg_cd();
		return new ResponseEntity<List<RentDto>>(rentService.listDongRent(house_gbn_nm,bjdong_cd,rent_division, sgg_cd), HttpStatus.OK);
	}
	
	

	
}