package com.hpe.enterprise.bl.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.enterprise.bl.EnterpriseService;
import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;
import com.hpe.enterprise.models.CommentsWithReplies;
import com.hpe.enterprise.models.HibernateUtility;

public class EnterpriseServiceImpl implements EnterpriseService {

	@Override
	public long postComment(Comment comment) {

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(comment);

		session.getTransaction().commit();
		session.close();

		return comment.getCommentId();
	}

	@Override
	public long replyOnComment(Reply reply) {

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		session.save(reply);

		session.getTransaction().commit();
		session.close();

		return reply.getReplyId();
	}

	@Override
	public List<CommentsWithReplies> getCommentsWithReplies(long orderId) {

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from com.hpe.enterprise.entities.Comment where orderId = :orderId");
		query.setParameter("orderId", orderId);
		List<Comment> comments = query.list();

		session.getTransaction().commit();
		session.close();

		List<Long> commentIds = getAllCommentIds(comments);

		List<Reply> replies = getAllRepliesPerComments(commentIds);

		List<CommentsWithReplies> commentsWithReplies = getCommentsWithReplies(comments, replies);

		return commentsWithReplies;

	}

	private List<CommentsWithReplies> getCommentsWithReplies(List<Comment> comments, List<Reply> replies) {

		List<CommentsWithReplies> commentWithRepliesList = new ArrayList<>();
		Map<Long, List<Reply>> commentWithRepliesMap = new HashMap<>();

		for (Reply reply : replies) {
			long commentId = reply.getCommentId();
			List<Reply> currReplies = commentWithRepliesMap.get(commentId);
			if (currReplies == null) {

				currReplies = new ArrayList<>();
				currReplies.add(reply);
				commentWithRepliesMap.put(commentId, replies);

			} else {
				currReplies.add(reply);
			}

		}

		for (Comment comment : comments) {
			List<Reply> repliesList = commentWithRepliesMap.get(comment.getCommentId());
			if (commentWithRepliesMap.get(comment.getCommentId()) != null) {
				CommentsWithReplies commentsWithReplies = new CommentsWithReplies();
				commentsWithReplies.setComment(comment);
				commentsWithReplies.setReplies(repliesList);

				commentWithRepliesList.add(commentsWithReplies);
			}

		}

		return commentWithRepliesList;
	}

	private List<Reply> getAllRepliesPerComments(List<Long> commentIds) {
		List<Reply> replies = new ArrayList<>();
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from com.hpe.enterprise.entities.Reply where comment_id in (:commentIds)");

		query.setParameterList("commentIds", commentIds);
		replies = query.list();

		session.getTransaction().commit();
		session.close();

		return replies;

	}

	private List<Long> getAllCommentIds(List<Comment> comments) {
		List<Long> commentIds = new ArrayList<>();

		for (Comment comment : comments) {
			commentIds.add(comment.getCommentId());
		}

		return commentIds;
	}

}
