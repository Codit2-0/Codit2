package control;

import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.AssociationBean;
import model.ERBean;
import model.EntityBean;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Parser {

  static final String ATTRIBUTES = "ownedAttribute";
  static final String CLASSES = "packagedElement"; 
  static final String FILE_ADDRESS = "C:/Users/feder/eclipse-workspace/Codit2-0/src/main/util/test.xmi";

  public ERBean parser (String url) {
    ERBean modello = new ERBean();
    ArrayList<EntityBean> arrayEntity = new ArrayList<EntityBean>();
    ArrayList<AssociationBean> arrayAssociation = new ArrayList<AssociationBean>();
    try {

      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(new File(FILE_ADDRESS));
      ArrayList<String> attribute = null;

      EntityBean entity = null;
      AssociationBean association = null;

      doc.getDocumentElement().normalize();
      System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

      NodeList listClass = doc.getElementsByTagName(CLASSES);
      int totalClass = listClass.getLength();
      System.out.println("Total Class : " + totalClass);

      for (int i = 0; i < totalClass;i++) {

        String type =  listClass.item(i).getAttributes().getNamedItem("xmi:type").toString();
        if (type.equals("xmi:type=\"uml:Association\"")) {

          String nomeAssociazione = listClass.item(i).getAttributes().getNamedItem("xmi:id").toString();
          nomeAssociazione = nomeAssociazione.substring(nomeAssociazione.indexOf('"') + 1, nomeAssociazione.lastIndexOf('"'));
          String nomeEntity1 = nomeAssociazione.substring(0, nomeAssociazione.indexOf("__"));
          EntityBean e1 = modello.findByName(nomeEntity1);
          String nuovaStringa = nomeAssociazione.substring(nomeAssociazione.indexOf("__") + 2);
          String nomeEntity2 = nuovaStringa.substring(0, nuovaStringa.indexOf("__"));
          EntityBean e2 = modello.findByName(nomeEntity2);
          attribute = new ArrayList<String>();
          association = new AssociationBean(attribute, nomeAssociazione, e1, e2);
          arrayAssociation.add(association);

        } else {

          String nomeClasse = listClass.item(i).getAttributes().getNamedItem("xmi:id").toString();
          nomeClasse = nomeClasse.substring(nomeClasse.indexOf('"') + 1, nomeClasse.lastIndexOf('"'));
          System.out.println("Classe : " + nomeClasse);
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
            String nomeAttributo = listAttributesClass.item(j).getAttributes().getNamedItem("name").toString();
            nomeAttributo = nomeAttributo.substring(nomeAttributo.indexOf('"') + 1, nomeAttributo.lastIndexOf('"'));
            System.out.println("Attribute : " + nomeAttributo);
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
      System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
      System.out.println(" " + err.getMessage());
    } catch (SAXException e) { 
      Exception x = e.getException();
      ((x == null) ? e : x).printStackTrace();
    } catch (Throwable t) {
      t.printStackTrace();
    }


    modello.setEntity(arrayEntity);
    modello.setAssociation(arrayAssociation);
    modello.stampa();
    return modello;
  }
}
