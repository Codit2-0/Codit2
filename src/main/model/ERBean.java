package model;

import java.util.ArrayList;

/**
 * Classe che raccoglie le informazioni
 * di un diagramma ER.
 */
public class ERBean {

  private ArrayList<EntityBean> entity;
  private ArrayList<AssociationBean> association;
  private ArrayList<HierarchyBean> hierarchy;

  public ERBean() {

  }

  /**
   * costruttore della classe {@link ERBean}.
   * @param entity Lista delle entita' del diagramma
   * @param association Lista delle relazioni del diagramma
   * @param hierarchy Lista delle gerarchie del diagramma
   */
  public ERBean(ArrayList<EntityBean> entity, ArrayList<AssociationBean> association,
                ArrayList<HierarchyBean> hierarchy) {
    super();
    this.entity = entity;
    this.association = association;
    this.hierarchy = hierarchy;
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
  
  public ArrayList<HierarchyBean> getHierarchy() {
    return hierarchy;
  }

  public void setHierarchy(ArrayList<HierarchyBean> hierarchy) {
    this.hierarchy = hierarchy;
  }

  /**
   *  Metodo per la ricerca di un'entita'.
   * @param name L'input e' il nome dell'entita' 
   * @return {@link EntityBean}
   */
  public EntityBean findByName(String name) {
    if (entity != null) {
      for (int i = 0; i < entity.size(); i++) {
        if (entity.get(i).getName().equals(name)) {
          return entity.get(i);
        }
      }
    }
    return null;
  }

  /**
   *  Metodo per la ricerca di una relazione.
   * @param name L'input e' il nome della relazione
   * @return {@link AssociationBean}
   */
  public AssociationBean findAssociationByName(String name) {
    if (association != null) {
      for (int i = 0; i < association.size(); i++) {
        if (association.get(i).getName().equals(name)) {
          return association.get(i);
        }
      }
    }
    return null;
  }

  /**
   * metodo di controllo che stampa il contenuto del bean.
   */
  public void stampa() {
//    if (entity != null) {
//      for (int i = 0; i < entity.size(); i++) {
//        //System.out.println(entity.get(i).getName() + "@@@");
//        for (int j = 0; j < entity.get(i).getAttribute().size(); j++) {
//          // System.out.println(entity.get(i).getAttribute().get(j));
//        }
//      }
//    }
//    if (association != null) {
//      for (int i = 0; i < association.size(); i++) {
//        // System.out.println(association.get(i).getName() + "@@");
//      }
//    }
//    if (hierarchy != null) {
//      for (int i = 0; i < hierarchy.size(); i++) {
//        System.out.println(hierarchy.toString() + "@@");
//      }
//    }
  }
  
  
}
