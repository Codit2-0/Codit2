package control;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;

import model.ErBean;

public class ServerFacade {
	   
	private ParserXMI pXMI;
	private ParserBean pBean;
	private SaveFilePosition sFile;
	private OntologyManager oM; 

	   public ServerFacade() {
	      pXMI = new ParserXMI();
	      pBean = new ParserBean();
	      sFile = new SaveFilePosition();
	      oM = new OntologyManager();
	   }

	   public ErBean parserXMI (File url){
	      return pXMI.parser(url);
	   }
	   
	   public JSONObject parserBean(ErBean beanER){
	      return pBean.parseToJson(beanER);
	   }
	   
	   public void saveFilePosition(String path, String nome, String contenuto){
	      try {
			sFile.saveDialog(path, nome, contenuto);
	      } catch (IOException e) {
			e.printStackTrace();
	      }
	   }
	   
	   public void saveOntology(ErBean bean, String title){
		      oM.save(bean, title);;
	   }
}
