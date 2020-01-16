package control;

import java.util.ArrayList;
import model.AssociationBean;
import model.ERBean;
import model.EntityBean;
import model.HierarchyBean;
import org.json.simple.*;



public class JsonParser {
  
  /**
   * Metodo che trasforma un ERBean in una stringa in formato JSON.
   * @param beanER Classe contenente due {@link ArrayList},
   una di {@link EntityBean} e una di {@link AssociationBean}.
   * @return
   */
  public static JSONObject parseToJson(ERBean beanER) {
    JSONObject bean = new JSONObject();
    bean.put("entity", entityToJson(beanER.getEntity()));
    bean.put("association", associationToJson(beanER.getAssociation()));
    bean.put("hierarchy", hierarchyToJson(beanER.getHierarchy()));
    return bean;
  }
 
  /*metodo per il popolamento del JSONArray delle entity.
   */
  private static JSONArray entityToJson(ArrayList<EntityBean> entities) {
    JSONArray entityArray = new JSONArray();
    
    for (int i = 0; i < entities.size(); i++) {
      JSONObject obj = new JSONObject();
      obj.put("name", entities.get(i).getName());
      
      ArrayList<String> attributes = entities.get(i).getAttribute();
      ArrayList<String> xAttributes = entities.get(i).getxAttribute();
      ArrayList<String> yAttributes = entities.get(i).getyAttribute();
      
      
      JSONArray attributeArray = new JSONArray();
      for (int j = 0; j < attributes.size(); j++) {
        attributeArray.add(attributes.get(j));
      }
      JSONArray xAttributeArray = new JSONArray();
      for (int j = 0; j < xAttributes.size(); j++) {
        xAttributeArray.add(xAttributes.get(j));
      }
      JSONArray yAttributeArray = new JSONArray();
      for (int j = 0; j < yAttributes.size(); j++) {
        yAttributeArray.add(yAttributes.get(j));
      }
      
      
      obj.put("attributes", attributeArray);
      obj.put("x", entities.get(i).getX());
      obj.put("y", entities.get(i).getY());
      obj.put("xAttribute", xAttributes);
      obj.put("yAttribute", yAttributes);



      entityArray.add(obj);
    }
    
    return entityArray;
  }

  /*metodo per il popolamento del JSONArray delle gerarchie.
   */
  private static JSONArray hierarchyToJson(ArrayList<HierarchyBean> hierarchies) {
    JSONArray hierarchyArray = new JSONArray();
    if (hierarchies != null) {
      for (int i = 0; i < hierarchies.size(); i++) {
        JSONObject obj = new JSONObject();
        obj.put("father", hierarchies.get(i).getFather());
        obj.put("son", hierarchies.get(i).getSons());
        obj.put("x", hierarchies.get(i).getX());
        obj.put("y", hierarchies.get(i).getY());

        hierarchyArray.add(obj);
      }
    }
    return hierarchyArray;

  }
  
  /*metodo per il popolamento del JSONArray delle assiociazioni.
   */
  private static JSONArray associationToJson(ArrayList<AssociationBean> associations) {
    JSONArray associationArray = new JSONArray();

    for (int i = 0; i < associations.size();  i++) {
      JSONObject obj = new JSONObject();
      obj.put("name", associations.get(i).getName());
 
      
      ArrayList<String> entity = associations.get(i).getEntity();
      JSONArray entityArray = new JSONArray();
      
      //ciclo per l'aggiunta delle entità
      for (int j = 0; j < entity.size(); j++) {
        entityArray.add(entity.get(j));
      }
      obj.put("entity", entityArray);
      obj.put("x", associations.get(i).getX());
      obj.put("y", associations.get(i).getY());
            
      //"attributes" : [] se l'array � vuoto  
      
      associationArray.add(obj);
    
      
      ArrayList<String> attributes = associations.get(i).getAttribute();
      JSONArray attributeArray = new JSONArray();
     
      //ciclo per l'aggiunta degli attributi
      for (int j = 0; j < attributes.size(); j++) {
        attributeArray.add(attributes.get(j));
      }
      obj.put("attributes", attributeArray);
      //"attributes" : [] se l'array � vuoto  
      
      associationArray.add(obj);
    }
    
    return associationArray;
  }
}
