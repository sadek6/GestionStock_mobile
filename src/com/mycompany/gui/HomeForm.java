/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Mohamed
 */
public class HomeForm extends Form{
    
    Form current;
    

    public HomeForm() {
        current = this;
        
        Toolbar tb = current.getToolbar();
        
        
        
        tb.addMaterialCommandToSideMenu("Shop", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new ListeProduitsForm(current).show();
        });
        
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new ListePaniersForm(current).show();
        });
        
        tb.addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new ListeCommandeForm().show();
        });
        
        tb.addMaterialCommandToSideMenu("Statistique etat commande", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new TestStatForm().show();
        });
        
        
        
    }
    
}
