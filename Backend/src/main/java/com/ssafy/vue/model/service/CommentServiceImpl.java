package com.ssafy.vue.model.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.CommentDto;
import com.ssafy.vue.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDto> list(String articleno) {
	   return commentMapper.list(articleno);
   }
   
	@Override
	@Transactional
	public boolean create(CommentDto commentDto) {
	   return commentMapper.create(commentDto)== 1;
 
   }
   
	@Override
	@Transactional
	public boolean modify(CommentDto commentDto) {
	   return commentMapper.modify(commentDto)== 1;
    
   }
	
	@Override
	@Transactional
	public boolean delete(int commentno) {
		return commentMapper.delete(commentno) == 1;
	}

   
   
   
   
}


