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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Lignepanier;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.LignePanierService;
import com.mycompany.myapp.services.ProductService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyrine
 */
public class ListeProduct extends Form{
        Form current;
     private Resources theme;
 public ListeProduct(Form previous) {
    theme = UIManager.initFirstTheme("/theme_product");
     //  List<Lignepanier> listLigne = new ArrayList<>();
     //   LignePanierService ps=new LignePanierService();
     // ArrayList<Lignepanier> list = new ArrayList<>();
        
         Button btnValider = new Button("Add produit");
       
            Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font smallPlainSystemFontbi = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Font smallPlainSystemFontbi1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
          
    current=this;
        setTitle("Shop");
        setLayout(BoxLayout.y());
        setUIID("bg3");
        
         Toolbar tb = current.getToolbar();
        
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
         
        ProductService es = new ProductService();
        List<Product> listp = new ArrayList<>();
        listp = es.getAllProduit();
     
          for (Product p : listp) {
           Container c2 = new Container(BoxLayout.xCenter());
     
         Container c3 = new Container(BoxLayout.x());
        
         
          //container aff detail produit ligne panier
          Container c1 = new Container(BoxLayout.yCenter());
           
          //image ligne panier
           Image placeholder2 = Image.createImage(320,300); 
           EncodedImage encImage2 = EncodedImage.createFromImage(placeholder2, false);
        URLImage imgUrl2 = URLImage.createToStorage(encImage2, "http://localhost/projet_3a/symfony/web/images/" + p.getImage(), "http://localhost/projet_3a/symfony/web/images/" + p.getImage());
           ImageViewer img2 = new ImageViewer(imgUrl2);
        
           //nom produit ligne panier
           Label nompr = new Label(p.getNompr(),"nompr");
           nompr.getUnselectedStyle().setFont(smallPlainSystemFontbi);
           nompr.setUIID("nompr");
        
           String urlImagecart = "http://localhost/projet_3a/symfony/web/images/cartpan.png" ;
          EncodedImage encImagecart = EncodedImage.createFromImage(theme.getImage("cartpan.png"), false);
        URLImage imgUrlcart = URLImage.createToStorage(encImagecart, ""+"cartpan.png" , urlImagecart);
        
         ImageViewer img1 = new ImageViewer(imgUrlcart);
         Label prix= new Label("Price :   "+String.valueOf(p.getPrix())+" DT" ,"labelpanier");
         prix.setUIID("prixlab");
         
         Button add=new Button(imgUrlcart);
         add.setUIID("btncart"); 
         
       
         
          add.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
               if(p.getQuantity()>0)
               {
                 es.addligne(p);
                    try{         
                        Dialog.show("Add to Cart", "validation of the operation! ", new Command("ok"));
                        new ListeProduct(current).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Product not found", new Command("OK"));
                    }
               }
               else
               {
                   try{         
                        Dialog.show("Oups", "Out of stock !!!!!! ", new Command("ok"));   
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
               }
             }  
                
           });
         
           c2.addAll(add,prix);
          Label ligne = new Label("          _____________________________           ");
        
          c1.addAll(img2,nompr,c2);
         // c2.addAll(btnsup,img,c1);
          c3.add(ligne);
          
         
            current.add(c1);
            current.add(c3);
            
           }
       
     
       /*   Button Button1 = new Button("Order");
        Button1.setUIID("Button");
       
         Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                         new CommandeForm(current).show();
            }
        });
         
         current.add(Button1);*/
            
          
        
        
        
        
        
        
        
        
        /*    SpanLabel sp = new SpanLabel();
        sp.setText(ProductService.getInstance().getAllProduit().toString());
        current.add(sp); */
       
        
        
        
 }

  
 
 
  public Form getF() {
        return current;
    }
}
