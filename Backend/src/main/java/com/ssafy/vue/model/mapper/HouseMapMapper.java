package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.DongCodeDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getAptInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptNearbyDong(Map<String, String> param) throws SQLException;
	SidoGugunCodeDto getGuguncodeByName(Map<String, String> param) throws SQLException;
	List<DongCodeDto> selectnearbydongBydong(Map<String, String> param) throws SQLException;
	
}
