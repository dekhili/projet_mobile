/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Lignepanier;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.LignePanierService;
import com.mycompany.myapp.services.ServiceEvents;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author fedy
 */
public class HomeForm extends com.codename1.ui.Form {
Form current;

private Resources theme;
    public HomeForm() {
        
                    this(com.codename1.ui.util.Resources.getGlobalResources());
                     Image imgUrl;
 theme = UIManager.initFirstTheme("/theme_panier");
          current=this;
          
        current.setTitle("Home");
        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        current.setUIID("HomePage");
       // Container cnt = new Container(BoxLayout.xCenter());
        
        
     
         tb.addMaterialCommandToOverflowMenu("Logout", FontImage.MATERIAL_ACCOUNT_CIRCLE, new ActionListener() {

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
                PanierForm panier = new PanierForm(current);
                panier.getF().show();
            }
        });

        tb.addMaterialCommandToSideMenu("Store", FontImage.MATERIAL_STORE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ListeProduct store = new ListeProduct(current);
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
  Container c = new Container(BoxLayout.x());
       Container c1 = new Container(BoxLayout.y());
       
          String urlImage = "http://localhost/projet_3a/symfony/web/images/4.jpg" ;
          EncodedImage encImage1 = EncodedImage.createFromImage(theme.getImage("4.jpg"), false);
          URLImage imgUrl2 = URLImage.createToStorage(encImage1, ""+"4.jpg" , urlImage);
          ImageViewer imgUrl1=new ImageViewer(imgUrl2);
         // Button btnsup = new Button(imgUrl);
         current.add(imgUrl1);
         
           SpanLabel sp = new SpanLabel();
           sp.setText("The fascination of shooting as a sport depends almost wholly on whether you are at the right or wrong end of the gun.” ― P.G. Wodehouse, The Adventures of Sally");
         current.add(sp);
         current.add("___________________________________________________________________________________");
       
       
       
       ServiceEvents es = new ServiceEvents();
        ArrayList<Events> listEvents = new ArrayList<>();
        listEvents = es.getAllEvents3();         
        //EVENTS' LIST
        for (Events e : listEvents) {
            Label label2 = new Label(e.getTitre());
            label2.setUIID("TitreEvent");
            Image placeholder = Image.createImage(400, 300);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image());
            ImageViewer img1 = new ImageViewer(imgUrl);

             Button b = new Button (" ");
             b.setUIID("ButtonDetails");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    EventDetailsForm1 sde = new EventDetailsForm1(e);
                    sde.getF().show();

                }
            });
            

            Label sep = new Label("___________________________________________________");
            SpanLabel sp1 = new SpanLabel();
           sp1.setText("Wildlife, we are constantly told, would run loose across our towns and cities were it not"
         + "for the sport hunters to control their population, as birds would blanket the skies without the culling "
                   + "services of Ducks Unlimited and other groups. Yet here they are breeding wild animals, year after year "
                   + "replenishing the stock, all for the sole purpose of selling and killing them, deer and bears and elephants "
                   + "so many products being readied for the market. Animals such as deer, we are told, have no predators in many"
                   + " areas, and therefore need systematic culling.");
            
            c1.addAll(label2,img1);
            //c1.addAll(label2,img1);
          c.addAll(sp1);
        
        }
        
        current.addAll(c1);
        current.add("___________________________________________________________________________________");
        current.addAll(c);
            
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////-- DON'T EDIT BELOW THIS LINE!!!


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
