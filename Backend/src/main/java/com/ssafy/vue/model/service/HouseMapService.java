package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.model.BaseAddressDto;
import com.ssafy.vue.model.CountDto;
import com.ssafy.vue.model.DongCodeDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<DongCodeDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptInGugun(String dong) throws Exception;
	SidoGugunCodeDto getGuguncodeByName(String sido, String gugun) throws Exception;
	List<DongCodeDto> selectnearbydongBydong(Map<String, String> param) throws Exception;
	List<HouseInfoDto> getAptNearbyDong(Map<String, String> param) throws Exception;
	
	List<BaseAddressDto> markerSido(Map<String, String> param) throws Exception;
	List<BaseAddressDto> markerGugun(Map<String, String> param) throws Exception;
	List<BaseAddressDto> markerDong(Map<String, String> param) throws Exception;
	List<HouseInfoDto> searchGugunAvg(Map<String, String> param)throws Exception;
	List<HouseInfoDto> searchSidoAvg(Map<String, String> param) throws Exception;
	List<HouseInfoDto> searchDongAvg(Map<String, String> param)throws Exception;
	List<CountDto> countsido()throws Exception;;
	
}
