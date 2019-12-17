package model;

import java.util.ArrayList;

public class AssociationBean {

  private ArrayList<String> attribute;
  private String name, entity1, entity2;

  public AssociationBean(ArrayList<String> attribute, String name,
                        String entity1, String entity2) {
    super();
    this.attribute = attribute;
    this.name = name;
    this.entity1 = entity1;
    this.entity2 = entity2;
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
  
  public String getEntity1() {
    return entity1;
  }
  
  public void setEntity1(String entity1) {
    this.entity1 = entity1;
  }
  
  public String getEntity2() {
    return entity2;
  }
  
  public void setEntity2(String entity2) {
    this.entity2 = entity2;
  }



}
