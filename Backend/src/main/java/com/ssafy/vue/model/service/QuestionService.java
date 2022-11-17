package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.QuestionDto;

public interface QuestionService {
	public boolean writeArticle(QuestionDto questionDto) throws Exception;
	public List<QuestionDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public QuestionDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(QuestionDto questionDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
