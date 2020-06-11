
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
 * @author cyrine
 */

public class ProductService {
    
    public ArrayList<Product> products;
    private ConnectionRequest connectionRequest;
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
     
     public boolean addProds(Product p) {
        String url = Statics.BASE_URL + "/product/prods/new" + "?nompr=" + p.getNompr() +"&quantity=" + p.getQuantity()  +"&Descrip=" + p.getDescrip() +"&prix=" + p.getPrix() +"&image=" + p.getImage() + "&idCategory=" + p.getIdCategory()  +"&barcode=" + p.getBarcode();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service event, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
 
       public ArrayList<Product> parseProds(String jsonText){
        try {
            products=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
           Map<String,Object> prodsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
          List<Map<String,Object>> list = (List<Map<String,Object>>)prodsListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
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
                //p.setIdCategory(obj.get("idCategory").toString());
           
                //Ajouter la tâche extraite de la réponse Json à la liste
                products.add(p);
            }
               
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       return products;
    }
    
    public ArrayList<Product> getAllProds(){
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/product/prods/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
    
     public ArrayList<Product> getSortedProds(){
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/product/prods/sorted";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProds(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    }
   
    }
     
      
