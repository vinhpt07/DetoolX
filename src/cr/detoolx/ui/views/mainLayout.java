/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JTextField;
import cr.detoolx.ui.controllers.*;
import org.swixml.SwingEngine;
import org.w3c.dom.Document;
import org.swixml.ConverterLibrary;
/**
 *
 * @author thanhvinh.phan
 */

public class mainLayout extends SwingEngine {
 

private final LayoutController controller = new LayoutController();
  /** Renders UI at construction
     * @throws java.lang.Exception */
  public mainLayout() throws Exception { 
     
     
     this.getTaglib().registerTag("xpanel", XPanel.class);
     
     Document doc =  controller.getGUI();
     render(doc).setVisible( true );

     
  }

  public static void main(String[] args) throws Exception{
      new mainLayout();
      
  }
    

}
