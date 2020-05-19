/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.services.ServiceEvents;
import java.util.ArrayList;

/**
 *
 * @author Fedi
 */
public class EventsForm extends Form {
    
Form current;
    Form f;
    
    int id_event;

 public EventsForm() {
     
      current=this;
        setTitle("Events");
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
       
       current.getToolbar().addCommandToSideMenu("Home", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           HomeForm home = new HomeForm();
           home.getF().show();
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
           current.getToolbar().addCommandToSideMenu("Client Services", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           SavForm sav = new SavForm();
           sav.getF().show();
            }
        });
           
           
           
            current.getToolbar().addCommandToSideMenu("About", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           AboutForm about = new AboutForm();
           about.getF().show();
            }
        });

        
           current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new HomeForm();
                hf.getF().show();
           
        });
           
         ServiceEvents es = new ServiceEvents();
        ArrayList<Events> listEvents = new ArrayList<>();
        listEvents = es.getAllEvents();
      

        for (Events e : listEvents) {
            
        
        this.id_event = e.getId();

        Container c0 = new Container(BoxLayout.y());
        Container c1 = new Container(BoxLayout.x());

        Label vide = new Label("      ");
             Label titre = new Label("L'evenement :       " +e.getTitre());
             c1.add(vide);
              c0.add(titre);
        

              //IMAGE 
        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_pidev/symfony/web/Events/" + e.getNom_image(), "http://localhost/projet_pidev/symfony/web/Events/" + e.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

        
        //DATE
        
        Container c2 = new Container(BoxLayout.x());
        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c2.add(time);
        c2.add(new Label("Date : "));
        c2.add(new Label(e.getDate()));

        
        
        //ADRESS
        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c3.add(adr);
        c3.add(new Label("Adresse : "));
        c3.add(new Label(e.getLocalisation()));
        

       
        //PRIX
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Prix : "));
        Double prix = e.getPrix();
        String s1 = Double.toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));


        //Details feha l description + idPro (etranger)
        Container c4 = new Container(BoxLayout.x());
        final FontImage descimg = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c4.add(descimg);
        c4.add(new Label("Details : "));
        Label desc = new Label("voir details ...");
        String xx = new String(e.getDescription()+" organisé par le professionnel :" +e.getIdPro()+", il reste encore "+e.getNbrPlaces()+" places.");
        
        desc.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Dialog.show("Details", xx , "OK", null);

            }
        });
        

        c4.add(desc);
        
        Container c5 = new Container(BoxLayout.x());
         Button btnReserver = new Button("Reserver");
         btnReserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                ReservationForm s = new ReservationForm(e);
                s.getF().show();
            }
        });

        Button btnEvaluer = new Button("Evaluer");
         btnEvaluer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                EvaluationForm s = new EvaluationForm(e);
                s.getF().show();
            }
        });

        
        c5.add(btnReserver);
        c5.add(btnEvaluer);
        c0.add(c1);


        c0.add(c2);
        c0.add(c3);
        c0.add(c6);

        c0.add(c4);

        c0.add(c5);
        current.add(c0);

        }
    }

  
    
     
 

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}