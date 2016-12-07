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
import cr.detoolx.ui.controllers.LayoutController;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
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

public class XPanel extends JPanel{

  private SwingEngine swix = new SwingEngine(this);
  private int clicks;
    
  /** JTextField member gets instantiated through Swixml (look for id="tf" in xml descriptor) */
  public JTextField tf;

  /** Jlabel to display number of button clicks */
  public JLabel cnt;
  
  

  /** Action appends a '#' to the textfields content.  */
  public Action submit = new AbstractAction() {
    @Override
    public void actionPerformed( ActionEvent e ) {
      tf.setText( tf.getText() + '#' );
      cnt.setText(String.valueOf( ++clicks ));
    }
  };

  public void setXml(String resource) {
    try {
            String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            path += "/.././xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document doc = db.parse(new File(path).getCanonicalPath() + resource);
            //InputStream is = new FileInputStream("C:\\Documents and Settings\\thanhvinh.phan\\My Documents\\NetBeansProjects\\DetoolX\\build\\xml\\menu.xml");
            swix.cleanup();
            swix.insert(doc,this);
            swix.getIdMap().clear();

    } catch (Exception e) {
      System.err.println( e.getMessage() );
    }
  }
}
