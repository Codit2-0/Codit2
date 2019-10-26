package control;

import java.util.ArrayList;
import model.AssociationBean;
import model.ERBean;
import model.EntityBean;
import org.json.simple.*;



public class JsonParser {
  
  /**
   * Metodo che trasforma un ERBean in una stringa in formato JSON.
   * @param beanER è una classe contenente due {@link ArrayList},
   una di {@link EntityBean} e una di {@link AssociationBean}.
   * @return
   */
  public static JSONObject parseToJson(ERBean beanER) {
    JSONObject bean = new JSONObject();
    bean.put("entity", entityToJson(beanER.getEntity()));
    bean.put("association", associationToJson(beanER.getAssociation()));
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
      JSONArray attributeArray = new JSONArray();
      for (int j = 0; j < attributes.size(); j++) {
        attributeArray.add(attributes.get(j));
      }
      
      obj.put("attributes", attributeArray);

      entityArray.add(obj);
    }
    
    return entityArray;
  }
  
  /*metodo per il popolamento del JSONArray delle assiociazioni.
   */
  private static JSONArray associationToJson(ArrayList<AssociationBean> associations) {
    JSONArray associationArray = new JSONArray();

    for (int i = 0; i < associations.size(); i++) {
      JSONObject obj = new JSONObject();
      obj.put("name", associations.get(i).getName());
      obj.put("entity1", associations.get(i).getEntity1());
      obj.put("entity2", associations.get(i).getEntity2());
      
      ArrayList<String> attributes = associations.get(i).getAttribute();
      JSONArray attributeArray = new JSONArray();
     
      //ciclo per l'aggiunta degli attributi
      for (int j = 0; j < attributes.size(); j++) {
        attributeArray.add(attributes.get(j));
      }
      obj.put("attributes", attributeArray);
      //"attributes" : [] se l'array è vuoto  
      
      associationArray.add(obj);
    }
    
    return associationArray;
  }
}
