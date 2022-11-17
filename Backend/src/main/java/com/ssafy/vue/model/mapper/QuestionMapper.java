package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.QuestionDto;

@Mapper
public interface QuestionMapper {
	
	public int writeArticle(QuestionDto questionDto) throws SQLException;
	public List<QuestionDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
	public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
	public QuestionDto getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(QuestionDto questionDto) throws SQLException;
	public void deleteMemo(int articleno) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	
}