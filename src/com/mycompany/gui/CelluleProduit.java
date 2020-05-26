/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Mohamed
 */
public class CelluleProduit extends Form{
    
    Form current;

    public CelluleProduit(Produit produit) {
        
        current = this;
        
        Container c1 = new Container(BoxLayout.x());
        Label lbNom = new Label("Nom : ");
        Label lbNom2 = new Label(produit.getNom());
        c1.add(lbNom);
        c1.add(lbNom2);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbPrix = new Label("Prix : ");
        Label lbPrix2 = new Label(String.valueOf(produit.getPrix()));
        c2.add(lbPrix);
        c2.add(lbPrix2);
        
        Container c3 = new Container(BoxLayout.x());
        Label lbId = new Label("Id : ");
        Label lbId2 = new Label(String.valueOf(produit.getId()));
        //lbId2.setHidden(true);
        c3.add(lbId);
        c3.add(lbId2);
        
        Button btAjouter = new Button("ajouter");
        c2.add(btAjouter);
        
        btAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("ok"+lbId2.getText());
                System.out.println(ServiceProduit.getInstance().addProduit(Integer.parseInt(lbId2.getText()), 1));
                Dialog.show("Produit", "Produit ajouté au panier", "OK", null);
            }
        });
        
        Container c_f = new Container(BoxLayout.y());
        c_f.addAll(c1, c2, c3);
        
        
        add(c_f);
    }
    
    
    
}
