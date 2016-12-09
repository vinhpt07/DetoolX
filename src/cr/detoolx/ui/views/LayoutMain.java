/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.views;

import cr.detoolx.ui.widget.XPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JTextField;
import cr.detoolx.ui.controllers.*;
import org.swixml.SwingEngine;
import org.w3c.dom.Document;
/**
 *
 * @author thanhvinh.phan
 */

public class LayoutMain extends SwingEngine{
 
  public int clicks;
    
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

private final LayoutController controller = new LayoutController();
  /** Renders UI at construction
     * @throws java.lang.Exception */
  public LayoutMain() throws Exception { 
     
     
     this.getTaglib().registerTag("xpanel", XPanel.class);
     
     Document doc =  controller.getGUI();
     Container a = render(doc);
     a.setVisible( true );
  }

  public static void main(String[] args) throws Exception{
      new LayoutMain();     
  }
    

}
