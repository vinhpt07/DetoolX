/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


/**
 *
 * @author thanhvinh.phan
 */
public class LoadXML {
    
    public Document getXML() throws Exception{
        Document doc;
        try {
            String path1 = getRelativePath();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            doc = db.parse(new File(path1));
            return doc;
             
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return null;

    }
    
    private String getRelativePath() throws IOException{
            String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            path += "/.././xml/HelloWorld.xml";
            return new File(path).getCanonicalPath();
    }

}
