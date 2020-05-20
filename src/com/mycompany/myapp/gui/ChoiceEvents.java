/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author fedy
 */
public class ChoiceEvents {

    Form f;
    private Resources theme;

    public ChoiceEvents() {
        theme = UIManager.initFirstTheme("/theme_2");

        f = new Form();

        f.setTitle("Choices");

        f.setLayout(BoxLayout.y());
        f.setUIID("bg2");
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
                PanierForm panier = new PanierForm(f);
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
        
        
                Label sep = new Label ("_____________________________________________________");
                Label sep2 = new Label ("_____________________________________________________");
                Label sep1 = new Label ("_____________________________________________________");


        Button b = new Button(" ");
        b.setUIID("ButtonComing");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                EventsForm2 sde = new EventsForm2();
                sde.getF().show();

            }
        });
        f.add(b);

        Button b2 = new Button(" ");
        b2.setUIID("ButtonPast");
        b2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                EventsForm1 sde = new EventsForm1();
                sde.getF().show();

            }
        });

        f.add(b2);
        
        f.add(sep);
        
       
        Label l = new Label("HUNTING IS AN OBSESSION");
        l.setUIID("Quotes");

        Label l2 = new Label("“The fascination of shooting as");
        l2.setUIID("Quotes");

        Label L3 = new Label("a sport depends almost wholly ");
        L3.setUIID("Quotes");

        Label L4 = new Label("on whether you are at the right ");
        L4.setUIID("Quotes");

        Label L5 = new Label("or wrong end of the gun.” ");
        L5.setUIID("Quotes");

        Label L6 = new Label("― P.G. Wodehouse, The Adventures of Sally");
        L6.setUIID("Quotes");
         Label L7 = new Label("Calendar");
        L7.setUIID("CalendarTitle");
         Button b3 = new Button (" ");
        b3.setUIID("ButtonCalend");
         b3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                CalendarForm sde = new CalendarForm();
                sde.getF().show();

            }
        });
        


        f.add(l);
        f.add(l2);
        f.add(L3);
        f.add(L4);
        f.add(L5);
        f.add(L6);
        f.add(sep2);
        f.add(L7);
        f.add(b3);


    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
