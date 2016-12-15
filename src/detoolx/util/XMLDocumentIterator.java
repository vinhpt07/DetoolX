/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package detoolx.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author thanhvinh.phan
 */
public class XMLDocumentIterator {

    private static String layout;
    private static String resource;
    private static Document doc1;
    private static String path;
    private static final String DEFAULT_TAG = "xpanel";
    private static boolean skipNL;
    private static final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         
    public XMLDocumentIterator(String DEFAULT_LAYOUT,String DEFAULT_RESOURCE) throws IOException {
        layout = DEFAULT_LAYOUT+".xml";
        resource = DEFAULT_RESOURCE + "/";
        path = getRelativePath(layout,resource);
    }
        
    private String getRelativePath(String layout,String resource) throws IOException {
         return getRelativePath(layout,resource,"/../");
     }
    
    private String getRelativePath(String layout,String resource, String delimiter) throws IOException{

        return new File(URLDecoder
                .decode(getClass()
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath(),"UTF-8")+ delimiter + resource + layout)
                .getCanonicalPath();
    }
    
    public Document getXML() throws Exception{
        return parseXML();
    }
    
    private Document parseXML() throws Exception{
        String newdoc = mergeXML();
        DocumentBuilder db = dbf.newDocumentBuilder(); 
        Document doc = db.parse(
                        new InputSource(
                        new StringReader(newdoc)
                        )
            ); 
        return doc;

    }
    
    private String mergeXML() throws ParserConfigurationException, IOException, SAXException{
        DocumentBuilder db = dbf.newDocumentBuilder(); 
        Document doc = db.parse(new File(path));
        return printXML(doc);        
    }
    
    private static String printXML(Node rootNode) throws IOException, ParserConfigurationException, SAXException {
        String tab = "";
        skipNL = false;
        return(printXML(rootNode, tab));
    }
    private static String printXML(Node rootNode, String tab) throws IOException, ParserConfigurationException, SAXException {
        String print = "";
        if(rootNode.getNodeType()==Node.ELEMENT_NODE && !"root".equals(rootNode.getNodeName())) {
            print += " "+tab+"<"+rootNode.getNodeName() + getAttributesAsString(rootNode.getAttributes()) + ">";
        }
        NodeList nl = rootNode.getChildNodes();

        if(DEFAULT_TAG.equals(rootNode.getNodeName())){

            NamedNodeMap attr = rootNode.getAttributes();
            Integer index = getItem(attr,"xml");
            if (index != null) {

                XMLDocumentIterator xml = new XMLDocumentIterator(attr.item(index).getNodeValue(), resource);
                String subprint = xml.mergeXML();
                    print += "\n"+subprint+"\n"; 
                }
        }

        if(nl.getLength()>0) {
            for (int i = 0; i < nl.getLength(); i++) {
                print += printXML(nl.item(i), tab+"  ");    // \t
            }
        } else { 
            if(rootNode.getNodeValue()!=null) {
                print = rootNode.getNodeValue();
            }
            skipNL = true;
        }
        if(rootNode.getNodeType()==Node.ELEMENT_NODE && !"root".equals(rootNode.getNodeName())) {
            if(!skipNL) {
                print += "\n"+tab;
            }
            skipNL = false;
            print += "</"+rootNode.getNodeName()+">";
        }
        return(print);
    }
    private static String getAttributesAsString(NamedNodeMap attributes) {
        StringBuilder sb = new StringBuilder(" ");
        for (int j = 0; j < attributes.getLength(); j++) {
            sb.append(" ").append(attributes.item(j).getNodeName()).append("=\"").append(attributes.item(j).getNodeValue()).append("\"");
        }
        return sb.toString();

    }
    
    private static Integer getItem(NamedNodeMap nl,String lookFor){          
        for(int i = 0 ; i < nl.getLength(); i++){
                if(lookFor.equals(nl.item(i).getNodeName())){                   
                    return i;
                }
            }      
        return null;     
    }
    //for testing
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        XMLDocumentIterator xml = new XMLDocumentIterator("mainLayout", "xml");
        String mergeXML = xml.mergeXML();
        System.out.println(mergeXML);
        
    }

}
