package model;

import java.util.ArrayList;

public class ERBean {

  private ArrayList<EntityBean> entity;
  private ArrayList<AssociationBean> association;

  public ERBean() {

  }

  public ERBean(ArrayList<EntityBean> entity, ArrayList<AssociationBean> association) {
    super();
    this.entity = entity;
    this.association = association;
  }

  public ArrayList<EntityBean> getEntity() {
    return entity;
  }
  
  public void setEntity(ArrayList<EntityBean> entity) {
    this.entity = entity;
  }
  
  public ArrayList<AssociationBean> getAssociation() {
    return association;
  }
  
  public void setAssociation(ArrayList<AssociationBean> association) {
    this.association = association;
  }

  public EntityBean findByName(String name) {
    if (entity != null)
      for (int i = 0; i < entity.size(); i++) {
        if (entity.get(i).getName().equals(name))
          return entity.get(i);
      }
    return null;
  }

  public AssociationBean findAssociationByName(String name) {
    if(association != null)
      for (int i = 0; i < association.size(); i++) {
        if (association.get(i).getName().equals(name))
          return association.get(i);
      }
    return null;
  }

  public void stampa() {
    if (entity != null)
      for (int i = 0; i < entity.size(); i++) {
        //System.out.println(entity.get(i).getName() + "@@@");
        for (int j = 0; j < entity.get(i).getAttribute().size(); j++) {
         // System.out.println(entity.get(i).getAttribute().get(j));
        }
      }
    if (association != null)
      for (int i = 0; i < association.size(); i++) {
       // System.out.println(association.get(i).getName() + "@@");
      }
  }
}