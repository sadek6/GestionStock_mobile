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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Adresse;
import com.mycompany.services.ServiceAdresse;
import com.mycompany.services.ServiceCommande;

/**
 *
 * @author Mohamed
 */
public class AdresseModForm extends Form{
    
    Form current;
    
    public AdresseModForm(Adresse adresse, int idC) {
        System.out.println("****");
        Container c0 = new Container(BoxLayout.x());
        Label lbNumTel = new Label("Num tel : ");
        TextField txNumTel = new TextField("", "num tel");
        txNumTel.setText(String.valueOf(adresse.getNumTel()));
        c0.add(lbNumTel);
        c0.add(txNumTel);
        
        Container c1 = new Container(BoxLayout.x());
        Label lbMail = new Label("Mail : ");
        TextField txMail = new TextField("", "mail");
        txMail.setText(adresse.getMail());
        c1.add(lbMail);
        c1.add(txMail);
        
        Container c2 = new Container(BoxLayout.x());
        Label lbPays = new Label("Pays : ");
        TextField txPays = new TextField("", "pays");
        txPays.setText(adresse.getPays());
        c2.add(lbPays);
        c2.add(txPays);
        
        Container c3 = new Container(BoxLayout.x());
        Label lbVille = new Label("Ville : ");
        TextField txVille = new TextField("", "ville");
        txVille.setText(adresse.getVille());
        c3.add(lbVille);
        c3.add(txVille);
        
        Container c4 = new Container(BoxLayout.x());
        Label lbPin = new Label("Pin Code : ");
        TextField txPin = new TextField("", "pin code");
        txPin.setText(String.valueOf(adresse.getPinCode()));
        c4.add(lbPin);
        c4.add(txPin);
        
        Container c_f = new Container(BoxLayout.y());
        c_f.addAll(c0, c1, c2, c3, c4);
        
        Button btValider = new Button("Valider");
        
        btValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("begin add adresse");
                Adresse adresse = new Adresse();
                adresse.setNumTel(Integer.parseInt(txNumTel.getText()));
                adresse.setMail(txMail.getText());
                adresse.setPays(txPays.getText());
                adresse.setVille(txVille.getText());
                adresse.setPinCode(Integer.parseInt(txPin.getText()));
                int idAdresse = ServiceAdresse.getInstance().createModAdresse(adresse, idC);
                //ServiceCommande.getInstance().createCommande(idAdresse,1);
                System.out.println("end add adresse");
                new HomeForm().show();
            }
        });
        c_f.add(btValider);
        
        add(c_f);
    }
    
    
    
}
