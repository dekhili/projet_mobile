/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.services.ServiceEvents;
import java.util.ArrayList;

/**
 *
 * @author fedy
 */
public class EventsForm1 extends Form{

    Form f;
    private Resources theme;
 

    public EventsForm1() {
            theme = UIManager.initFirstTheme("/theme_2_1");


        f = new Form();

        ServiceEvents es = new ServiceEvents();
        ArrayList<Events> listEvents = new ArrayList<>();
        listEvents = es.getAllEvents();

        f.setTitle("OLD EVENTS");

        f.setLayout(BoxLayout.y());
        f.setUIID("bg1");
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
                 ListeProduct store = new ListeProduct(f);
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
            ChoiceEvents sb = new ChoiceEvents();
            sb.getF().show();
        });
         
 
        //RECHERCHE
        Container c8 = new Container(BoxLayout.x());

        TextField search = new TextField("","search");

        Button searchbtn = new Button(" ");
         searchbtn.setUIID("ButtonSearch");
        searchbtn.addActionListener((e) -> {
            String a = search.getText();
            // int i = Integer.parseInt(a);
            new searchForm(f, a).show();
        });
        c8.add(search);
        c8.add(searchbtn);        
        
        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
       
       
        //EVENTS' LIST
        for (Events e : listEvents) {
            Label label2 = new Label(e.getTitre());
            label2.setUIID("TitreEvent");
            c1.add(label2);
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image());
            ImageViewer img1 = new ImageViewer(imgUrl);

             Button b = new Button (" ");
             b.setUIID("ButtonDetails");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    EventDetailsForm sde = new EventDetailsForm(e);
                    sde.getF().show();

                }
            });

            Label sep = new Label("___________________________________________________");
            
            c1.add(img1);
            c1.add(b);
            c1.add(sep);
        }
        f.add(c8);
        f.add(c1);
        
        
      

//           Events ee = new Events("za3ma", "description", "image", "type", "adress", 0, 0, 0);
//           es.addEvent(ee);
//            ShowBrand a = new ShowBrand();
//            a.getF().show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
