package com.ssafy.vue.model.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.QcommentDto;
import com.ssafy.vue.model.mapper.CommentMapper;
import com.ssafy.vue.model.mapper.QcommentMapper;

@Service
public class QcommentServiceImpl implements QcommentService {
	
	@Autowired
	private QcommentMapper qcommentMapper;

	@Override
	public List<QcommentDto> list(String articleno) {
	   return qcommentMapper.list(articleno);
   }
   
	@Override
	@Transactional
	public boolean create(QcommentDto qcommentDto) {
	   return qcommentMapper.create(qcommentDto)== 1;
 
   }
   
	@Override
	@Transactional
	public boolean modify(QcommentDto qcommentDto) {
	   return qcommentMapper.modify(qcommentDto)== 1;
    
   }
	
	@Override
	@Transactional
	public boolean delete(int commentno) {
		return qcommentMapper.delete(commentno) == 1;
	}

   
   
   
   
}


