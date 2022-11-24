package com.ssafy.vue.model;

import java.util.List;

public class DetailDto{
	
	private List<HouseDealDto> housedealDto;
	private List<AmtDto> amtDto;
	
	private String max_amt;
	private String min_amt;
	private String avg_amt;
	
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
	public String getMin_amt() {
		return min_amt;
	}
	public void setMin_amt(String min_amt) {
		this.min_amt = min_amt;
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
