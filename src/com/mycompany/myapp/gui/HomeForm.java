/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 * GUI builder created Form
 *
 * @author h^
 */
public class HomeForm extends com.codename1.ui.Form {
Form current;


    public HomeForm() {
        
                    this(com.codename1.ui.util.Resources.getGlobalResources());

          current=this;

        setTitle("Home");

        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        
        current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
        
         current.getToolbar().addCommandToSideMenu("Panier", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           PanierForm panier = new PanierForm(current);
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
           
           
            current.getToolbar().addCommandToSideMenu("About", null, new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
           AboutForm about = new AboutForm();
           about.getF().show();
            }
        });
            
            
        }
    

       public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }
    
    public HomeForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//////////////////////-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("HomeForm");
        setName("HomeForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

}
