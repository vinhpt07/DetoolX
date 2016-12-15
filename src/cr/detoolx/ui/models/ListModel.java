/*
 *  Copyright (C) 2016 Vinh Phan.
 *  All rights reserved.
 * 
 *  1.DetoolX is combination of words Detool,means Data Entry Tool,
 *  and X may use for Project Name.
 *  2.Source code is asset of R&D department CRUNCH- branch Business 
 *  Unit of Offience.
 */

package cr.detoolx.ui.models;

import javax.swing.DefaultListModel;

/**
 *
 * @author thanhvinh.phan
 */
public class ListModel extends DefaultListModel<String> {
    
    //public static ListModel that = new ListModel();
    public ListModel(){
        for(String s : new String[] {"BEATY","CULTURE","FASHION","FOOD","FOOD DISTRICT","GIFTS & ACCESSORIES",
        "HOME","LEISURE & SPORTS","MULTIMEDIA","SERVICE","SHOES&LEATHER"}){
            this.addElement(s);
        }
        
    }
    
}
