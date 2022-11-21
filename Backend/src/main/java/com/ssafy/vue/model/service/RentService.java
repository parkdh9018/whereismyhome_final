package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.RentDto;

public interface RentService {

	List<RentDto> listRent() throws Exception;
	List<RentDto> listDongRent(String house_gbn_nm, String bjdong_cd, String rent_division, String sgg_cd);

}
