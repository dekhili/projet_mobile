
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MONDHER
 */

public class searchProdService {
    
    public ArrayList<Product> prods;
    Product pr = new Product();
    public static searchProdService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
  
    public searchProdService() {
        ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

     public static searchProdService getInstance() {
        if (instance == null) {
            instance = new searchProdService();
        }
        return instance;
    }
      
    public ArrayList<Product> getAllProds(String c) {
        
        ConnectionRequest req = new ConnectionRequest(c);

        String url = Statics.BASE_URL + "/product/prods/find/"+c;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
    }

     public ArrayList<Product> getProdsByCat(String c) {
        
        ConnectionRequest req = new ConnectionRequest(c);

        String url = Statics.BASE_URL + "/product/prods/filterBy/"+c;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                prods = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return prods;
     }
     
    public ArrayList<Product> parseProds(String jsonText) {
        try { prods = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> prodListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) prodListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Product p = new Product();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                p.setNompr(obj.get("nompr").toString());
                float quantity = Float.parseFloat(obj.get("quantity").toString());
                p.setQuantity((int)quantity);
                p.setDescrip(obj.get("descrip").toString());
                p.setPrix(((int)Double.parseDouble(obj.get("prix").toString())));
                p.setImage(obj.get("image").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                prods.add(p);
            }

        } catch (IOException ex) {

        }
        return prods;
    }
    
}
