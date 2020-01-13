package model;

/**
 * Classe che gestisce i dati di una gerarchia
 * di un diagramma ER.
 */
public class HierarchyBean {
  private String sons;
  private String father;

  /**
   * construttore della classe {@link HierarchyBean}.
   * @param father Nome dell'entita' padre
   * @param sons Nome dell'entita' figlio
   */
  public HierarchyBean(String father, String sons) {
    super();
    this.father = father;
    this.sons = sons;
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



}
