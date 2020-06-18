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
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author h^
 */
public class EvaluationService {
      public ArrayList<Evaluations> tasks;
    
    public static EvaluationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
  //  Events evnt = new Events();
    Evaluations re = new Evaluations();

    public EvaluationService() {
         req = new ConnectionRequest();
    }

    public static EvaluationService getInstance() {
        if (instance == null) {
            instance = new EvaluationService();
        }
        return instance;
    }

     public boolean addEvaluation(Evaluations t , int id ,int user_id) {
      String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter/"  +t.getCommentaire()+ "/"+ t.getNote() + "/" + id+ "/"+user_id;
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
    
   
    public boolean addEv(Evaluations t, int id) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter1/"  +t.getCommentaire()+ "/"+ id;
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
    
    public boolean addEv2(Evaluations t, int id) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter2/"  +t.getCommentaire()+ "/"+ id;
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
    
    public boolean addEv3(Evaluations t, int id) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter3/"  +t.getCommentaire()+ "/"+ id;
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
    
    public boolean addEv4(Evaluations t, int id) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter4/"  +t.getCommentaire()+ "/"+ id;
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
    
    public boolean addEv5(Evaluations t, int id) {
        String url =  "http://localhost/projet_3a/symfony/web/app_dev.php/event/evaluation/ajouter5/"  +t.getCommentaire()+ "/"+ id;
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
    
    
    /*   public Evaluations getEv2(int evaluation_id) {

        //  String url = Statics.BASE_URL + "/event/events/find/" + id;
        req.setUrl(Statics.BASE_URL + "/event/evaluation/details/" +evaluation_id);
         req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                EvaluationService es = new EvaluationService();
                re = es.getEv(new String(req.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return re;
    }

    public Evaluations getEv(String json) {
        Evaluations r = new Evaluations();


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
                r.setCommentaire(obj.get("commentaire").toString());
                r.setNote(((int) Integer.parseInt(obj.get("note").toString())));
      
                //Ajouter la tâche extraite de la réponse Json à la liste
            }

        } catch (IOException ex) {
        }

        return re;

    }*/
    
        public Evaluations getEv2(Evaluations l ,int id) {
           ConnectionRequest req = new ConnectionRequest();
      String url = Statics.BASE_URL + "/event/evaluation/details/"+id;
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

         public List<Evaluations> FindByUserID(int id,int id_ev) {
        ConnectionRequest req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/event/evaluation/verif/" + id+"/"+id_ev;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseEvaluation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
          public ArrayList<Evaluations> parseEvaluation(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Evaluations e = new Evaluations();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                float note = Float.parseFloat(obj.get("note").toString());
                e.setNote((int) note);
                e.setCommentaire(obj.get("commentaire").toString());
//                float event_id = Float.parseFloat(obj.get("event_id").toString());
              //  e.setEvent_id((int) event_id);
               Map<String, Object> u = (Map) obj.get("Events");
                e.setEvent_id((int) Double.parseDouble(u.get("id").toString()));
                // float user_id = Float.parseFloat(obj.get("user_id").toString());
                //e.setUser_id((int) user_id);
                 Map<String, Object> u1 = (Map) obj.get("user");
                e.setUser_id((int) Double.parseDouble(u1.get("id").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(e);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return tasks;
    }

}

