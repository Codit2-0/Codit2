package model;

import java.util.ArrayList;

/**
 * Classe che gestisce i dati di un'entita'
 * di un diagramma ER.
 */
public class EntityBean {



private String name;
  private ArrayList<String> attribute;
  private String x;
  private String y;
  private ArrayList<String> xAttribute;
  private ArrayList<String> yAttribute;


  public EntityBean(String name, ArrayList<String> attribute, String x, String y, ArrayList<String> xAttribute, ArrayList<String> yAttribute) {
    this.name = name;
    this.attribute = attribute;
    this.x = x;
    this.y = y;
    this.xAttribute = xAttribute;
    this.yAttribute = yAttribute;

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
	
	public String getX() {
		return x;
	}
	
	public void setX(String x) {
		this.x = x;
	}
	
	public String getY() {
		return y;
	}
	
	public void setY(String y) {
		this.y = y;
	}
	
	public ArrayList<String> getxAttribute() {
			return xAttribute;
	}

	public void setxAttribute(ArrayList<String> xAttribute) {
		this.xAttribute = xAttribute;
	}

	public ArrayList<String> getyAttribute() {
		return yAttribute;
	}

	public void setyAttribute(ArrayList<String> yAttribute) {
		this.yAttribute = yAttribute;
	}



}
