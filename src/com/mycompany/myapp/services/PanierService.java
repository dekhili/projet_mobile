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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cyrine
 */
public class PanierService {
     public ArrayList<Panier> panier;
    
    public static PanierService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public PanierService() {
         req = new ConnectionRequest();
    }

    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }


  
  
    public ArrayList<Panier> parsePanier(String jsonText){
        try {
            panier=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ListJson.get("root");
            for(Map<String,Object> obj : list){
                Panier p = new Panier();
            /*    Map<String, Object> u = (Map) obj.get("user");
                float id = Float.parseFloat(obj.get("id").toString());
                p.setIdpan((int)id);
                p.setUser((int) Double.parseDouble(u.get("id").toString()));
                p.setDatepanier(obj.get("datePanier").toString());
                p.setEtat((boolean) Boolean.parseBoolean(obj.get("etat").toString()));
                //t.setEtat(Integer.parseInt(obj.get("etat").toString()));
                // t.setArchive(Integer.parseInt(obj.get("archive").toString()));*/
                p.setPrixtotal(Float.parseFloat(obj.get("prixTotal").toString()));
                panier.add(p);
            }
        } catch (IOException ex) {           
        }
        return panier;
    }
    
    
     public ArrayList<Panier> getAllPanier(){ 
       String url = Statics.BASE_URL + "/panier/affP";
        req.setUrl(url);
        req.setPost(false);
        System.out.print(req.getResponseData());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                panier = parsePanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return panier;
    }
}
