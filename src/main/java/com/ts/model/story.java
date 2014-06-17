package com.ts.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class story {
	
	private String title;

	private String storytext;
	
	 public ArrayList<String> storybook = new ArrayList<String>();


	
	public void addstory(ArrayList<String> storybook, String str){
		storybook.add(str);
		
	}

	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStorytext() {
		return storytext;
	}



	public void setStorytext(String storytext) {
		this.storytext = storytext;
	}
	

}


