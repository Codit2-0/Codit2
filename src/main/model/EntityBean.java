package model;

import java.util.ArrayList;

/**
 * Classe che gestisce i dati di un'entita'
 * di un diagramma ER.
 */
public class EntityBean {



  private String name;
  private ArrayList<String> attribute;
  private String posX;
  private String posY;
  private ArrayList<String> attributePosX;
  private ArrayList<String> attributePosY;


  /**
   * costruttore della classe {@link EntityBean}.
   * @param name Nome dell'entità
   * @param attribute Lista degli attributi
   * @param x Coordinata x dell'entità
   * @param y Coordinata y dell'entità
   * @param attributePosX Lista delle coordinate x degli attributi dell'entità
   * @param attributePosY Lista delle coordinate y degli attributi dell'entità
   */
  public EntityBean(String name, ArrayList<String> attribute, String x, String y,
                    ArrayList<String> attributePosX, ArrayList<String> attributePosY) {
    this.name = name;
    this.attribute = attribute;
    this.posX = x;
    this.posY = y;
    this.attributePosX = attributePosX;
    this.attributePosY = attributePosY;

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

  public ArrayList<String> getxAttribute() {
    return attributePosX;
  }

  public void setxAttribute(ArrayList<String> attributePosX) {
    this.attributePosX = attributePosX;
  }

  public ArrayList<String> getyAttribute() {
    return attributePosY;
  }

  public void setyAttribute(ArrayList<String> attributePosY) {
    this.attributePosY = attributePosY;
  }



}
