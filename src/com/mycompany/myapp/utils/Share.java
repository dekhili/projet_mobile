/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.codename1.facebook.FaceBookAccess;
import com.codename1.io.AccessToken;
import com.codename1.share.FacebookShare;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;
import com.mycompany.myapp.gui.ShowReservationsForm;



/**
 *
 * @author El Kamel 
 */
public class Share {
    
    String token;

    public void shareFacebook(String img, Events e , Reservations r) {

        FaceBookAccess.setClientId("2543360665976120");
        FaceBookAccess.setClientSecret("24d8ac1ce21b70cd2b2daab0145c3f4b");
        FaceBookAccess.setRedirectURI("https://www.google.tn/");
        FaceBookAccess.setPermissions(new String[]{"publish_actions"});

        FaceBookAccess.getInstance().showAuthentication(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                
                if(evt.getSource() instanceof String){
                    token = (String) evt.getSource();
                }
                if(evt.getSource() instanceof AccessToken){
                    AccessToken t = (AccessToken) evt.getSource();
                    token = t.getToken();
                    
                }
                
                
                  
                
                FacebookShare f = new FacebookShare();
                ShowReservationsForm sde = new ShowReservationsForm(e,r);
                f.setOriginalForm(sde.getF());
                ShareTest st = new ShareTest(token,sde.getF());
                st.share("j'ai participer à cet évenement","file://C:/wamp64/www/projet_pidev/symfony/web/images/"+img,"test");
                
                
                
                
                
                
           //     f.share("J'ai participé a cet evenement http://localhost/TestFOS/web/app_dev.php/event ");
                

                
            }
        });

//            try {
//                fb.postOnWall("me","test",null,null,null,"file://C:/wamp/www/cupcake/"+img,null,new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        
//                    }
//                });
//                } catch (IOException ex) {
//                    
//            }

    }

}
