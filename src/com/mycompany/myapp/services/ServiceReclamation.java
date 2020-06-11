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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sarra
 */
public class ServiceReclamation {
    
    public ArrayList<Reclamation> reclamation;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    public ConnectionRequest req;

    public ServiceReclamation() {
          req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
 
    public ArrayList<Reclamation> parseReclamation(String jsonText){
        try {
            reclamation=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                Map<String, Object> p = (Map) obj.get("produit");
                 t.setProduit((int) Double.parseDouble(p.get("id").toString()));
                 t.setNomproduit(p.get("nompr").toString());
                 t.setImage(p.get("image").toString());
                                  t.setDesc(p.get("descrip").toString());


                 
                Map<String, Object> u = (Map) obj.get("user");
                 t.setUser((int) Double.parseDouble(u.get("id").toString()));
                 t.setNomuser(u.get("username").toString());
                 
                 t.setProbleme(obj.get("probleme").toString());
                 t.setEtat(obj.get("etat").toString());
                 
                 
                reclamation.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamation;
    }
    
 
     public ArrayList<Reclamation> getAllreclamation(){
      //  String url = "http://41.226.11.252:11300/tasks/";
      String url = "http://localhost/projet_3a/symfony/web/app_dev.php/sav/afficherreclamationapi";
      req.setUrl(url);
      req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamation = parseReclamation(new String(req.getResponseData()));
                System.out.print(reclamation);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamation;
    }

     public boolean addReclamation(Reclamation t, int user , int produit) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/sav/ajouterreclamationapi/"+   t.getProbleme() + "/" + user + "/" + produit;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
    
    public void suppReclamation(int id ) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/projet_3a/symfony/web/app_dev.php/sav/supprimerreclamationapi/";// création de l'URL
        con.setUrl(Url+id);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
  
   
    public void modifReclamation(Reclamation t, int id ) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/projet_3a/symfony/web/app_dev.php/sav/modifierreclamationapi/"  +t.getProbleme()+ "/";// création de l'URL
        con.setUrl(Url+id);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
public boolean searchReclamation(Reclamation t , String probleme ) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/sav/search/"+   probleme ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
   
    

    
}
