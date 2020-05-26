/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Commande;
import com.mycompany.services.ServiceCommande;

/**
 *
 * @author Mohamed
 */
public class ListeCommandeForm extends Form{
    
    Form current;
    

    public ListeCommandeForm() {
        current = this;
        
        Container liste = new Container(BoxLayout.y());
        for (Commande commande : ServiceCommande.getInstance().getAllCommandeClient(1)) {
            CelluleCommande celluleCommande = new CelluleCommande(commande);
            liste.add(celluleCommande);
        }
        
        add(liste);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().show());
    }
    
}
