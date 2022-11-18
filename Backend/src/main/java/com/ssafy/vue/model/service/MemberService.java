package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.PasswordDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto memberInfo(String userid) throws Exception;
	
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
	public int idCheck(String userid) throws Exception;
	public MemberDto idFind(MemberDto memberDto) throws Exception;
	public int idFindCheck(String email) throws Exception;
	
	public int passwordFind(MemberDto memberDto) throws Exception;
	public void passwordUpdate(String userid, String userpwd) throws Exception;
	
	public boolean signup(MemberDto memberDto)  throws Exception;
	public boolean deleteMember(String userid) throws Exception;
	public boolean modifyMember(MemberDto memberDto)throws Exception;
	public String isValidMember(String userid, String email)throws Exception;
	public boolean modifyPassword(PasswordDto passwordDto) throws Exception;

	
}
