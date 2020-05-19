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
import com.mycompany.myapp.gui.ShowEventsForm;



/**
 *
 * @author El Kamel 
 */
public class Share {
    
    String token;

    public void shareFacebook(String img,int id) {

        FaceBookAccess.setClientId("1468741356639356");
        FaceBookAccess.setClientSecret("4a9918ab8152c7fde7f7c43411b3c597");
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
                ShowEventsForm sde = new ShowEventsForm(id);
                f.setOriginalForm(sde.getF());
                ShareTest st = new ShareTest(token,sde.getF());
                st.share("j'ai participer à cet évenement","file://C:/wamp/www/projet_pidev/symfony/"+img,"test");
                
                
                
                
                
                
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
