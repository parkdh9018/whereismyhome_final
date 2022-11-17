package com.ssafy.vue.model;


import java.util.Date;
 
public class QcommentDto {
 
    private int commentno;
    private int articleno;
    private String comment;
    private String userid;
    private Date regtime;
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
 
 
}