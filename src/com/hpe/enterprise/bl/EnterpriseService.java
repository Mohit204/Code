package com.hpe.enterprise.bl;

import java.util.List;

import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;
import com.hpe.enterprise.models.CommentsWithReplies;

public interface EnterpriseService {

	public long postComment(Comment comment);

	public long replyOnComment(Reply reply);

	public List<CommentsWithReplies> getCommentsWithReplies(long orderId);

}
