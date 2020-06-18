/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.LocalNotificationTest;

/**
 *
 * @author fedy
 */
public class ShowEvaluationForm extends Form {
    

    
Form current;
    int reservation_id;
    Reservations r = new Reservations(reservation_id);

    int event_id;
    Events e = new Events(event_id);
   // Events e = new Events(event_id);


 public ShowEvaluationForm(Events events, Evaluations evaluation) {
    // this.reservation_id=id;
   //  this.event_id = id;
   
        this.e=events;

      current=this;
        setTitle("Détails de l'évaluation");
      
        
       
       
        current.setLayout(BoxLayout.y());
               current.setUIID("bg1");

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
        
          tb.addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                StoreForm store = new StoreForm();
                store.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_LOCAL_GROCERY_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PanierForm panier = new PanierForm(current);
                panier.getF().show();
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
         
         
           tb.addMaterialCommandToSideMenu("Gallery", FontImage.MATERIAL_COLLECTIONS, new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent evt) {
                GalleryForm about = new GalleryForm();
                about.getF().show();
            }
        });
        
                
         tb.addMaterialCommandToSideMenu("Get Notified", FontImage.MATERIAL_ADD_ALERT, new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent evt) {
              new   LocalNotificationTest().start();
            }
        });

         tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent evt) {
                AboutForm about = new AboutForm();
                about.getF().show();
            }
        });


        
            tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, (ev) -> {
            EventsForm1 sb = new EventsForm1();
            sb.getF().show();
        });

           
                setLayout(BoxLayout.y());
        
        Container c1 = new Container(BoxLayout.xCenter());
       // final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TITLE, "Label",3);
        //c1.add(adr);
        //e = se.getEvent2(event_id);
       
        Label l8 = new Label(events.getTitre());
        l8.setUIID("TitreEvent");
        c1.add(l8);
        
        current.add(c1);
        
        Container c0 = new Container(BoxLayout.y());
         Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
       
        c0.add(img1);
        current.add(c0);

        
        EvaluationService sr = new EvaluationService();
        Evaluations rs = new Evaluations(evaluation.getId());
       // ArrayList<Reservations> listRes = new ArrayList<>();
       // rs = sr.getRes2(reservation,reservation.getId());
       rs = sr.getEv2(evaluation, evaluation.getId());

        Container c2 = new Container(BoxLayout.x());
        final FontImage adr1 = FontImage.createMaterial(FontImage.MATERIAL_CONFIRMATION_NUMBER, "Label", 6);
        c2.add(adr1);
       c2.add(new Label("Note :"));
       c2.add(new Label(Integer.toString(rs.getNote())));
        current.add(c2);
       
       
       
        Container c3 = new Container(BoxLayout.x());
        final FontImage adr2 = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c3.add(adr2);
        c3.add(new Label("Commentaire : "));
        c3.add(new Label(rs.getCommentaire()));        
        current.add(c3);
        

        /* Container c0 = new Container(BoxLayout.y());
         Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/Events/" + events.getNom_image(), "http://localhost/projet_3a/symfony/web/Events/" + events.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
       
        c0.add(img1);
        current.add(c0);
  }
 
   public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
    
     /*public ShowReservationsForm(Form current,Reservations reservation) {
        setTitle("Show Reservation");
        this.r = reservation;
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceReservation.getInstance().getRes2(reservation, reservation.getId()).toString());
        add(sp);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }*/
     
 }
}
