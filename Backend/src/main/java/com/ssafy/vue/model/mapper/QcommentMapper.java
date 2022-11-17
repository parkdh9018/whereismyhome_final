package com.ssafy.vue.model.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.QcommentDto;


public interface QcommentMapper {

	List<QcommentDto> list(String articleno);
	int create(QcommentDto qcommentDto);
	int modify(QcommentDto qcommentDto);
	int delete(int commentno);
 
}
 
