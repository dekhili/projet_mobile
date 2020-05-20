/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.compat.Paint;
import com.codename1.components.ImageViewer;
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
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.services.PanierService;
import com.mycompany.myapp.services.LignePanierService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyrine
 */
public class PanierForm extends Form{
     Form current;
     private Resources theme;
 public PanierForm(Form previous) {
    theme = UIManager.initFirstTheme("/theme_panier");
     //  List<Lignepanier> listLigne = new ArrayList<>();
     //   LignePanierService ps=new LignePanierService();
     // ArrayList<Lignepanier> list = new ArrayList<>();
        
         Button btnValider = new Button("Add produit");
       
        /* btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Product v = new Product(1,"sac",90,"sac de chasse",50,"6666.jpg",1);
                System.out.println(LignePanierService.getInstance().addligne(v));
                    try {
                        
                        if( LignePanierService.getInstance().addligne(v))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
           
        });
*/      Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font smallPlainSystemFontbi = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Font smallPlainSystemFontbi1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
          
    current=this;
        setTitle("My Cart");
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
        
       
           
       
            
        LignePanierService es = new LignePanierService();
        List<Lignepanier> listLigne = new ArrayList<>();
        listLigne = es.getAlllignePanier();
        
        
          Image imgUrl; 
          Image imgUrl1; 
          Image imgUrlcart,imgUrlup; 
          
        Container btn = new Container(BoxLayout.xCenter());
        Container cart = new Container(BoxLayout.y());
        
       // affichage PrixTotal
          Container pri = new Container(BoxLayout.xCenter());
               PanierService ps = new PanierService();
               List<Panier> listp = new ArrayList<>();
               listp = ps.getAllPanier();  
            for (Panier p : listp) {
            Label prixt = new Label("Total amount : ");
            Label prixt1 = new Label(String.valueOf(p.getPrixtotal())+ " DT");
            prixt.getUnselectedStyle().setFont(smallPlainSystemFontbi);
            prixt1.getUnselectedStyle().setFont(smallPlainSystemFontbi1);
            pri.addAll(prixt,prixt1);
            
            }
            pri.setUIID("prixtot");
             current.add(pri);
           
       //affichage ligne panier      
       for (Lignepanier e : listLigne) {
           
         Container c2 = new Container(BoxLayout.x(),"c2");
         Container c3 = new Container(BoxLayout.x());
         Container c = new Container(BoxLayout.x());
         Container incdec = new Container(BoxLayout.x());
         
               // final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_, "Label", 11);
         //btn supprimer 
         String urlImage = "http://localhost/projet_3a/symfony/web/images/icondelete.png" ;
          EncodedImage encImage1 = EncodedImage.createFromImage(theme.getImage("icondelete.png"), false);
          imgUrl1 = URLImage.createToStorage(encImage1, ""+"icondelete.png" , urlImage);
          Button btnsup = new Button(imgUrl1);
          btnsup.setUIID("Button1");
          btnsup.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(e.getIdlp());
                    es.suppligne(e,e.getIdlp());
                    try{         
                        Dialog.show("Delete Confirmed !", "Removed product from cart ", new Command("ok"));
                        new PanierForm(current).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
           });
          
          //container aff detail produit ligne panier
          Container c1 = new Container(BoxLayout.yCenter());
           
          //image ligne panier
           Image placeholder = Image.createImage(320,300); 
           EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
           imgUrl = URLImage.createToStorage(encImage, "" + e.getImage(), "http://localhost/projet_3a/symfony/web/images/" + e.getImage());
           ImageViewer img = new ImageViewer(imgUrl);
           //nom produit ligne panier
           Label nompr = new Label("           "+e.getNompr(),"nompr");
           nompr.getUnselectedStyle().setFont(smallPlainSystemFontbi);
           //description produit ligne panier
           Label descrip = new Label("Description :   "+e.getDescrip(),"labelpanier");
           //prix produit ligne panier
           Label prix = new Label("Price :   "+String.valueOf(e.getPrix())+" DT" ,"labelpanier");
           //quantité ligne panier
           Label qte = new Label("Quantity :   "+String.valueOf(e.getQuantite()),"labelpanier");
           
           
          
           
            Button btdown = new Button("Remove");
            btdown.setUIID("btndown");
            btdown.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(e.getIdlp());
                    System.out.println(String.valueOf(e.getQuantite()));
                   if (e.getQuantite()==1)
                    { Dialog.show("minumum quantity !", "we can't decrement ", new Command("ok"));
                        new PanierForm(current).show();
                    }else{
                   es.DecQTE(e,e.getIdlp());
                    try{         
                        Dialog.show("Decrement quantity", "validation of the operation! ", new Command("ok"));
                        new PanierForm(current).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                   }  
                }
           });
            
            /*
            Label up = new Label("add");
            String urlImaup = "http://localhost/projet_3a/symfony/web/images/plus1.png" ;
          EncodedImage encImagup = EncodedImage.createFromImage(theme.getImage("plus1.png"), false);
          imgUrlup = URLImage.createToStorage(encImagup, ""+"plus1.png" , urlImaup);
         */
            
             
            Button btnup = new Button("    Add     ");
            btnup.setUIID("btnup");
            btnup.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println(e.getIdlp());
                   es.IncQTE(e,e.getIdlp());
                    try{         
                        Dialog.show("Increment quantity", "validation of the operation! ", new Command("ok"));
                        new PanierForm(current).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
           });
            
           incdec.addAll(btdown,btnup);
           
          Label ligne = new Label("-------------------------------------------------------------------------------------------------------------------------------");
          
          c1.addAll(nompr,descrip,prix,qte,incdec);
          c2.addAll(btnsup,img,c1);
          c3.add(ligne);
          
         
            current.add(c2);
            current.add(c3);
            
           }
       
     /* String urlImagecart = "http://localhost/projet_3a/symfony/web/images/cartplus.png" ;
          EncodedImage encImagecart = EncodedImage.createFromImage(theme.getImage("cartplus.png"), false);
          imgUrlcart = URLImage.createToStorage(encImagecart, ""+"cartplus.png" , urlImagecart);
          Button btncom = new Button(imgUrlcart);*/
          Button Button1 = new Button("Order");
        Button1.setUIID("Button");
       
         Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                         new CommandeForm(current).show();
            }
        });
         
         current.add(Button1);
         
    }

    public PanierForm() {
       }
     
 

    public Form getF() {
        return current;
    }
    
    
      
      
   /*     btnValider2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             
                  
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                    
                }
                
                
            });
     
        
   addAll(btnValider,btnValider2);
      SpanLabel sp = new SpanLabel();
        sp.setText(LignePanierService.getInstance().getAlllignePanier().toString());
        add(sp);  
   }*/ 

}
