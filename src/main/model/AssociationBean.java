package model;

import java.util.ArrayList;

/**
 * Classe che gestisce i dati di  una relazione di un 
 * diagramma ER.
 */
public class AssociationBean {

  private ArrayList<String> attribute;
  private ArrayList<String> entity;
  private String name;
  private String posX;
  private String posY;

  /**
   * Costruttore della classe {@link AssociationBean}. 
   * @param name nome che identifica la relazione
   * @param attribute lista degli attributi della relazione
   * @param entity lista delle entita' collegate alla relazione
   */
  public AssociationBean(String name, ArrayList<String> attribute,
                         ArrayList<String> entity, String x, String y) {
    super();
    this.name = name;
    this.attribute = attribute;
    this.entity = entity;
    this.posX = x;
    this.posY = y;
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
  
  public void addEntity(String entity) {
    this.entity.add(entity);
  }
  
  public String getX() {
    return posX;
  }

  public void setX(String x) {
    this.posX = x;
  }

  public String getY() {
    return posY;
  }

  public void setY(String y) {
    this.posY = y;
  }
}

