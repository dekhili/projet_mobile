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
import com.mycompany.myapp.entities.UserEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author El Kamel
 */
public class UserEventService {

    ArrayList<UserEvent> listUV = new ArrayList<>();
    UserEvent uv = new UserEvent();
    int insert_id = 0;

    public int addUserEvent(int user_id, int event_id) {

        UserEvent ue = new UserEvent();
        ue.setEventId(event_id);
        ue.setUserId(user_id);

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/user_event/");
        con.setPost(true);

        con.setHttpMethod("POST");
        con.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        con.addArgument("scanned", Integer.toString(0));
        con.addArgument("confirmed", Integer.toString(0));
        con.addArgument("user_id", Integer.toString(ue.getUserId()));
        con.addArgument("event_id", Integer.toString(ue.getEventId()));

        con.addResponseListener((e) -> {

            String json = new String(con.getResponseData());
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evt;
            try {
                
                evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                //List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");
                
                for (Map.Entry<String, Object> i: evt.entrySet()) {
                    
                    System.out.println("hedha el key *************"+i.getKey());
                    if("insertId".equals(i.getKey())){
                        
                       System.out.println("hedha el s**********" + i.getValue());
                       
                       
                       Double d = (double) i.getValue();
                        insert_id = (int) Math.round(d);

                    }
                  
                    

                    
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return insert_id ; 

    }

    public ArrayList<UserEvent> getListEventsByUserID2(int id) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:3000/api/user_event?_where=(user_id,eq," + id + ")");
        con.setPost(false);
        con.setHttpMethod("GET");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                UserEventService uvs = new UserEventService();

                try {
                    listUV = uvs.getListEventsByUserID(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUV;
    }

    public ArrayList<UserEvent> getListEventsByUserID(String json) throws ParseException {

        ArrayList<UserEvent> listEvt = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> evt = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(evt);

            List<Map<String, Object>> list = (List<Map<String, Object>>) evt.get("root");

            for (Map<String, Object> obj : list) {

                float id = Float.parseFloat(obj.get("id").toString());
                float usr = Float.parseFloat(obj.get("user_id").toString());
                float evnt_id = Float.parseFloat(obj.get("event_id").toString());
                float confirmed = Float.parseFloat(obj.get("confirmed").toString());
                float scanned = Float.parseFloat(obj.get("scanned").toString());

                uv.setId((int) id);
                uv.setUserId((int) usr);
                uv.setEventId((int) evnt_id);

                if (confirmed == 1) {
                    uv.setConfirmed(true);
                } else {
                    uv.setConfirmed(false);
                }

                uv.setScanned((int) scanned);

                listEvt.add(uv);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEvt);
        return listEvt;

    }

}
