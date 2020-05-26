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
import com.mycompany.entities.Panier;
import com.mycompany.services.ServicePanier;
import com.mycompany.services.ServiceProduit;

/**
 *
 * @author Mohamed
 */
public class CellulePanier extends Form{
    
    Form current;

    public CellulePanier(Panier panier) {
        
        current = this;
        
        Container c0 = new Container(BoxLayout.x());
        Label lbId = new Label("Id : ");
        Label lbId2 = new Label(String.valueOf(panier.getId()));
        c0.add(lbId);
        c0.add(lbId2);
        
        Container c1 = new Container(BoxLayout.x());
        Label lbNom = new Label("Nom : ");
        Label lbNom2 = new Label(panier.getNom());
        c1.add(lbNom);
        c1.add(lbNom2);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbQuantite = new Label("Quantite : ");
        Label lbQuantite2 = new Label(String.valueOf(panier.getQuantite()));
        c2.add(lbQuantite);
        c2.add(lbQuantite2);
        
        Container c3 = new Container(BoxLayout.x());
        Button btSupprimer = new Button("Supprimer");
        Button btInc = new Button("Inc");
        Button btDec = new Button("Dec");
        c3.addAll(btSupprimer, btInc, btDec);
        
        btSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("supp");
                System.out.println(ServicePanier.getInstance().delete(Integer.parseInt(lbId2.getText())));
                new ListePaniersForm(current).show();
            }
        });
        btInc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("inc");
                System.out.println(ServicePanier.getInstance().inc(Integer.parseInt(lbId2.getText()), 1));
                new ListePaniersForm(current).show();
            }
        });
        btDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("dec");
                System.out.println(ServicePanier.getInstance().dec(Integer.parseInt(lbId2.getText()), 0));
                new ListePaniersForm(current).show();
            }
        });
        
        
        Container c_f = new Container(BoxLayout.y());
        c_f.addAll(c0, c1, c2, c3);
        
        add(c_f);
    }
    
}
