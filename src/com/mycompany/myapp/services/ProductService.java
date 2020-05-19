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
import com.mycompany.myapp.entities.Lignepanier;
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
     public ArrayList<Product> lignepanier;
    
    public static ProductService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ProductService() {
         req = new ConnectionRequest();
    }

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

 

  
  
    public ArrayList<Product> parseProd(String jsonText){
        try {
            lignepanier=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("root");
            for(Map<String,Object> obj : list){
                Product lp = new Product();
                
                float id = Float.parseFloat(obj.get("id").toString());
                lp.setId((int)id);
                lp.setNompr(obj.get("nompr").toString());
                 Map<String, Object> cat = (Map) obj.get("idCategory");
                lp.setIdCategory(Integer.parseInt(cat.get("id").toString()));
                //t.setEtat(Integer.parseInt(obj.get("etat").toString()));
            /*    Map<String, Object> prod = (Map) obj.get("product_id");
                lp.setProduct_id((int) Double.parseDouble(prod.get("id").toString()));
                Map<String, Object> pan = (Map) obj.get("panier_id");
                lp.setPanier_id((int) Double.parseDouble(pan.get("id").toString()));
                //t.setEtat(Integer.parseInt(obj.get("etat").toString()));
                // t.setArchive(Integer.parseInt(obj.get("archive").toString()));
                lp.setQuantite(Integer.parseInt(obj.get("prixTotal").toString()));*/
                lignepanier.add(lp);
            }
        } catch (IOException ex) {           
        }
        return lignepanier;
    }
    
    
     public ArrayList<Product> getAllProd(){
      //  String url = "http://41.226.11.252:11300/tasks/";
      String url = "http://localhost/projet%203a/symfony/web/app_dev.php/product/listproduct";
       // req.setUrl(url);
        req.setPost(false);
        System.out.print(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignepanier = parseProd(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignepanier;
    }
}
