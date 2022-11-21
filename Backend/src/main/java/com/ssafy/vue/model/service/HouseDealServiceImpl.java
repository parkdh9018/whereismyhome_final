package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<HouseDealDto> getPrivateHouseDeal(String bjdong_nm) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getPrivateHouseDeal(bjdong_nm);
	}

	@Override
	public List<HouseDealDto> getMultiplexHouseDeal(String bjdong_nm) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getMultiplexHouseDeal(bjdong_nm);
	}

	@Override
	public List<HouseDealDto> getOfficetelHouseDeal(String bjdong_nm) throws SQLException {
		return sqlSession.getMapper(HouseDealMapper.class).getOfficetelHouseDeal(bjdong_nm);
	}
	
	
	
}
