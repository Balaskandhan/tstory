package com.ts.stories;



import java.util.ArrayList;






import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

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
import com.ts.command.UpdateStory;
import com.ts.command.SearchStory;
import com.ts.model.story;
import com.ts.command.GetStory;
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
	
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTitle(@PathParam("title") String title) {
		GetStory get = new GetStory();
		DBObject story = get.execute2(title);
		return Response.status(200).entity(story).build();
	}
	
	@GET
	@Path("/search/{str}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getstory(@PathParam("str") String str) {
		
		try{
		SearchStory get = new SearchStory();
		ArrayList<DBObject> list = get.execute(str);
	boolean success;
	if(list!=null){ success =true;}else success=false;
		
		
		if (success) {
			return Response.status(200).entity(list).build();
		} else
			return Response.status(500).entity("").build();
	} catch (Exception e) {
		return Response.status(500).entity(e.toString()).build();
	}
	
	}
	
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response createStory(String storyStr) {

		try {
			CreateStoryCommand create = new CreateStoryCommand();
			
			story stry = mapper.readValue(storyStr, story.class);
			stry.storybook.add(stry.getStorytext());
			//stry.addstory(stry.storybook, stry.getStorytext());
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
	
	
	
	@Path("/addstory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response conStory(String storyStr) {

		try {
			UpdateStory create = new UpdateStory();
			GetStory a = new GetStory();
			story stry = mapper.readValue(storyStr, story.class);
			story nstry= null;
			nstry = a.execute(stry.getTitle());
			nstry.addstory(nstry.storybook, stry.getStorytext());
			boolean success = create.execute(nstry);
			
			//will have to use update DB object command function****
			String storyJSON = mapper.writeValueAsString(nstry);
			if (success) {
				return Response.status(201).entity(storyJSON).build();
			} else
				return Response.status(500).entity("").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}
	
	
	@GET
	@Path("/twitter")
	@Produces(MediaType.WILDCARD)
	public Response textTwitter() {
		
		return Response.status(200).entity("").build();
	}
	
	

}
