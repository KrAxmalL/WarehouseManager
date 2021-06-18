package org.example.UI;

public class Category {

	private String name;
	private String description;
	private static int MAX_ID = 0;
	private int id;

	
	/* Constructor */
	public Category(int newID, String newName, String newDescription) {
		this.id = newID;
		this.name = newName;
		this.description = newDescription;
		if (MAX_ID < newID){MAX_ID = newID;}
	}

	
	/* Constructor */
	public Category(String newName, String newDescription) {
		this.name = newName;
		this.description = newDescription;
		MAX_ID++;
		id = MAX_ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	public int getCategoryId() {
		return this.id;
	}

	@Override
	public String toString() {
//		return "Category: "+this.name+"\n Description: "+this.description+"\n Category Id: "+this.id;
		return  this.id  + "|" + this.name + "|" + this.description ;
	}
	
	//Returns the detailed info about the category
	public String detailedInfo() {
		return "Category: "+this.name+"\n Description: "+this.description+"\n Category Id: "+this.id + "." + "\n";
	}
}
