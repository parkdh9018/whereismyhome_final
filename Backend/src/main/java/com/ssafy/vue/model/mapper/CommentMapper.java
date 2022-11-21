package com.ssafy.vue.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.vue.model.CommentDto;


public interface CommentMapper {

	public List<CommentDto> list(String articleno);
	public int create(CommentDto commentDto);
	public int modify(CommentDto commentDto);
	public int delete(int commentno);
 
}
 
