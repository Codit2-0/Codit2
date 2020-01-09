package model;

public class HierarchyBean {
	  private String sons;
	  private String father;

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

}
