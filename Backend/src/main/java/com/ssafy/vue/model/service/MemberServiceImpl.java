package com.ssafy.vue.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.PasswordDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto memberInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).memberInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

	@Override
	public int idCheck(String userid) throws Exception{
		System.out.println(userid);
		int res =sqlSession.getMapper(MemberMapper.class).idCheck(userid);
		return res;
	}
	
	
	@Override
	public MemberDto idFind(MemberDto memberDto)throws Exception{
		return sqlSession.getMapper(MemberMapper.class).idFind(memberDto);
	}
	
	@Override
	public int idFindCheck(String email)throws Exception{
		return sqlSession.getMapper(MemberMapper.class).idFindCheck(email);
	}

	@Override
	public int passwordFind(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).passwordFind(memberDto);
	}

	@Override
	public void passwordUpdate(String userid, String userpwd) throws Exception {
		sqlSession.getMapper(MemberMapper.class).passwordUpdate(userid, userpwd);
		
	}

	@Override
	public boolean signup(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).signup(memberDto) ==1;
	}

	@Override
	public boolean deleteMember(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(userid) ==1;
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto) ==1;
	}

	@Override
	public String isValidMember(String userid, String email) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).isValidMember(userid, email);
	}

	@Override
	public boolean modifyPassword(PasswordDto passwordDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class)
				.modifyPassword(passwordDto.getOriginpassword(), passwordDto.getNewpassword(), passwordDto.getUserid())==1;
	}

	@Override
	public boolean insertFavorite(FavoriteDto favoriteDto) throws Exception {
		if(favoriteDto.getCode() == null || favoriteDto.getHouse_type() == null || favoriteDto.getUserid() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemberMapper.class).insertFavorite(favoriteDto) == 1;
	}

	@Override
	public List<FavoriteDto> listFavorite(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).listFavorite(userid);
	}

	@Override
	public boolean deleteFavorite(String sgdbbCode) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteFavorite(sgdbbCode) == 1;
	}
	

	
	
}
