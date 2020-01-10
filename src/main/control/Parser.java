package control;

import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.AssociationBean;
import model.ERBean;
import model.EntityBean;
import model.HierarchyBean;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Parser {

  static final String ATTRIBUTES = "ownedAttribute";
  static final String CLASSES = "packagedElement"; 
  //static final String FILE_ADDRESS = "/Users/antoniocimino/git/Codit/src/main/util/test.xmi";

  public ERBean parser (File url) {
    ERBean modello = new ERBean();
    ArrayList<EntityBean> arrayEntity = new ArrayList<EntityBean>();
    ArrayList<AssociationBean> arrayAssociation = new ArrayList<AssociationBean>();
    ArrayList<HierarchyBean> arrayHierarchy = new ArrayList<HierarchyBean>();
    try {

      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(url);
      ArrayList<String> attribute = null;
      ArrayList<String> entityList = new ArrayList<String>();

      EntityBean entity = null;
      AssociationBean association = null;
      HierarchyBean hierarchy = null;

      doc.getDocumentElement().normalize();
      //System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

      NodeList listClass = doc.getElementsByTagName(CLASSES);
      int totalClass = listClass.getLength();
      //System.out.println("Total Class : " + totalClass);

      for (int i = 0; i < totalClass;i++) {

        String type =  listClass.item(i).getAttributes().getNamedItem("xmi:type").toString();
        if (type.equals("xmi:type=\"uml:Association\"")) {
        	if(listClass.item(i).getAttributes().getNamedItem("type")!=null) {
        		String hierarchyString = listClass.item(i).getAttributes()
                        .getNamedItem("type").toString();
            	if(hierarchyString.equals("type=\"ISA\"")) {

            		String nomeGerarchia = listClass.item(i).getAttributes()
                            .getNamedItem("xmi:id").toString();
            		nomeGerarchia = nomeGerarchia.substring(nomeGerarchia.indexOf('"') + 1,
            				nomeGerarchia.lastIndexOf('"'));
					String padre = nomeGerarchia;
					String figlio = nomeGerarchia;
					
					
					padre = nomeGerarchia.substring(0, nomeGerarchia.indexOf("__"));
					figlio = nomeGerarchia.substring(nomeGerarchia.indexOf("__")+2, nomeGerarchia.lastIndexOf("__"));
				
					
					
					hierarchy = new HierarchyBean(padre, figlio);
					arrayHierarchy.add(hierarchy);

            		
            	}
        	} else{
        	
          String nomeAssociazione = listClass.item(i).getAttributes()
                                              .getNamedItem("xmi:id").toString();
          nomeAssociazione = nomeAssociazione.substring(nomeAssociazione.indexOf('"') + 1,
                                                       nomeAssociazione.lastIndexOf('"'));
          int j = 0;
          ArrayList<String> tmp = new ArrayList<String>();
          String nuovaStringa = nomeAssociazione;
          while(true) {

          if(!nuovaStringa.equals("id")) {
        	  //System.out.println("nuovaStringa: "+ nuovaStringa+ " j: "+j);
          	tmp.add(nomeAssociazione.substring(j, nuovaStringa.indexOf("__")+j));

          nuovaStringa = nuovaStringa.substring(nuovaStringa.indexOf("__") + 2);

          j = nomeAssociazione.indexOf(nuovaStringa);

          }else {
          j= 0;
          nuovaStringa = "";
          break;
          }
          }
          for(int k = 0; k < tmp.size()-1; k++) {
        	  entityList.add(tmp.get(k));
        	  
          }
          nomeAssociazione = tmp.get(tmp.size()-1);
          
          tmp = new ArrayList<String>();
          
          attribute = new ArrayList<String>();
    
          association = new AssociationBean(nomeAssociazione, attribute, entityList);
          entityList = new ArrayList<String>();
          association.stampa();

          arrayAssociation.add(association);
          
        	}
        } else {

          String nomeClasse = listClass.item(i).getAttributes().getNamedItem("xmi:id").toString();
          nomeClasse = nomeClasse.substring(nomeClasse.indexOf('"') + 1,
                                            nomeClasse.lastIndexOf('"'));
          //System.out.println("Classe : " + nomeClasse);
          attribute = new ArrayList<String>();
          attribute = new ArrayList<String>();
          entity = new EntityBean(nomeClasse, attribute);
          arrayEntity.add(entity);

        }

        NodeList listAttributesClass = listClass.item(i).getChildNodes();
        int totalAttributesClass = listAttributesClass.getLength();

        for (int j = 0; j < totalAttributesClass; j++) {
          String Attribute = listAttributesClass.item(j).getNodeName();
          if (Attribute.equals(ATTRIBUTES)) {
            String nomeAttributo = listAttributesClass.item(j).getAttributes()
                                                       .getNamedItem("name").toString();
            nomeAttributo = nomeAttributo.substring(nomeAttributo.indexOf('"') + 1,
                                                    nomeAttributo.lastIndexOf('"'));
            //System.out.println("Attribute : " + nomeAttributo);
            entity.getAttribute().add(nomeAttributo);
            for (int o = 0; o < arrayEntity.size(); o++) {
              if (arrayEntity.get(o).getName().equals(entity.getName())) {
                arrayEntity.remove(o);
                arrayEntity.add(entity);
                break;
              }
            }

          }
        }

      }

    } catch (SAXParseException err) {
      System.out.println("** Parsing error" + ", line "
                        + err.getLineNumber() + ", uri " + err.getSystemId());
      System.out.println(" " + err.getMessage());
    } catch (SAXException e) { 
      Exception x = e.getException();
      ((x == null) ? e : x).printStackTrace();
    } catch (Throwable t) {
      t.printStackTrace();
    }


    modello.setEntity(arrayEntity);
    modello.setAssociation(arrayAssociation);
    modello.setHierarchy(arrayHierarchy);
    modello.stampa();
    return modello;
  }
}
