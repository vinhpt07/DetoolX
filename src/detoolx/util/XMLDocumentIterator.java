/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package detoolx.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author thanhvinh.phan
 */
public class XMLDocumentIterator {

    private final String layout;
    private final String resource;
    private static Document doc;
    private final String path;
            
    public XMLDocumentIterator(String DEFAULT_LAYOUT,String DEFAULT_RESOURCE) throws IOException {
        this.layout = DEFAULT_LAYOUT+".xml";
        this.resource = DEFAULT_RESOURCE + "/";
        this.path = getRelativePath(this.layout,this.resource);
    }
        
    public Document getXML() throws Exception{
        return parseXML();
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

    
    private Document parseXML() throws Exception{
        Document newdoc = mergeXML();
        return doc;

    }
    
    private Document mergeXML() throws ParserConfigurationException, IOException, SAXException{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            doc = db.parse(new File(path));
            doc.getDocumentElement().normalize();
            doc.getDocumentElement().getNodeName();
            System.out.println(printXML(doc)); 
        
        return null;
        
      
    }
    private static boolean skipNL;
private static String printXML(Node rootNode) {
    String tab = "";
    skipNL = false;
    return(printXML(rootNode, tab));
}
private static String printXML(Node rootNode, String tab) {
    String print = "";
    if(rootNode.getNodeType()==Node.ELEMENT_NODE) {
        print += " "+tab+"<"+rootNode.getNodeName() + getAttributesAsString(rootNode.getAttributes()) + ">";
    }
    NodeList nl = rootNode.getChildNodes();
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
    if(rootNode.getNodeType()==Node.ELEMENT_NODE) {
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
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        XMLDocumentIterator xml = new XMLDocumentIterator("mainLayout", "xml");
        Document mergeXML = xml.mergeXML();
    }

}
