package model;

import java.util.ArrayList;

public class AssociationBean {

  private ArrayList<String> attribute;
  private ArrayList<String> entity;
  private String name;

  public AssociationBean(String name, ArrayList<String> attribute, ArrayList<String> entity) {
    super();
    this.name = name;
    this.attribute = attribute;
    this.entity = entity;
  }

  public ArrayList<String> getAttribute() {
    return attribute;
  }
  
  public void setAttribute(ArrayList<String> attribute) {
    this.attribute = attribute;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public ArrayList<String> getEntity() {
    return entity;
  }
  
  public void addEntity(String entity){
	  this.entity.add(entity);
  }
  public void stampa() {
	  System.out.print("nome: "+name);
	  for(int i = 0; i < entity.size(); i++) {
		  System.out.print(" entità:"+entity.get(i)+" ");
	  }
  }
}
