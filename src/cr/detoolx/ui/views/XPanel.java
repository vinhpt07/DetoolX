/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.views;

/**
 *
 * @author thanhvinh.phan
 */
import java.awt.Container;
import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import org.swixml.SwingEngine;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 * This file contains proprietary information of CarlsbadCubes
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2002-2003
 *
 *
 *
 * Date: Feb 28, 2003
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since
 */

public class XPanel extends Container {

  private SwingEngine swix = new SwingEngine( this );


  public void setXml(String resource) {
    try {
            String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            path += "/.././xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document doc = db.parse(new File(path).getCanonicalPath() + resource);
            InputStream is = new FileInputStream("C:\\Documents and Settings\\thanhvinh.phan\\My Documents\\NetBeansProjects\\DetoolX\\build\\xml\\menu.xml");
      swix.insert(doc, this );

    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }
  }
}
