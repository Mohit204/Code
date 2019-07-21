package com.hpe.enterprise.models;

import java.util.List;

import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;

public class CommentsWithReplies {

	private Comment comment;
	private List<Reply> replies;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

}
