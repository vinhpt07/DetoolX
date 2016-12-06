/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cr.detoolx.ui.controllers;
import cr.detoolx.ui.views.*;
import cr.detoolx.ui.models.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author thanhvinh.phan
 */
public class LayoutController {
    
    public void startApplication(){
        mainLayout view;
        try {
            view = new mainLayout();
            
        } catch (Exception ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Document getGUI(){
        try {
            LoadXML xml = new LoadXML();
            return xml.getXML();
        } catch (Exception ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
}
