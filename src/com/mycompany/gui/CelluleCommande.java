/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Adresse;
import com.mycompany.entities.Commande;
import com.mycompany.services.ServiceAdresse;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServicePanier;

/**
 *
 * @author Mohamed
 */
public class CelluleCommande extends Form{
    
    Form current;

    public CelluleCommande(Commande commande) {
        
        current = this;
        
        Container c0 = new Container(BoxLayout.x());
        Label lbId = new Label("Id : ");
        Label lbId2 = new Label(String.valueOf(commande.getId()));
        c0.add(lbId);
        c0.add(lbId2);
        
        Container c1 = new Container(BoxLayout.x());
        Label lbNom = new Label("Prix : ");
        Label lbNom2 = new Label(String.valueOf(commande.getPrix()));
        c1.add(lbNom);
        c1.add(lbNom2);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbQuantite = new Label("Etat : ");
        Label lbQuantite2 = new Label(commande.getEtat());
        c2.add(lbQuantite);
        c2.add(lbQuantite2);
        
        Button btSupprimer = new Button("Supprimer");
        btSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("supp");
                System.out.println(ServiceCommande.getInstance().delete(Integer.parseInt(lbId2.getText())));
                new ListeCommandeForm().show();
            }
        });
        
        Button btModAdresse = new Button("mod adresse");
        btModAdresse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Adresse adresse = ServiceAdresse.getInstance().getAdresse(Integer.parseInt(lbId2.getText()));
                new AdresseModForm(adresse, Integer.parseInt(lbId2.getText())).show();
            }
        });
        
        Container c_f = new Container(BoxLayout.y());
        c_f.addAll(c0, c1, c2, btSupprimer,btModAdresse);
        
        add(c_f);
    }
    
}
