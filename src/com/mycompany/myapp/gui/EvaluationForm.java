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
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.ServiceEvents;


/**
 *
 * @author Fedi
 */
public class EvaluationForm extends Form {
    
Form current;
    Form f;
    Evaluations r = new Evaluations();
    int event_id;
    Events e = new Events(event_id);


 public EvaluationForm(Events events) {
     
   //  this.event_id = id;
      current=this;
        setTitle("Evaluations");
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
        
        TextField tfnote = new TextField("","note");
        TextField tfcmnt= new TextField("", "commentaire");
        Button btnValider = new Button("Add evaluations");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnote.getText().length()==0)||(tfcmnt.getText().length()==0))
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                else
                {
                    try {
                        Evaluations r = new Evaluations(Integer.parseInt(tfnote.getText()),tfcmnt.getText());
                        if( EvaluationService.getInstance().addEvaluation(r,events.getId()))
                        { Dialog.show("Success","Evaluation succeded. The note you submitted is  : "+r.getNote(),new Command("OK"));
                      //  int idRes = r.getId();
                        // ShowReservationForm hf = new ShowReservationForm(r);
                        // hf.getF().show();
                        }else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Ouuuuuups !! note doit être des entiers", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        current.addAll(tfcmnt,tfnote,btnValider);

        }
    

  
    
     
 

     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
}