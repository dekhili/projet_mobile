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
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class ServiceReservation {
      public ArrayList<Reservations> listReservation;
    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
  //  Events evnt = new Events();
    Reservations re = new Reservations();
    Events e = new Events();
    public ServiceReservation() {
         req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public boolean addReservation(Reservations r , Events e) {
      //  String url = Statics.BASE_URL + "/ajouter/" + r.getEvent_id() + "/" + r.getQuantite() +r.getPrixpaye(); //création de l'URL
        req.setUrl("http://localhost/projet_3a/symfony/web/app_dev.php/event/reservation/ajouter/" + e.getId()+"/?quantite="+r.getQuantite()+"&prixpaye=" +r.getPrixpaye());// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
     /* public ArrayList<Reservations> getRes2(int id) {

        //  String url = Statics.BASE_URL + "/event/events/find/" + id;
        req.setUrl("http://localhost/projet_pidev/symfony/web/app_dev.php/event/reservation/details/" +id);
         req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceReservation es = new ServiceReservation();
                listReservation = es.getRes(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return listReservation;
    }*/
    
    /*public ArrayList<Reservations> getRes2(int id){
      //  String url = "http://41.226.11.252:11300/tasks/";
      String url = "http://localhost/projet_pidev/symfony/web/app_dev.php/event/reservation/details/" +id;
      req.setUrl(url);
      req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listReservation = getRes(new String(req.getResponseData()));
                System.out.print(listReservation);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return listReservation;
    }*/

 /*   public  ArrayList<Reservations>  getRes(String json) {
       
        ArrayList<Reservations> listRes = new ArrayList<>();


        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);

            List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");

            for (Map<String, Object> obj : list) {
                Reservations r = new Reservations();
                //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                Map<String, Object> u = (Map) obj.get("Events");
                float event_id = Float.parseFloat(u.get("id").toString());
                r.setEvent_id((int)event_id);
                float qunt = Float.parseFloat(obj.get("quantite").toString());
                r.setId((int) qunt);
                                float prx = Float.parseFloat(obj.get("prixpaye").toString());

                r.setPrixpaye((double) prx);
                
                listRes.add(r);
      
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {
        }

        return listRes;

    }*/
    
    
    public Reservations getRes2(Reservations l ,int id) {
           ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/projet_3a/symfony/web/app_dev.php/event/reservation/details/"+id;
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
          });
       NetworkManager.getInstance().addToQueue(req);
       return l;
    }
    
    public int showId(int id){
         ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/projet_3a/symfony/web/app_dev.php/event/reservation/id/"+id;
      req.setUrl(url);
      req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            
            byte[] data = (byte[]) evt.getMetaData();
            String s = new String(data);
           System.out.println(data);
          });
       NetworkManager.getInstance().addToQueue(req);
       return id;
    }
    
    public void suppRes(Reservations l ,int id_res, int id_ev) {
           ConnectionRequest req = new ConnectionRequest();
      String url = "http://localhost/projet_3a/symfony/web/app_dev.php/event/reservation/"+id_ev+"/supp/";
      req.setUrl(url+id_res);
     // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
           String str = new String(req.getResponseData());
         
        });
       
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

   /* public void suppReclamation(int id ) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/projet_pidev/symfony/web/app_dev.php/sav/supprimerreclamationapi/";//création de l'URL
        con.setUrl(Url+id);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }*/

}

