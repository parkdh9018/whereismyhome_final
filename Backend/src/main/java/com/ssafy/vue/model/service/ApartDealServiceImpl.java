package com.ssafy.vue.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.vue.model.ApartDealDto;
import com.ssafy.vue.model.mapper.ApartDealMapper;

public class ApartDealServiceImpl implements ApartDealService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ApartDealDto> getAptByaptCode(Map<String, String> param) throws Exception {
		return sqlSession.getMapper(ApartDealMapper.class).getAptByaptCode(param);
	}
	

}
