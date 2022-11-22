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

//	아파트
	@Override
	public List<ApartDealDto> getAptByaptCode(String aptCode) throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).getAptByaptCode(aptCode);
	}
	
	
//	다세대주택
	@Override
	public List<HouseDealDto> listMultiplexHouseDeal() throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).listMultiplexHouseDeal();
	}

	@Override
	public List<HouseDealDto> getMultiplexHouseDeal(String sgdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDeal(sgdong_cd);
	}

	@Override
	public List<HouseDealDto> getMultiplexHouseDealDetail(String multiplexhouseCode)
			throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDealDetail(multiplexhouseCode);
	}
	
	
//	오피스텔
	@Override
	public List<HouseDealDto> listOfiicetelHouseDeal() throws Exception {
		return sqlSession.getMapper(HouseDealMapper.class).listOfficetelHouseDeal();
	}
	
	@Override
	public List<HouseDealDto> getOfficetelHouseDeal(String sgdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelHouseDeal(sgdong_cd);
	}
	
	@Override
	public List<HouseDealDto> getOfficetelHouseDealDetail(String officetelhouseCode) throws SQLException {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelHouseDealDetail(officetelhouseCode);
	}
	

//	단독주택
	@Override
	public List<HouseDealDto> getPrivateHouseDeal(String sgg_cd, String bjdong_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getPrivateHouseDeal(sgg_cd,bjdong_cd);
	}
	
	@Override
	public List<HouseDealDto> listPrivateHouseDeal(){
		return sqlSession.getMapper(HouseDealMapper.class).listPrivateHouseDeal();
	}


	

	
	
}
