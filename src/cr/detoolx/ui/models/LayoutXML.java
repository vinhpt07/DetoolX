/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.models;

import org.w3c.dom.Document;
import detoolx.util.XMLDocumentIterator;
import java.io.IOException;

/**
 *
 * @author thanhvinh.phan
 */
public class LayoutXML {
    
    public static final String DEFAULT_LAYOUT = "mainLayout";
    public static final String DEFAULT_RESOURCE = "xml";
    
    private final XMLDocumentIterator xmlDoc;

    public LayoutXML() throws IOException {
        this.xmlDoc = new XMLDocumentIterator(LayoutXML.DEFAULT_LAYOUT,LayoutXML.DEFAULT_RESOURCE);
    }
    
    public Document getXML() throws Exception{
        
            return xmlDoc.getXML();
            
    }
    


}
