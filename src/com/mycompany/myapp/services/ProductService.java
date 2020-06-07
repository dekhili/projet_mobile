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
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cyrine
 */
public class ProductService {
     public ArrayList<Product> produit;
    
    public static ProductService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ProductService() {
         req = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

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
  
  
    public ArrayList<Product> parseProduit(String jsonText){
        try {
            produit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("root");
            for(Map<String,Object> obj : list){
                Product p = new Product();
             
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNompr(obj.get("nompr").toString());
                p.setDescrip(obj.get("descrip").toString());
                float qte = Float.parseFloat(obj.get("quantity").toString());
                p.setQuantity((int)qte);
                p.setPrix(Float.parseFloat(obj.get("prix").toString()));
                p.setImage(obj.get("image").toString());
               /*  Map<String, Object> u = (Map) obj.get("category");
                p.setIdCategory((int) Double.parseDouble(u.get("id").toString()));*/
                produit.add(p);
            }
        } catch (IOException ex) {           
        }
        return produit;
    }
    
    
     public ArrayList<Product> getAllProduit(){ 
       String url = Statics.BASE_URL + "/product/listProductIndexapi";
        req.setUrl(url);
        req.setPost(false);
        System.out.print(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
}
