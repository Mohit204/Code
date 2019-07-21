package com.hpe.enterprise.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.fasterxml.jackson.annotation.JsonPropert

@Entity(name = "comment")
public class Comment implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	@Column(name = "comment_id")
	private long commentId;

	@JsonProperty
	@Column(name = "user_id")
	private long userId;

	@JsonProperty
	@Column(name = "order_id")
	private long orderId;

	@JsonProperty
	@Column(name = "text")
	private String text;

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
