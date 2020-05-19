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
public class ServiceEvents {

    public ArrayList<Events> events;
    Events evnt = new Events();

    public static ServiceEvents instance = null;
    public boolean resultOK;
    private ConnectionRequest req;


    public ServiceEvents() {
      ConnectionRequest req = new ConnectionRequest();

        req = new ConnectionRequest();
    }

    public static ServiceEvents getInstance() {
        if (instance == null) {
            instance = new ServiceEvents();
        }
        return instance;
    }

    public ArrayList<Events> getAllEvents() {
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/event/events/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    public ArrayList<Events> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
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
                e.setDate(obj.get("date").toString());
                float nbrPlaces = Float.parseFloat(obj.get("nbrPlaces").toString());
                e.setNbrPlaces((int) nbrPlaces);
                e.setLocalisation(obj.get("localisation").toString());
                e.setNom_image(obj.get("nomImage").toString());
                Map<String, Object> u = (Map) obj.get("professional");
                e.setIdPro((int) Double.parseDouble(u.get("id").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(e);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return events;
    }

    public Events getEvent2(int event_id) {

        //  String url = Statics.BASE_URL + "/event/events/find/" + id;
        req.setUrl("http://localhost/projet_pidev/symfony/web/app_dev.php/event/events/details/" +event_id);
         req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceEvents es = new ServiceEvents();
                evnt = es.getEvent(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evnt;
    }

    public Events getEvent(String json) {
        Events e = new Events();


        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);

            List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");

            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setPrix(((int) Double.parseDouble(obj.get("prix").toString())));
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                e.setDate(obj.get("date").toString());
                float nbrPlaces = Float.parseFloat(obj.get("nbrPlaces").toString());
                e.setNbrPlaces((int) nbrPlaces);
                e.setLocalisation(obj.get("localisation").toString());
                e.setNom_image(obj.get("nomImage").toString());
                Map<String, Object> u = (Map) obj.get("professional");
                e.setIdPro((int) Double.parseDouble(u.get("id").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {
        }

        return evnt;

    }

}
