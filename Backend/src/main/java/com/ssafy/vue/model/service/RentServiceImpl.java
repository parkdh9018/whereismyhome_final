package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.RentDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.RentMapper;

@Service
public class RentServiceImpl implements RentService {

	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public List<RentDto> listRent() throws Exception{
		return sqlSession.getMapper(RentMapper.class).listRent();
	}


	@Override
	public List<RentDto> listDongRent(String house_gbn_nm, String bjdong_cd, String rent_division, String sgg_cd) {
		return sqlSession.getMapper(RentMapper.class).listDongRent(house_gbn_nm, bjdong_cd, rent_division, sgg_cd);
	}
	
}
