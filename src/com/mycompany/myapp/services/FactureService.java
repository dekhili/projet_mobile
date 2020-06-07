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
import com.mycompany.myapp.entities.Panier;
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

    public FactureService() {
         req = new ConnectionRequest();
    }

    public static FactureService getInstance() {
        if (instance == null) {
            instance = new FactureService();
        }
        return instance;
    }
    
    public boolean newfact(Facture f) {
            ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/newfactureapi?numtel="+f.getNumtel()+"&adresse="+f.getAdresse()+"&dateDeLivraison="+f.getDatedelivraison();
      System.out.println(url);
      req.setUrl(url);
     req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        }
    
    public void payer(Facture f ,int idfacture) {
           ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/"+idfacture+"/paiementapi";
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
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
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("root");
            for(Map<String,Object> obj : list){
                Facture f = new Facture();
                System.out.println(obj.get("id").toString());
                 float id = Float.parseFloat(obj.get("id").toString());
                f.setIdfact((int)id);
              /*   Map<String, Object> pan = (Map) obj.get("panier");
                f.setPanier_id((int) Double.parseDouble(pan.get("id").toString()));*/
                f.setAdresse(obj.get("adresse").toString());
                f.setNumtel(obj.get("numtel").toString());
                f.setDatedelivraison(obj.get("dateDeLivraison").toString());
                f.setEtat(Boolean.parseBoolean(obj.get("etat").toString()));
                fact.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return fact;
    }
    
    public void getFacture(Facture f ,int idfact){
         ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/panier/"+idfact+"/showapi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
        });
        NetworkManager.getInstance().addToQueue(req);
    }

    public ArrayList<Facture> getAllFacture(){
      String url = Statics.BASE_URL + "/panier/allfacture";
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
