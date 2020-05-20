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
import com.mycompany.myapp.entities.Reservations;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.utils.Share;

/**
 *
 * @author fedy
 */
public class ShowReservationsForm extends Form {
    

    
Form current;
    Form f;

    int event_id;
    Events e = new Events(event_id);
   // Events e = new Events(event_id);


 public ShowReservationsForm(Events events, Reservations reservation) {
    // this.reservation_id=id;
   //  this.event_id = id;
   
        this.e=events;

      current=this;
        setTitle("Détails de reservation");
    
       
       current.setLayout(BoxLayout.y());
       current.setUIID("bg1");
        Toolbar tb = current.getToolbar();
           tb.addMaterialCommandToOverflowMenu("Logout", FontImage.MATERIAL_INPUT, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });
         
          tb.addMaterialCommandToOverflowMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new EventsForm1().getF().show();

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
        
         tb.addMaterialCommandToSideMenu("SAV", FontImage.MATERIAL_WORK, new ActionListener() {
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
            EventsForm1 sb = new EventsForm1();
            sb.getF().show();
        });

           
                setLayout(BoxLayout.y());
        
        Container c1 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TITLE, "Label", 6);
        c1.add(adr);
        //e = se.getEvent2(event_id);
        c1.add(new Label("               "));
        
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

        
        ServiceReservation sr = new ServiceReservation();
        Reservations rs = new Reservations();
       // ArrayList<Reservations> listRes = new ArrayList<>();
       // rs = sr.getRes2(reservation,reservation.getId());
       rs = sr.getRes2(reservation, reservation.getId());

       
        Container c2 = new Container(BoxLayout.x());
        final FontImage adr1 = FontImage.createMaterial(FontImage.MATERIAL_CONFIRMATION_NUMBER, "Label", 6);
        c2.add(adr1);
       c2.add(new Label("Nombre de places reservé"));
       c2.add(new Label(Integer.toString(rs.getQuantite())));
        current.add(c2);
        
        
        Container c3 = new Container(BoxLayout.x());
        final FontImage adr2 = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c3.add(adr2);
        c3.add(new Label("Prix à payer"));
        c3.add(new Label(Double.toString(rs.getPrixpaye())));        
        current.add(c3);
        
          Button btnPartager = new Button(" ");
        btnPartager.setUIID("buttonPartager");
        btnPartager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
                Share s = new Share();
                s.shareFacebook(e.getNom_image(),e,reservation);

            }
        });
        
        
        
        //   System.out.println("id de reservation est : "+reservation.getId());
       /* Button c = new Button ("delete");
        c.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //System.out.println(e.getIdlp());
                    sr.suppRes(reservation,reservation.getEvent_id(),reservation.getId());
                            
                        Dialog.show("Delete Confirmed !", "Removed product from cart ", new Command("ok"));
                        EventsForm1 ef = new EventsForm1();
                        ef.getF().show();
                   
                }
           });
                current.add(c);*/

        current.add(btnPartager);
              //  current.add(btnsupp);


  }
 
   public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
 
}
