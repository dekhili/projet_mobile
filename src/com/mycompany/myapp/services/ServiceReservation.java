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
      public ArrayList<Reservations> tasks;
    
    public static ServiceReservation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
  //  Events evnt = new Events();
    Reservations re = new Reservations();

    public ServiceReservation() {
         req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public boolean addReservation(Reservations r , int event_id) {
      //  String url = Statics.BASE_URL + "/ajouter/" + r.getEvent_id() + "/" + r.getQuantite() +r.getPrixpaye(); //création de l'URL
        req.setUrl("http://localhost/projet_pidev/symfony/web/app_dev.php/event/reservation/ajouter/" + event_id+"/?quantite="+r.getQuantite()+"&prixpaye=" +r.getPrixpaye());// Insertion de l'URL de notre demande de connexion
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
    
    
    
       public Reservations getRes2(int reservation_id) {

        //  String url = Statics.BASE_URL + "/event/events/find/" + id;
        req.setUrl("http://localhost/projet_pidev/symfony/web/app_dev.php/event/reservation/details/" +reservation_id);
         req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceReservation es = new ServiceReservation();
                re = es.getRes(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return re;
    }

    public Reservations getRes(String json) {
        Reservations r = new Reservations();


        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);

            List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                 Map<String, Object> u = (Map) obj.get("Events");
                r.setTitre(u.get("titre").toString());
                float qunt = Float.parseFloat(obj.get("quantite").toString());
                r.setId((int) qunt);
                r.setPrixpaye(((int) Double.parseDouble(obj.get("prixpaye").toString())));
      
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {
        }

        return re;

    }


}

