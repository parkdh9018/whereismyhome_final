package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.FavoriteDto;
import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto memberInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public int idCheck(String userid)throws SQLException;
	public MemberDto idFind(MemberDto memberDto)throws Exception;
	public int idFindCheck(String email)throws Exception;
	public int passwordFind(MemberDto memberDto) throws Exception;
	public void passwordUpdate(String userid, String userpwd);
	public int signup(MemberDto memberDto) throws Exception;
	public int deleteMember(String userid)throws Exception;
	public int modifyMember(MemberDto memberDto)throws Exception;
	public String isValidMember(String userid, String email)throws Exception;
	public int modifyPassword(String originpassword, String newpassword, String userid)throws Exception;
	public int insertFavorite(FavoriteDto favoriteDto)throws Exception;
	public List<FavoriteDto> listFavorite(String userid) throws Exception;
	public int deleteFavorite(String sgdbbCode) throws SQLException;
	
}
