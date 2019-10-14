package control;

import java.util.List;
import model.ERBean;
import model.EntityBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;





public class ParserJson {

  /**
   * questo metodo è usato per scrivere un oggetto java in un file JSON.
   * @param bean è un oggetto {@link ERBean} 
   */
  public void writeJson(ERBean bean) {
    JSONArray listaJson = new JSONArray();
    List<EntityBean> listaEntity = bean.getEntity();

    for (EntityBean en: listaEntity) {
      JSONObject obj = new JSONObject();
      obj.put("name", en.getName());
      
    }
    
    
    
  }

  /**
   * questo metodo è usato per convertire il contenuto di un file JSON in un oggetto java.
   * @return
   */
  public ERBean readJson() {
    
    
    return null;
  }


}
