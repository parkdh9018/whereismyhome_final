package com.ssafy.vue.model;

import java.util.List;

public class DetailDto{
	
	private List<HouseDealDto> housedealDto;
	private List<AmtDto> amtDto;
	
	private String max_amt;
	private String avg_amt;
	private String avg_gtn;
	private String max_gtn;
	
	
	public String getAvg_gtn() {
		return avg_gtn;
	}
	public void setAvg_gtn(String avg_gtn) {
		this.avg_gtn = avg_gtn;
	}
	public String getMax_gtn() {
		return max_gtn;
	}
	public void setMax_gtn(String max_gtn) {
		this.max_gtn = max_gtn;
	}
	public List<AmtDto> getAmtDto() {
		return amtDto;
	}
	public void setAmtDto(List<AmtDto> amtDto) {
		this.amtDto = amtDto;
	}
	public String getMax_amt() {
		return max_amt;
	}
	public void setMax_amt(String max_amt) {
		this.max_amt = max_amt;
	}
	
	public String getAvg_amt() {
		return avg_amt;
	}
	public void setAvg_amt(String avg_amt) {
		this.avg_amt = avg_amt;
	}
	public List<HouseDealDto> getHousedealDto() {
		return housedealDto;
	}
	public void setHousedealDto(List<HouseDealDto> housedealDto) {
		this.housedealDto = housedealDto;
	}
	
	
}
