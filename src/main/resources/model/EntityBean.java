package model;

import java.util.ArrayList;

public class EntityBean {
	
	private String name;
	private ArrayList<String> attribute;
	
	public EntityBean(String name, ArrayList<String> attribute) {
		this.name=name;
		this.attribute=attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getAttribute() {
		return attribute;
	}

	public void setAttribute(ArrayList<String> array) {
		this.attribute = array;
	}
	
	
	
}
