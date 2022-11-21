package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HouseDealDto> listPrivateHouseDeal(){
		return sqlSession.getMapper(HouseDealMapper.class).listPrivateHouseDeal();
	}

	@Override
	public List<HouseDealDto> listMultiplexHouseDeal() throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).listMultiplexHouseDeal();
	}

	@Override
	public List<HouseDealDto> listOfiicetelHouseDeal() throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).listOfficetelHouseDeal();
	}

	@Override
	public List<HouseDealDto> getPrivateHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getPrivateHouseDeal(sgg_cd,bjdong_cd);
	}

	@Override
	public List<HouseDealDto> getMultiplexHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDeal(sgg_cd,bjdong_cd);
	}

	@Override
	public List<HouseDealDto> getOfficetelHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelHouseDeal(sgg_cd, bjdong_cd);
	}
	
	@Override
	public List<ApartDealDto> getAptByaptCode(String aptCode) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getAptByaptCode(aptCode);
	}

	@Override
	public List<HouseDealDto> getMultiplexHouseDealDetail(String sgg_cd, String bjdong_cd, String bubn)
			throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDealDetail(sgg_cd,bjdong_cd, bubn );
	}
	

	
	
}
