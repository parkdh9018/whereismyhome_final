package com.ssafy.vue.model.service;

import java.util.List;
import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.QcommentDto;



public interface QcommentService {

	List<QcommentDto> list(String articleno);

	boolean create(QcommentDto qcommentDto);

	boolean modify(QcommentDto qcommentDto);

	boolean delete(int commentno);
}
