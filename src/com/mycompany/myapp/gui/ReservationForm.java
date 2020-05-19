/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;
import com.mycompany.myapp.services.ServiceReservation;
import com.mycompany.myapp.services.ServiceEvents;


/**
 *
 * @author Fedi
 */
public class ReservationForm extends Form {
    
Form current;
    Form f;
    Reservations r = new Reservations();
    int event_id;
    Events e = new Events(event_id);


 public ReservationForm(Events events) {
     
   //  this.event_id = id;
      current=this;
        setTitle("Reservation");
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new EventsForm().show();

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
           
            setLayout(BoxLayout.y());
        
            ServiceEvents se = new ServiceEvents();
        
        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c3.add(adr);
        //e = se.getEvent2(event_id);
        c3.add(new Label("Titre : "));
        c3.add(new Label(events.getTitre()));
        current.add(c3);
        
        TextField tfquantite = new TextField("","Quantité");
        TextField tfprix= new TextField("", "Prix");
        Button btnValider = new Button("Add reservation");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfquantite.getText().length()==0)||(tfprix.getText().length()==0))
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                else
                {
                    try {
                        Reservations r = new Reservations(Integer.parseInt(tfquantite.getText()), Double.parseDouble(tfprix.getText()));
                        if( ServiceReservation.getInstance().addReservation(r,events.getId()))
                        { Dialog.show("Success","Reservation made You'll pay the day of the event an amount of : "+r.getPrixpaye()+"Dinars.",new Command("OK"));
                      //  int idRes = r.getId();
                         ShowReservationForm hf = new ShowReservationForm(r);
                         hf.getF().show();
                        }else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Ouuuuuups !! quantité & prix doivent être des entiers", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        current.addAll(tfquantite,tfprix,btnValider);

        }
    

  
    
     
 

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}