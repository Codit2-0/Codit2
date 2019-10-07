package control;

import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.lang.String;
 
public class Parser {
 
    final static String ATTRIBUTES = "ownedAttribute";
    final static String CLASSES = "packagedElement"; 
    final static String FILE_ADDRESS = "/Users/antoniocimino/git/Codit2/CODIT/src/test.xmi";
 
    public static void main(String args[]) {
        try {
 
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(FILE_ADDRESS));
 
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());
 
            NodeList listClass = doc.getElementsByTagName(CLASSES);
            int totalClass = listClass.getLength();
            System.out.println("Total Class : " + totalClass);
            
            for(int i = 0; i < totalClass;i++) {
            	
                String type =  listClass.item(i).getAttributes().getNamedItem("xmi:type").toString();
            	if(type.equals("xmi:type=\"uml:Association\"")) {
            		System.out.println("Association : " + listClass.item(i).getAttributes().getNamedItem("xmi:id"));
            	} else {
            		System.out.println("Class : " + listClass.item(i).getAttributes().getNamedItem("name"));
            	}
        
            	NodeList listAttributesClass = listClass.item(i).getChildNodes();
            	int totalAttributesClass = listAttributesClass.getLength();
            	
            	for(int j = 0; j < totalAttributesClass; j++) {
            		String Attribute = listAttributesClass.item(j).getNodeName();
            		if(Attribute.equals(ATTRIBUTES)) {
                        System.out.println("Attribute : " + listAttributesClass.item(j).getAttributes().getNamedItem("name"));
            		}
            	}
            	
            	System.out.println("");
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
    }
}
