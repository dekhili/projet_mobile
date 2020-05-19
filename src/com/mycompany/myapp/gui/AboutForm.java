/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author esprit
 */
public class AboutForm extends Form {
    
Form current;
 public AboutForm() {
     
    current=this;
        setTitle("About");

         
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
           current.getToolbar().addCommandToSideMenu("Client Services", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           SavForm sav = new SavForm();
           sav.getF().show();
            }
        });
           
           
        }
 

    public Form getF() {
        return current;
    }
    
    
}
