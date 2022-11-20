package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.RentDto;

@Mapper
public interface RentMapper {

	List<RentDto> listRent();


}
