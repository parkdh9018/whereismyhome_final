package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.RentDto;

@Mapper
public interface RentMapper {

	public List<RentDto> listRent();
	public List<RentDto> listDongRent(String house_gbn_nm, String bjdong_cd, String rent_division, String sgg_cd);


}
