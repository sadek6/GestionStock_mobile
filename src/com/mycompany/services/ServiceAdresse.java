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
import com.mycompany.entities.Adresse;
import com.mycompany.entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class ServiceAdresse {
    
    public ArrayList<Adresse> adresses;
    public static ServiceAdresse instance=null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;
    
    private ServiceAdresse() {
         connectionRequest = new ConnectionRequest();
    }
    
    public static ServiceAdresse getInstance() {
        if (instance == null) {
            instance = new ServiceAdresse();
        }
        return instance;
    }
    
    public ArrayList<Adresse> parseAdresse(String jsonText){
        try {
            adresses=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Adresse adresse = new Adresse();
                float id = Float.parseFloat(obj.get("id").toString());
                adresse.setId((int)id);
                adresse.setNumTel((int)Float.parseFloat(obj.get("numTel").toString()));
                adresse.setMail(obj.get("mail").toString());
                adresse.setPays(obj.get("pays").toString());
                adresse.setVille(obj.get("ville").toString());
                adresse.setPinCode((int)Float.parseFloat(obj.get("pinCode").toString()));
                
                adresses.add(adresse);
            }
            
            
        } catch (IOException ex) {
            
        }
        return adresses;
    }
    
    public int addAdresse(Adresse adresse) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/createAdresse?numTel="+adresse.getNumTel()+"&mail="+adresse.getMail()+"&pays="+adresse.getPays()+"&ville="+adresse.getVille()+"&pinCode="+adresse.getPinCode();
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                adresses = parseAdresse(new String(connectionRequest.getResponseData()));
                for (Adresse adresse1 : adresses) {
                    System.out.println(adresse1);
                }
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return adresses.get(0).getId();
    }
    
    public int addAdresse2(Adresse adresse, int idA) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/createAdresse?numTel="+adresse.getNumTel()+"&mail="+adresse.getMail()+"&pays="+adresse.getPays()+"&ville="+adresse.getVille()+"&pinCode="+adresse.getPinCode()+"&idA"+idA;
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                adresses = parseAdresse(new String(connectionRequest.getResponseData()));
                for (Adresse adresse1 : adresses) {
                    System.out.println(adresse1);
                }
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return adresses.get(0).getId();
    }
    
    
    public Adresse getAdresse(int idC){
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/modeAdresse/"+idC;
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                adresses = parseAdresse(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return adresses.get(0);
    }
    
    public int createModAdresse(Adresse adresse, int idC) {
        String url = "http://127.0.0.1/webb/test/PI3/web/app_dev.php/shop_client/createModAdresse?numTel="+adresse.getNumTel()+"&mail="+adresse.getMail()+"&pays="+adresse.getPays()+"&ville="+adresse.getVille()+"&pinCode="+adresse.getPinCode()+"&idC="+idC;
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                adresses = parseAdresse(new String(connectionRequest.getResponseData()));
                for (Adresse adresse1 : adresses) {
                    System.out.println(adresse1);
                }
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return adresses.get(0).getId();
    }
    
}
