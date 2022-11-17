package com.ssafy.vue.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.QcommentDto;
import com.ssafy.vue.model.service.QcommentService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qcomment")
public class QcommentContoller {
	private static final Logger logger = LoggerFactory.getLogger(QcommentContoller.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
    QcommentService qcommentService;
    
    
	@ApiOperation(value = "articleno에 해당하는 댓글 목록을 반환한다.", response = List.class)
	@GetMapping("{articleno}")
	public ResponseEntity<List<QcommentDto>> listComment(@PathVariable("articleno") String articleno) {
		logger.debug("listComment - 호출");
		return new ResponseEntity<>(qcommentService.list(articleno), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "새로운 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createComment(@RequestBody QcommentDto qcommentDto) {
		logger.debug("createComment - 호출");
		if(qcommentService.create(qcommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
    
	@ApiOperation(value = "글번호가 commentno에 해당하는 댓글을 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyComment(@RequestBody QcommentDto qcommentDto) {
		logger.debug("modifyComment - 호출");
		logger.debug("" + qcommentDto);
		System.out.println("댓글 수정하자!!!"  + qcommentDto);
		if(qcommentService.modify(qcommentDto)) {
			System.out.println("댓글 수정 성공!!!");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글번호가 comment_no에 해당하는 댓글을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{commentNO}")
	public ResponseEntity<String> deleteBook(@PathVariable("commentNO") int commentno) {
		logger.debug("deleteBook - 호출");
		if(qcommentService.delete(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}
 
