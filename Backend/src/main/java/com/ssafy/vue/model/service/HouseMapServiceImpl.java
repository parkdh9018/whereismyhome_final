package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.BaseAddressDto;
import com.ssafy.vue.model.DongCodeDto;
import com.ssafy.vue.model.HouseInfoDto;
import com.ssafy.vue.model.SidoGugunCodeDto;
import com.ssafy.vue.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	private SqlSession sqlSession;
	private Map<String, String> sidoAlias;
	
	@Autowired
	public HouseMapServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		this.sidoAlias = new HashMap<>();
	
		sidoAlias.put("서울", "서울특별시");
		sidoAlias.put("부산", "부산광역시");
		sidoAlias.put("대구", "대구광역시");
		sidoAlias.put("인천", "인천광역시");
		sidoAlias.put("광주", "광주광역시");
		sidoAlias.put("대전", "대전광역시");
		sidoAlias.put("울산", "울산광역시");
		sidoAlias.put("세종특별자치시", "세종특별자치시");
		sidoAlias.put("경기", "경기도");
		sidoAlias.put("강원", "강원도");
		sidoAlias.put("충북", "충청북도");
		sidoAlias.put("충남", "충청남도");
		sidoAlias.put("전북", "전라북도");
		sidoAlias.put("전남", "전라남도");
		sidoAlias.put("경상", "경상북도");
		sidoAlias.put("경북", "경상남도");
		sidoAlias.put("제주특별자치도", "제주특별자치도");
		
	
	}
	

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<DongCodeDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public SidoGugunCodeDto getGuguncodeByName(String sido, String gugun) throws Exception {
		
		Map<String, String> param = new HashMap<>();
		if(sidoAlias.containsKey(sido))
			param.put("sido", sidoAlias.get(sido));
		else
			param.put("sido", sido);
		param.put("gugun", gugun);
		
		return sqlSession.getMapper(HouseMapMapper.class).getGuguncodeByName(param);
	}


	@Override
	public List<HouseInfoDto> getAptInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInGugun(gugun);
	}


	@Override
	public List<DongCodeDto> selectnearbydongBydong(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).selectnearbydongBydong(param);
	}


	@Override
	public List<HouseInfoDto> getAptNearbyDong(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptNearbyDong(param);
	}


	@Override
	public List<BaseAddressDto> markerSido(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).markerSido(param);
	}


	@Override
	public List<BaseAddressDto> markerGugun(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).markerGugun(param);
	}


	@Override
	public List<BaseAddressDto> markerDong(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).markerDong(param);
	}




}
