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
         theme = UIManager.initFirstTheme("/theme_2");

    current=this;
        setTitle("Client Services");

         
        setLayout(BoxLayout.y());
        
         current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                             new HomeForm().show();

            }
        });
         
      current.getToolbar().addCommandToSideMenu("Panier", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           PanierForm panier = new PanierForm();
           panier.getF().show();
            }
        });
         
         
        current.getToolbar().addCommandToSideMenu("Store", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           StoreForm store = new StoreForm();
           store.getF().show();
            }
        });
        
        
         current.getToolbar().addCommandToSideMenu("Recruitement", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           RecruitForm recruit = new RecruitForm();
           recruit.getF().show();
            }
        });
          current.getToolbar().addCommandToSideMenu("Events", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           EventsForm events = new EventsForm();
           events.getF().show();
            }
        });
           
           
            current.getToolbar().addCommandToSideMenu("About", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           AboutForm about = new AboutForm();
           about.getF().show();
            }
        });
               
        current.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EXIT_TO_APP, ev->{Display.getInstance().exitApplication();});
        //current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_STAR , ev->{Display.getInstance().exitApplication();});

        
        setLayout(BoxLayout.yCenter());
        Container c1 = new Container(BoxLayout.yCenter());
        Container c2 = new Container(BoxLayout.yCenter());


        
        Image imgUrlAvis; 
        Image imgUrlRec; 
        Label review = new Label("Review");
        Label rating = new Label("Rating");


          
        
          String urlImageRec = "http://localhost/projet_pidev/symfony/web/bundles/product1/images/sav/review.png" ;
          EncodedImage encImageRec = EncodedImage.createFromImage(theme.getImage("review.png"), false);
          imgUrlRec = URLImage.createToStorage(encImageRec, ""+"review.png" , urlImageRec);
          Button btnReview = new Button(imgUrlRec);
          
           btnReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            ReclamationForm review = new ReclamationForm(current);
            review.getF().show();   
            }
        }); 
           c1.addAll( review, btnReview);


          
          String urlImageAvis = "http://localhost/projet_pidev/symfony/web/bundles/product1/images/sav/rating.png" ;
          EncodedImage encImageAvis = EncodedImage.createFromImage(theme.getImage("rating.png"), false);
          imgUrlAvis = URLImage.createToStorage(encImageAvis, ""+"rating.png" , urlImageAvis);
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
