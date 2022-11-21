package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.HousedealDto;
import com.ssafy.vue.model.RentDto;
import com.ssafy.vue.model.mapper.HouseDealMapper;
import com.ssafy.vue.model.mapper.RentMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HousedealDto> listPrivateHouseDeal(){
		return sqlSession.getMapper(HouseDealMapper.class).listPrivateHouseDeal();
	}
	
	
	
}
