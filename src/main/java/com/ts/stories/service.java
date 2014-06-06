package com.ts.stories;



import java.util.ArrayList;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

import com.ts.command.CreateStoryCommand;
import com.ts.command.ListAllStoriesCommand;
import com.ts.model.story;
import com.mongodb.DBObject;

@Path("/stories")
public class service {
	ObjectMapper mapper = new ObjectMapper();
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listStory() {
		ListAllStoriesCommand liststry = new ListAllStoriesCommand();
		ArrayList<DBObject> list = liststry.execute();
		return Response.status(200).entity(list).build();
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response createStory(String storyStr) {

		try {
			CreateStoryCommand create = new CreateStoryCommand();
			story stry = mapper.readValue(storyStr, story.class);
			//story stry= new;
			//stry.setStory(storyStr);
			boolean success = create.execute(stry);
			String storyJSON = mapper.writeValueAsString(stry);
			if (success) {
				return Response.status(201).entity(storyJSON).build();
			} else
				return Response.status(500).entity("").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}

}
