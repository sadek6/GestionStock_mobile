/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Panier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Mohamed
 */
public class ServicePanier {
    
    public ArrayList<Panier> paniers;
    public static ServicePanier instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;
    
    private ServicePanier() {
         connectionRequest = new ConnectionRequest();
    }
    
    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }
    
    public ArrayList<Panier> parsePaniers(String jsonText){
        try {
            
            paniers=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Panier panier = new Panier();
                float id = Float.parseFloat(obj.get("id").toString());
                panier.setId((int)id);
                panier.setQuantite((int)Float.parseFloat(obj.get("quantite").toString()));
                panier.setNom(obj.get("produit").toString());
                
                
                Map<String, Object> listRecup = null;
                
                if(obj.get("produit")!=null){
                    listRecup=(Map<String, Object>) obj.get("produit");
                    panier.setNom(listRecup.get("nom").toString());
                }
                
                
                paniers.add(panier);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return paniers;
    }
    
    public ArrayList<Panier> getAllPaniers(){
        //String url = "http://127.0.0.1/webb/PI3/web/app_dev.php/shop_client/readPanier/1";
        String url = "http://localhost/webb/test/PI3/web/app_dev.php/shop_client/readPanier/1";
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                paniers = parsePaniers(new String(connectionRequest.getResponseData()));
                
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        /*for (Panier panier : paniers) {
            System.out.println("--"+panier);
        }*/
        return paniers;
    }
    
    public boolean inc(int id, int mod) {
        //String url = "http://127.0.0.1/webb/PI3/web/app_dev.php/shop_client/updatePanier/" + id + "/" + mod;
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/updatePanier/" + id + "/" + mod;
        
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;
    }
    
    public boolean dec(int id, int mod) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/updatePanier/" + id + "/" + mod;
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;
    }
    
    public boolean delete(int id) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/deletePanier/" + id;
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;
    }
}
