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
import com.mycompany.entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class ServiceProduit {
    
    public ArrayList<Produit> produits;
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;
    
    public ServiceProduit() {
         connectionRequest = new ConnectionRequest();
    }
    
    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
    public ArrayList<Produit> parseProduits(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit produit = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                produit.setId((int)id);
                produit.setNom(obj.get("nom").toString());
                produit.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                
                produits.add(produit);
            }
            
            
        } catch (IOException ex) {
            
        }
        return produits;
    }
    
    public ArrayList<Produit> getAllProduits(){
        //String url = "http://127.0.0.1/webb/PI3/web/app_dev.php/shop_client/readProduit";
        String url = "http://localhost/webb/test/PI3/web/app_dev.php/shop_client/readProduit";
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return produits;
    }
    
    public boolean addProduit(int id, int idClient) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/createPanier/" + id + "/" + idClient;
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
