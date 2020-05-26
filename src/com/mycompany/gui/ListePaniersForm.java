/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Panier;
import com.mycompany.services.ServicePanier;

/**
 *
 * @author Mohamed
 */
public class ListePaniersForm extends Form{
    
    Form current;
    

    public ListePaniersForm(Form previous) {
        current = this;
        
        Container liste = new Container(BoxLayout.y());
        for (Panier panier : ServicePanier.getInstance().getAllPaniers()) {
            CellulePanier cellulePanier = new CellulePanier(panier);
            liste.add(cellulePanier);
        }
        
        
        
        
        Button btValider = new Button("Valider");
        btValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AdresseForm().show();
            }
        });
        
        liste.add(btValider);
        
        add(liste);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().show());
    }
    
}
