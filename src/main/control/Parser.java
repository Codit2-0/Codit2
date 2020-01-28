package control;

import java.io.File;
import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.AssociationBean;
import model.EntityBean;
import model.ErBean;
import model.HierarchyBean;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class Parser {

  static final String ATTRIBUTES = "ownedAttribute";
  static final String CLASSES = "packagedElement"; 
  //static final String FILE_ADDRESS = "/Users/antoniocimino/git/Codit/src/main/util/test.xmi";

  /**
   * Parser che legge da un file .xmi e lo traduce in un elemento {@link ErBean}.
   * @param url il percorso del file all'interno del server
   * @return 
   */
  public ErBean parser(File url) {
    ErBean modello = new ErBean();
    ArrayList<EntityBean> arrayEntity = new ArrayList<EntityBean>();
    ArrayList<AssociationBean> arrayAssociation = new ArrayList<AssociationBean>();
    ArrayList<HierarchyBean> arrayHierarchy = new ArrayList<HierarchyBean>();
    try {

      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(url);
      ArrayList<String> attribute = null;
      ArrayList<String> xattribute = null;
      ArrayList<String> yattribute = null;
      ArrayList<String> entityList = new ArrayList<String>();

      EntityBean entity = null;
      AssociationBean association = null;
      HierarchyBean hierarchy = null;

      doc.getDocumentElement().normalize();

      NodeList listClass = doc.getElementsByTagName(CLASSES);
      int totalClass = listClass.getLength();

      //ciclo di tutti gli elementi 'packagedElement'
      for (int i = 0; i < totalClass;i++) {

        String type =  listClass.item(i).getAttributes().getNamedItem("xmi:type").toString();

        // controllo se il 'type' � 'Association' o 'Class'
        if (type.equals("xmi:type=\"uml:Association\"")) {

          // caso in cui l'elemento � 'Association', cio� una relazione

          if (listClass.item(i).getAttributes().getNamedItem("type") != null) {
            String hierarchyString = listClass.item(i).getAttributes()
                .getNamedItem("type").toString();

            // controllo se la relazione � una gerarchia
            if (hierarchyString.equals("type=\"ISA\"")) {

              String nomeGerarchia = listClass.item(i).getAttributes()
                  .getNamedItem("xmi:id").toString();
              String x;
              try {
                x = listClass.item(i).getAttributes().getNamedItem("x").toString();
                x = x.substring(x.indexOf("\"") + 1, x.lastIndexOf("\""));

              } catch (NullPointerException e) {
                x = "n";
              }
              String y;
              try {
                y = listClass.item(i).getAttributes().getNamedItem("y").toString();
                y = y.substring(y.indexOf("\"") + 1, y.lastIndexOf("\""));

              } catch (NullPointerException e) {
                y = "n";
              }

              nomeGerarchia = nomeGerarchia.substring(nomeGerarchia.indexOf('"') + 1,
                  nomeGerarchia.lastIndexOf('"'));
              String padre = nomeGerarchia;
              String figlio = nomeGerarchia;


              padre = nomeGerarchia.substring(0, nomeGerarchia.indexOf("__"));
              figlio = nomeGerarchia.substring(nomeGerarchia.indexOf("__") + 2,
                  nomeGerarchia.lastIndexOf("__"));
              
              
              boolean trovato = false;
              for (Iterator<HierarchyBean> h = arrayHierarchy.iterator(); h.hasNext(); ) {
                HierarchyBean bean = h.next();
                if (bean.getFather().equals(padre)) {
                  trovato = true;
                  int index = arrayHierarchy.indexOf(bean);
                  bean.addSon(figlio);
                  arrayHierarchy.set(index, bean);
                  break;
                }
              }
              if (!trovato) {
                hierarchy = new HierarchyBean(padre, x, y);
                hierarchy.addSon(figlio);
                arrayHierarchy.add(hierarchy);
              }

            }
          } else {

            String nomeAssociazione = listClass.item(i).getAttributes()
                .getNamedItem("xmi:id").toString();
            nomeAssociazione = nomeAssociazione.substring(nomeAssociazione.indexOf('"') + 1,
                nomeAssociazione.lastIndexOf('"'));
            int j = 0;
            ArrayList<String> tmp = new ArrayList<String>();
            String nuovaStringa = nomeAssociazione;

            String x;
            try {
              x = listClass.item(i).getAttributes().getNamedItem("x").toString();
              x = x.substring(x.indexOf("\"") + 1, x.lastIndexOf("\""));

            } catch (NullPointerException e) {
              x = "n";
            }
            String y;
            try {
              y = listClass.item(i).getAttributes().getNamedItem("y").toString();
              y = y.substring(y.indexOf("\"") + 1, y.lastIndexOf("\""));
            } catch (NullPointerException e) {
              y = "n";
            }


            while (true) {

              if (!nuovaStringa.equals("id")) {
                tmp.add(nomeAssociazione.substring(j, nuovaStringa.indexOf("__") + j));

                nuovaStringa = nuovaStringa.substring(nuovaStringa.indexOf("__") + 2);

                j = nomeAssociazione.indexOf(nuovaStringa);

              } else {
                j = 0;
                nuovaStringa = "";
                break;
              }
            }
            for (int k = 0; k < tmp.size() - 1; k++) {
              entityList.add(tmp.get(k));


            }
            nomeAssociazione = tmp.get(tmp.size() - 1);

            tmp = new ArrayList<String>();

            attribute = new ArrayList<String>();

            association = new AssociationBean(nomeAssociazione, attribute, entityList, x, y);
            entityList = new ArrayList<String>();

            arrayAssociation.add(association);

          }
        } else {

          //caso in cui � 'Class', cio� un'entita'

          String nomeClasse = listClass.item(i).getAttributes().getNamedItem("xmi:id").toString();
          String x;
          try {
            x = listClass.item(i).getAttributes().getNamedItem("x").toString();
            x = x.substring(x.indexOf("\"") + 1, x.lastIndexOf("\""));
          } catch (NullPointerException e) {
            x = "n";
          }
          String y;
          try {
            y = listClass.item(i).getAttributes().getNamedItem("y").toString();
            y = y.substring(y.indexOf("\"") + 1, y.lastIndexOf("\""));

          } catch (NullPointerException e) {
            y = "n";
          }
          nomeClasse = nomeClasse.substring(nomeClasse.indexOf('"') + 1,
              nomeClasse.lastIndexOf('"'));


          attribute = new ArrayList<String>();
          xattribute = new ArrayList<String>();
          yattribute = new ArrayList<String>();
          entity = new EntityBean(nomeClasse, attribute, x, y, xattribute, yattribute);
          arrayEntity.add(entity);

        }

        NodeList listAttributesClass = listClass.item(i).getChildNodes();
        int totalAttributesClass = listAttributesClass.getLength();

        // ciclo per leggere gli attributi dell'entita'
        for (int j = 0; j < totalAttributesClass; j++) {
          String attribute1 = listAttributesClass.item(j).getNodeName();
          if (attribute1.equals(ATTRIBUTES)) {
            String nomeAttributo = listAttributesClass.item(j).getAttributes()
                .getNamedItem("name").toString();
            nomeAttributo = nomeAttributo.substring(nomeAttributo.indexOf('"') + 1,
                nomeAttributo.lastIndexOf('"'));
            String xattributo;
            String yattributo;
            try {
              xattributo = listAttributesClass.item(j).getAttributes()
                  .getNamedItem("x").toString();
              xattributo = xattributo.substring(xattributo.indexOf('"') + 1,
                  xattributo.lastIndexOf('"'));
            } catch (Exception e) {
              xattributo = "n";
            }
            try {
              yattributo = listAttributesClass.item(j).getAttributes()
                  .getNamedItem("y").toString();
              yattributo = yattributo.substring(yattributo.indexOf('"') + 1,
                  yattributo.lastIndexOf('"'));
            } catch (Exception e) {
              yattributo = "n";
            }
            entity.getAttribute().add(nomeAttributo);
            entity.getxAttribute().add(xattributo);
            entity.getyAttribute().add(yattributo);
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
    //modello.stampa();
    return modello;
  }
}
