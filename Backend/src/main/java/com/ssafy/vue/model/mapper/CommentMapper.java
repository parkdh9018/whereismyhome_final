package com.ssafy.vue.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.vue.model.CommentDto;


public interface CommentMapper {

	List<CommentDto> list(String articleno);

	int create(CommentDto commentDto);

	int modify(CommentDto commentDto);

	int delete(int commentno);
 
}
 
