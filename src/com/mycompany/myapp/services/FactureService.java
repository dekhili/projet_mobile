/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Lignepanier;
import com.mycompany.myapp.entities.Product;


/**
 *
 * @author gogo-
 */
public class FactureService {

    public ArrayList<Facture> fact;
    
    public static FactureService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private FactureService() {
         req = new ConnectionRequest();
    }

    public static FactureService getInstance() {
        if (instance == null) {
            instance = new FactureService();
        }
        return instance;
    }
    
    public void newfact(Facture f) {
            ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/projet_3a/symfony/web/app_dev.php/panier/api/newfactureapi?numtel="+f.getNumtel()+"&adresse="+f.getAdresse()+"&dateDeLivraison="+f.getDatedelivraison();
       req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
         
        });
       
        NetworkManager.getInstance().addToQueue(req);
    }
     
   
public boolean addFacture(Facture f) {
        String url = Statics.BASE_URL + "/facture/" + f.getAdresse() + "/" + f.getNumtel()+ "/" + f.getDatedelivraison()+ "/" + f.getEtat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

   public ArrayList<Facture> parseFacture(String jsonText){
        try {
            fact=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture f = new Facture();
                float id = Float.parseFloat(obj.get("id").toString());
                f.setIdfact((int)id);
                f.setAdresse(obj.get("Adresse").toString());
                f.setNumtel(obj.get("numtel").toString());
                f.setDatedelivraison(obj.get("dateDeLivraison").toString());
                f.setEtat(Integer.parseInt(obj.get("etat").toString()));
                fact.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return fact;
    }
    
    public ArrayList<Facture> getAllTasks(){
        String url = Statics.BASE_URL+"/facture/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fact = parseFacture(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fact;
    }

   
}
