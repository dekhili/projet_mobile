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
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Lignepanier;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.gui.PanierForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cyrine
 */
public class LignePanierService {
    
     public ArrayList<Lignepanier> lignepanier;
    
    public static LignePanierService instance=null;
    public boolean resultOK;
    public ConnectionRequest req;

    public LignePanierService() {
          req = new ConnectionRequest();
    }

    public static LignePanierService getInstance() {
        if (instance == null) {
            instance = new LignePanierService();
        }
        return instance;
    }
    
    /*    LignePanierService es = new LignePanierService();
        List<Lignepanier> listLigne = new ArrayList<>();
        listLigne = es.getAlllignePanier();
        
      
        
         Button btnsup = new Button("btn");
          for (Lignepanier e : listLigne) {
          btnsup.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   
              Product t=new Product(1);
              es.addligne(t);
              
          }
                
           });
        
          }
         
         current.add(btnsup);*/

     public void addligne(Product t) {
            ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/"+t.getId()+"/addProductToPanierapi";
       req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
         
        });
       
        NetworkManager.getInstance().addToQueue(req);
    }
     
     
     public void suppligne(Lignepanier l ,int idligne) {
           ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/"+idligne+"/deleteProductapi";
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
         
        });
       
        NetworkManager.getInstance().addToQueue(req);
    }

     public void DecQTE(Lignepanier l ,int idligne) {
           ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/"+idligne+"/DecreaseProductQTEapi";
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
        });
        NetworkManager.getInstance().addToQueue(req);
    }
     
     public void IncQTE(Lignepanier l ,int idligne) {
           ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/panier/"+idligne+"/increaseProductQTEapi";
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
          });
       NetworkManager.getInstance().addToQueue(req);
    }
  
  
    public ArrayList<Lignepanier> parseLignePanier(String jsonText){
        try {
            lignepanier=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Lignepanier t = new Lignepanier();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setIdlp((int)id);
                
                Map<String, Object> p = (Map) obj.get("product");
                 t.setProduct_id((int) Double.parseDouble(p.get("id").toString()));
                 t.setNompr(p.get("nompr").toString());
                 t.setDescrip(p.get("descrip").toString());
                 t.setPrix(Double.parseDouble(p.get("prix").toString()));
                 t.setImage(p.get("image").toString());
                 
                Map<String, Object> pa = (Map) obj.get("panier");
                 t.setPanier_id((int) Double.parseDouble(pa.get("id").toString()));
                 t.setPrixTotal(Double.parseDouble(pa.get("prixTotal").toString()));
                 
                 float qte = Float.parseFloat(obj.get("quantite").toString());
                t.setQuantite((int)qte);
                lignepanier.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return lignepanier;
    }
    
 
     public ArrayList<Lignepanier> getAlllignePanier(){
      //  String url = "http://41.226.11.252:11300/tasks/";
      String url = Statics.BASE_URL + "/panier/aff";
      req.setUrl(url);
      req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignepanier = parseLignePanier(new String(req.getResponseData()));
                System.out.print(lignepanier);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignepanier;
    }
}
