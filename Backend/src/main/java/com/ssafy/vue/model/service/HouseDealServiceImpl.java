package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.AmtDto;
import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.DetailDto;
import com.ssafy.vue.model.HouseDealDto;
import com.ssafy.vue.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService{

	@Autowired
	private SqlSession sqlSession;

//	아파트
	@Override
	public List<HouseDealDto> getApartDeal(Map<String, Object> resultMap) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getApartDeal(resultMap);
	}
	
	@Override
	public List<HouseDealDto> getApartDealDetail(Map<String, Object> resultMap) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getApartDealDetail(resultMap);
	}
	
	@Override
	public DetailDto getApartAvg(String sgdbb_cd) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getApartAvg(sgdbb_cd);
	}
	
	@Override
	public List<AmtDto> getAmt(String sgdbb_cd) throws SQLException{
		return sqlSession.getMapper(HouseDealMapper.class).getAmt(sgdbb_cd);
	}



//	다세대주택
	@Override
	public List<HouseDealDto> getMultiplexHouseDeal(Map<String, Object> resultMap) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDeal(resultMap);
	}
	@Override
	public List<HouseDealDto> getMultiplexHouseDealDetail(Map<String, Object> resultMap)
			throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDealDetail(resultMap);
	}
	
	
//	오피스텔
	
	@Override
	public List<HouseDealDto> getOfficetelDeal(Map<String, Object> resultMap) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelDeal(resultMap);
	}
	@Override
	public List<HouseDealDto> getOfficetelDealDetail(Map<String, Object> resultMap) throws SQLException {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelDealDetail(resultMap);
	}




	


	




	

	
	
}
