package com.hpe.enterprise.endpoints;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.hpe.enterprise.bl.EnterpriseService;
import com.hpe.enterprise.bl.impl.EnterpriseServiceImpl;
import com.hpe.enterprise.entities.Comment;
import com.hpe.enterprise.entities.Reply;
import com.hpe.enterprise.models.CommentsWithReplies;
import com.hpe.enterprise.models.Product;

@Path("/hpe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnterpriseEndPoints {

	private EnterpriseService enterpriseService = new EnterpriseServiceImpl();

	@POST
	@Path("/add/comment/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postComment(Comment comment) {

		long result = enterpriseService.postComment(comment);
		return Response.status(201).entity(result).build();

	}

	@POST
	@Path("/reply/comment/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replyOnComment(Reply reply) {

		long result = enterpriseService.replyOnComment(reply);
		return Response.status(201).entity(result).build();

	}

	@GET
	@Path("/allCommentsWithReplies/{orderId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllCommentsWithReplies(@PathParam("orderId") long orderId) {

		List<CommentsWithReplies> result = enterpriseService.getCommentsWithReplies(orderId);
		return Response.status(201).entity(result).build();

	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response defaultReverser() throws JSONException {

		StringBuilder sb = new StringBuilder();
		sb.append("ANKARA");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("original", sb.toString());
		jsonObject.put("reversed", sb.reverse().toString());

		String result = "" + jsonObject;
		return Response.status(200).entity(result).build();
	}

}
