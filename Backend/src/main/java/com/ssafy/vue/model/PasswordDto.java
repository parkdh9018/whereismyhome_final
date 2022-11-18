package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PasswordDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class PasswordDto {
	@ApiModelProperty(value = "user 아이디")
	private String userid;
	@ApiModelProperty(value = "새로운 비밀번호")
	private String newpassword;
	@ApiModelProperty(value = "원래 비밀번호")
	private String originpassword;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getOriginpassword() {
		return originpassword;
	}
	public void setOriginpassword(String originpassword) {
		this.originpassword = originpassword;
	}


}