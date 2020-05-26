/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui2;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceProduit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mohamed
 */
public class ListeProduits {
    
    Form f;

    EncodedImage encImg;
    Image img;
    ImageViewer imgV;

    Container global;
    ArrayList<Produit> produits;
    ServiceProduit serviceProduit = new ServiceProduit();
    
    public ListeProduits(ArrayList<Produit> produits) throws IOException{
        
        Toolbar.setGlobalToolbar(true);
        f = new Form("Nos Produits.", new BoxLayout(BoxLayout.Y_AXIS));
        
        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        f.add(slider);
        
        this.liste(produits);
    }
    
    public void liste(ArrayList<Produit> produits) throws IOException {

        for (Produit item : produits) {
            

                global = new Container(new BoxLayout(BoxLayout.X_AXIS));

                Container Right = new Container(new FlowLayout(Component.LEFT));
                Container Left = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                global.add(Right);
                global.add(Left);

                f.add(global);
                Slider sld = new Slider();
                sld.setPreferredSize(new Dimension(256, 2));
                f.add(sld);

                Label lab_nom = new Label(item.getNom() + ".");
                Label lab_prix = new Label(String.valueOf(item.getPrix()) + " DT.");

                Button detail_btn = new Button("Details.");

                detail_btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        /*try {
                            DetailProduit_Client detail_prod;
                            detail_prod = new DetailProduit_Client(item.getIdProduit());
                        } catch (IOException ex) {
                        }*/

                    }
                });

                //encImg = EncodedImage.create("/giphy.gif");
                imgV = new ImageViewer();
               // String url = "http://localhost/Medina_VersionFinale_web/web/uploads/ImgProduit/" + item.getUrlImgProduit();

                //img = URLImage.createToStorage(encImg, item.getUrlImgProduit(), url);
                img = img.scaled(125, 100);
                imgV.setImage(img);

                Right.add(imgV);

                Left.add(lab_nom);
                //Left.add(lab_categorie);
                Left.add(lab_prix);
                Left.add(detail_btn);

            
        }

    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
