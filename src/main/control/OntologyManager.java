package control;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.impl.OntModelImpl;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.NullAppender;

import model.AssociationBean;
import model.ErBean;
import model.EntityBean;
import model.HierarchyBean;

/**
 * Classe per la persistenza mediante un'ontologia .OWL.
 */
public class OntologyManager {

  public static final Logger logger = LogManager.getLogger(OntologyManager.class);

  public static final String SOURCE = "http://www.unisalab.it/codit/tboxER";
  public static final String BASE = SOURCE + "#";
  public static final String PATH = "http://localhost:8080/Codit2-0/util/tbox.owl";

  public static final String ENTITY = "Entity";
  public static final String ATTRIBUTE = "Attribute";
  public static final String RELATIONSHIP = "OneToOneRelationship";
  public static final String HIERARCHY = "Hierarchy";


  private OntModelImpl model;

  /**
   * costruttore.
   */
  public OntologyManager() {
    BasicConfigurator.configure(new NullAppender());
    model = new OntModelImpl(OntModelSpec.OWL_DL_MEM_RULE_INF);
    model.read(PATH, "RDF/XML");
  }

  /**
   * Metodo che permette di salvare un {@link ErBean} con il nome title in un ontologia.
   * @param bean Bean della classe {@link ErBean}.
   * @param title Il nome del file in cui salvare (se esiste, lo sovrascrive).
   */
  public void save(ErBean bean, String title) {


    try {
      FileOutputStream out = new FileOutputStream(title + ".owl");


      OntClass classed = model.getOntClass(BASE + ENTITY);
      OntClass subClass;
      ObjectProperty property = model.getObjectProperty(BASE + "hasAttribute");
      Individual entity;
      Individual attribute;
      for (Iterator<EntityBean> i = bean.getEntity().iterator(); i.hasNext(); ) {
        EntityBean eb = i.next();
        entity = model.createIndividual(BASE + eb.getName(), classed);

        subClass = model.getOntClass(BASE + ATTRIBUTE);
        for (Iterator<String> j = eb.getAttribute().iterator(); j.hasNext(); ) {
          attribute = model.createIndividual(BASE + j.next(), subClass);
          model.add(new StatementImpl(entity, property, attribute));
        }

      }

      Individual relation;
      classed = model.getOntClass(BASE + RELATIONSHIP);
      property = model.getObjectProperty(BASE + "hasRelation");
      subClass = model.getOntClass(BASE + ENTITY);
      for (Iterator<AssociationBean> i = bean.getAssociation().iterator(); i.hasNext(); ) {
        AssociationBean ab = i.next();
        relation = model.createIndividual(BASE + ab.getName(), classed);
        for (Iterator<String> j = ab.getEntity().iterator(); j.hasNext(); ) {
          entity = model.getIndividual(BASE + j.next());
          model.add(new StatementImpl(relation, property, entity));
        }
      }

      Individual hierarchy;
      classed = model.getOntClass(BASE + HIERARCHY);
      subClass = model.getOntClass(BASE + "FatherEntity");
      OntClass sonSubClass = model.getOntClass(BASE + "DaughterEntity");
      property = model.getObjectProperty(BASE + "hasFather");
      ObjectProperty sonProperty = model.getObjectProperty(BASE + "hasDaughter");
      
      for (Iterator<HierarchyBean> i = bean.getHierarchy().iterator(); i.hasNext(); ) {
        HierarchyBean hb = i.next();
        hierarchy = model.createIndividual(BASE + hb.getFather() + HIERARCHY, classed);
        entity = model.getIndividual(BASE + hb.getFather());
        if (entity.hasOntClass(sonSubClass)) {
          entity.addOntClass(subClass);
        } else {
          entity.setOntClass(subClass);
        }
        model.add(new StatementImpl(hierarchy, property, entity));
        
        ArrayList<String> sons = hb.getSons();
        for (int j = 0; j < sons.size(); j++) {
          entity = model.getIndividual(BASE + sons.get(j));
          if (entity.hasOntClass(subClass)) {
            entity.addOntClass(sonSubClass);
          } else {
            entity.setOntClass(sonSubClass);
          }
          model.add(new StatementImpl(hierarchy, sonProperty, entity));
        }
        
      }

      model.write(out);
      out.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


}
