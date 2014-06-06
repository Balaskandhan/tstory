package com.ts.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class story {
	 
	//Vector<String> story = new Vector<String>(3,2);
	
	private String storyText;
	

	public String getStory() {
		return storyText;
	}

	public void setStory(String story) {
		this.storyText = story;
	}
	
	
	

	//the main function to call the respective funtion
	/*public static void main(String args[]){
			story obj = new story();
			Scanner sc = new Scanner(System.in);
			char c = 0;
			
			//asking for the title
			System.out.println("Enter the title:");
			String inputString= sc.nextLine();
			
			obj.story.addElement(new String(inputString));
			
			
			
			
			do{
			obj.enterstory();
			
			System.out.println("Do u wnat to continue?");
			 try {
				c = (char)System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}while(c!='n');
			
			
			obj.printstory();
		
			System.out.println("The title of the sotry is:"+obj.gettitle());
			sc.close();
		
	}
	
	 public void enterstory(){
		 String inputString = "";
			
			System.out.println("Enter the storyline:");
			 inputString = sc.nextLine();
			
			story.addElement(new String(inputString));
			
	 }
	
	 public void printstory(){
		 // enumerate the elements in the vector.
	      Enumeration vEnum = story.elements();
	      System.out.println("\nElements in vector:");
	      while(vEnum.hasMoreElements())
	         System.out.print(vEnum.nextElement() + " ");
	      System.out.println();
		
	 }
	 
	 public String gettitle(){
		 return story.firstElement();
	 }
*/
}


