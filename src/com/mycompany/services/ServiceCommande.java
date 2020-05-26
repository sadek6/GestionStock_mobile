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
import com.mycompany.entities.Commande;
import static com.mycompany.services.ServiceAdresse.instance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class ServiceCommande {
    public ArrayList<Commande> commandes;
    public static ServiceCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;
    
    private ServiceCommande() {
         connectionRequest = new ConnectionRequest();
    }
    
    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }
    
    public boolean createCommande(int idAdresse, int idClient) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/createCommande/" + idAdresse + "/" + idClient;
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
    
    public ArrayList<Commande> parseCommandes(String jsonText){
        try {
            
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Commande commande = new Commande();
                float id = Float.parseFloat(obj.get("id").toString());
                commande.setId((int)id);
                commande.setPrix((int)Float.parseFloat(obj.get("prixtotal").toString()));
                commande.setEtat(obj.get("etat").toString());
                
                
                
                
                
                commandes.add(commande);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return commandes;
    }
    
    public ArrayList<Commande> getAllCommandeClient(int idClient){
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/readCommande/" + idClient;
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(connectionRequest.getResponseData()));
                
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        /*for (Panier panier : paniers) {
            System.out.println("--"+panier);
        }*/
        return commandes;
    }
    
    public boolean delete(int id) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/deleteCommande/" + id;
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
