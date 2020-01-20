package model;

/**
 * Classe che gestisce i dati di una gerarchia
 * di un diagramma ER.
 */
public class HierarchyBean {
  private String sons;
  private String father;
  private String posX;
  private String posY;

  /** Construttore della classe {@link HierarchyBean}.
   * @param father Nome dell'entita' padre
   * @param sons Nome dell'entita' figlio
   * @param x Coorinata x della gerarchia
   * @param y Coordinata y della gerarchia
   */
  public HierarchyBean(String father, String sons, String x, String y) {
    super();
    this.father = father;
    this.sons = sons;
    this.posX = x;
    this.posY = y;
  }

  public String getSons() {
    return sons;
  }

  public void setSons(String sons) {
    this.sons = sons;
  }

  public String getFather() {
    return father;
  }

  public void setFather(String father) {
    this.father = father;
  }

  @Override
  public String toString() {
    return "HierarchyBean [sons=" + sons + ", father=" + father + "]";
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
