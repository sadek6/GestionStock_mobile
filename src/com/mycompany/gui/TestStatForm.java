/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mohamed
 */
public class TestStatForm extends Form{
    
    Form current;
    
    public TestStatForm(){
        current = this;
        
        Container liste = new Container(BoxLayout.y());
        
        liste.add(new StatCommandeFrom().createPieChartForm());
        add(liste);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().show());
    
    }
}
