package com.ssafy.vue.model;

public class DongCodeDto {
	
	String dongcode;
	String dongname;
	String lng;
	String lat;
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}

	
}
