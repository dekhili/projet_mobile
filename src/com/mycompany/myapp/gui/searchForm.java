/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.services.RechercheService;

/**
 *
 * @author fedy
 */
public class searchForm extends HomeForm{
    
    
    Form current;
    

    public searchForm(Form previous,String i)  {
        current = this;
 
        current.setTitle("Search : "+i);
        current.setLayout(BoxLayout.y());
   

         current.setLayout(BoxLayout.y());
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
            ChoiceEvents sb = new ChoiceEvents();
            sb.getF().show();
        });

        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
        for (Events c : RechercheService.getInstance().getAllEvents1(i)) {

           
            Label label0 = new Label(" ");
            c1.add(label0);
            Label label1 = new Label(" ");
            c1.add(label1);

            Label label2 = new Label(c.getTitre());
            label2.setUIID("TitreEvent");
            c1.add(label2);
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + c.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + c.getNom_image());
            ImageViewer img1 = new ImageViewer(imgUrl);

            Button b = new Button("Voir détail");
            b.setUIID("ButtonDetails");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    EventDetailsForm sde = new EventDetailsForm(c);
                    sde.getF().show();

                }
            });

            c1.add(img1);
            c1.add(b);
        }
        current.add(c1);
       
    }

    
}