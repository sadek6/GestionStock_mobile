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
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Mohamed
 */
public class ListeProduitsForm extends Form{
    
    Form current;
    

    public ListeProduitsForm(Form previous) {
        current = this;
        
        Container liste = new Container(BoxLayout.y());
        for (Produit produit : ServiceProduit.getInstance().getAllProduits()) {
            CelluleProduit celluleProduit = new CelluleProduit(produit);
            liste.add(celluleProduit);
        }
        
        add(liste);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
