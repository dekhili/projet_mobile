/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.location.Location;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.services.ServiceEvents;
//import com.mycompany.Service.UserEventService;

/**
 *
 * @author fedy
 */
public class EventDetailsForm1 extends Form{

    Form f;
    int id_event;
    Events e = new Events(id_event);
    int user_id;

    //int SelectedID;
    public EventDetailsForm1(Events events) {
        f = new Form();
        this.e = events;
        f.setTitle("Coming Event details");

        f.setLayout(BoxLayout.y());
        f.setUIID("bg3");
        Toolbar tb = f.getToolbar();
        
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
                PanierForm panier = new PanierForm();
                panier.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                StoreForm store = new StoreForm();
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
            
         tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, (ev) -> {
            EventsForm2 sb = new EventsForm2();
            sb.getF().show();
        });


        Events ev = new Events(e.getId());
        ServiceEvents es = new ServiceEvents();

        ev = es.getEvent2(events, e.getId());

        Container c1 = new Container(BoxLayout.y());

        Label label = new Label(e.getTitre());
                    label.setUIID("TitreEvent");

        c1.add(label);

        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

        Container c2 = new Container(BoxLayout.x());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c2.add(time);
        c2.add(new Label("Date : "));
        c2.add(new Label(formatter.format(e.getDate())));

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c3.add(adr);
        c3.add(new Label("Location : "));
        c3.add(new Label(e.getLocalisation()));

        //PRIX
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Price : "));
        Double prix = e.getPrix();
        String s1 = Double.toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));

        Container c4 = new Container(BoxLayout.x());
        final FontImage descimg = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c4.add(descimg);
        c4.add(new Label(" "));
        Label desc = new Label("More details ...");
        String xx = new String(e.getDescription() + " organised by :" + e.getIdPro() + ", it remains " + e.getNbrPlaces() + " places.");

        desc.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Dialog.show("Details", xx, "OK", null);

            }
        });
        
                    System.out.println(e.getId());


        c4.add(desc);
        
       
       int nbrPlaces = e.getNbrPlaces();

            Container c5 = new Container(BoxLayout.y());
            
            Container c10 = new Container(BoxLayout.x());
           Label ques1 = new Label("reserve your place here ");
            c10.add(ques1);
            Button btnReserver = new Button(" ");
         btnReserver.setUIID("ButtonReservation");
            btnReserver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt1) {
                    if (nbrPlaces != 0) {
                        ReservationForm s = new ReservationForm(e);
                        s.getF().show();
                    } else {
                        Dialog.show("Alert", "Oups pas de places !", new Command("OK"));

                    }
                }
            });

 
        c10.add(btnReserver);
        c5.add(c10);
      
         Container c12 = new Container(BoxLayout.x());
         Label sep = new Label("_______________________________________________");
         c12.add(sep);

        c1.add(c2);
        c1.add(c3);
        c1.add(c6);
        c1.add(c4);
        c1.add(c12);

        c1.add(c5);

        f.add(c1);
        
        

    }
    
     static Location lastKnownLocation;
protected void beforeMap(Form f) {
    
    
    
}
            

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  
}
