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
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class RechercheService {
        public ArrayList<Events> events;

        Events evnt = new Events();

    public static RechercheService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    public RechercheService() {
        ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

     public static RechercheService getInstance() {
        if (instance == null) {
            instance = new RechercheService();
        }
        return instance;
    }
   
    
    public ArrayList<Events> getAllEvents1(String c) {
                ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/event/events/recherche/"+c;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseOneEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }



public ArrayList<Events> parseOneEvent(String jsonText) {
        try { events = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Events e = new Events();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setPrix(((int) Double.parseDouble(obj.get("prix").toString())));
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                String dd = (obj.get("date").toString());

                String dt = dd.substring(0, 10);

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                Date d = new Date();

                try {

                    d = formatter.parse(dt);

                } catch (ParseException exp) {
                }

                System.out.println(formatter.format(d));
                float nbrPlaces = Float.parseFloat(obj.get("nbrPlaces").toString());
                e.setNbrPlaces((int) nbrPlaces);
                e.setLocalisation(obj.get("localisation").toString());
                e.setNom_image(obj.get("nomImage").toString());
                e.setDate(d);

                Map<String, Object> u = (Map) obj.get("professional");
                e.setIdPro((int) Double.parseDouble(u.get("id").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(e);
            }

        } catch (IOException ex) {

        }
        return events;
    }
    
}
