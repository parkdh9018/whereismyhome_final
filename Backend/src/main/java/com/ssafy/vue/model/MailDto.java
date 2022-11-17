package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MailDto  : 이메일", description = "이메일 정보")
public class MailDto  {

	@ApiModelProperty(value = "주소")
	private String address;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "메세지")
	private String message;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}