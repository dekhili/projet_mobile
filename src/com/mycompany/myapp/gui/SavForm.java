/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.ServiceEvents;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author esprit
 */
public class SavForm extends Form {
    
Form current;
private Resources theme;

 public SavForm() {
         theme = UIManager.initFirstTheme("/theme_2_1");

    current=this;
        setTitle("Client Services");

         
        setLayout(BoxLayout.y());
        
          Toolbar tb = current.getToolbar();
        
         tb.addMaterialCommandToOverflowMenu("Logout", FontImage.MATERIAL_INPUT, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new SignInForm().show();

            }
        });
         
          tb.addMaterialCommandToOverflowMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new Profile().getF().show();

            }
        });
          
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });

        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_LOCAL_GROCERY_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PanierForm panier = new PanierForm(current);
                panier.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 ListeProduct store = new ListeProduct(current);
                store.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Recruitement", FontImage.MATERIAL_FACE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RecruitForm recruit = new RecruitForm();
                recruit.getF().show();
            }
        });
        tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_EVENT, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ChoiceEvents events = new ChoiceEvents();
                events.getF().show();
            }
        });
        
         tb.addMaterialCommandToSideMenu("Client Service", FontImage.MATERIAL_WORK, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SavForm sav = new SavForm();
                sav.getF().show();
            }
        });

         tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent evt) {
                AboutForm about = new AboutForm();
                about.getF().show();
            }
        });
               
        
        //current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_STAR , ev->{Display.getInstance().exitApplication();});

        
        setLayout(BoxLayout.yCenter());
        setUIID("bg3");
        Container c1 = new Container(BoxLayout.yCenter());
        Container c2 = new Container(BoxLayout.yCenter());


        
        Image imgUrlAvis; 
        Image imgUrlRec; 
        Label review = new Label("Troubleshooting");
        review.setUIID("avislabel");
        Label rating = new Label("Rating");
        rating.setUIID("avislabel");


          
        
          String urlImageRec = "http://localhost/projet_3a/symfony/web/images/sav/r.png" ;
          EncodedImage encImageRec = EncodedImage.createFromImage(theme.getImage("r.png"), false);
          imgUrlRec = URLImage.createToStorage(encImageRec, ""+"r.png" , urlImageRec);
          Button btnReview = new Button(imgUrlRec);
          
           btnReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            ReclamationForm review = new ReclamationForm(current);
            review.getF().show();   
            }
        }); 
           c1.addAll( review, btnReview);


          
          String urlImageAvis = "http://localhost/projet_3a/symfony/web/images/sav/rate.png" ;
          EncodedImage encImageAvis = EncodedImage.createFromImage(theme.getImage("rate.png"), false);
          imgUrlAvis = URLImage.createToStorage(encImageAvis, ""+"rate.png" , urlImageAvis);
          Button btnRating = new Button(imgUrlAvis);
          
          btnRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            AvisForm avis = new AvisForm(current);
            avis.getF().show();   
            }
        }); 
        
          c2.addAll(rating, btnRating);


         
          
          
          
        
        

       
        
        
        current.addAll( c1, c2 );


        
        
            
        
        
        
                 
       
        
        
       

        }
    
            
      

    public Form getF() {
               


        return current;
    }
    
    
}
