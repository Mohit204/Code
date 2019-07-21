package com.hpe.enterprise.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "reply")
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private long replyId;
	@Column(name = "comment_id")
	private long commentId;
	@Column(name = "user_id")
	private long userId;
	@Column(name = "text")
	private String text;

	public long getReplyId() {
		return replyId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
