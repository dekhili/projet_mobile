/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ProductService;
import com.mycompany.myapp.services.ServiceAvis;
import java.util.List;

/**
 *
 * @author sarra
 */
public class ReclamationForm  extends Form{
    
    Form current;
    Form f2;
    Form f3;
private Resources theme;

public ReclamationForm (Form previous){
    
     current=this;
     theme = UIManager.initFirstTheme("/theme_2");
     
     current.setTitle("Troubleshooting");
     current.setLayout(BoxLayout.y());
     //current.setUIID("bg1");

    
     current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             SavForm sav = new SavForm();
             sav.getF().show();
         }
     });
     
/*************************************************************************** Search ***************************************************************************/ 

     current.getToolbar().setGlobalToolbar(true);
     Style s = UIManager.getInstance().getComponentStyle("Title");
     TextField searchField = new TextField("", " Search"); // <1>
     searchField.getHintLabel().setUIID("Title");
     searchField.setUIID("Title");
     current.getToolbar().setTitleComponent(searchField);
     FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
     searchField.addDataChangeListener((i1, i2) -> { // <2>
         String t = searchField.getText();
         if(t.length() < 1) {
             for(com.codename1.ui.Component cmp : current.getContentPane()) {
                 cmp.setHidden(false);
                 cmp.setVisible(true);
             }
         } 
         else {
             t = t.toLowerCase();
             for(com.codename1.ui.Component cmp : current.getContentPane()) {
                 String val = null;
                 if(cmp instanceof Label) {
                     val = ((Label)cmp).getText();
                 } 
                 else {
                     if(cmp instanceof TextArea) {
                         val = ((TextArea)cmp).getText();
                     } 
                     else {
                         val = (String)cmp.getPropertyValue("text");
                     }
                 }
                 boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                 cmp.setHidden(!show); // <3>
                 cmp.setVisible(show);
             }
         }
         current.getContentPane().animateLayout(250);
});
     current.getToolbar().addCommandToRightBar("", null, (e) -> {
         searchField.startEditingAsync(); // <4>
});
     
     ServiceReclamation es = new ServiceReclamation();
     ArrayList<Reclamation> listReclamation = new ArrayList<>();
     listReclamation = es.getAllreclamation();
     for (Reclamation r : listReclamation) 
     {
         
         Label problem = new Label("Your Problem :");
         problem.setUIID("prob_titre"); 
         
         Label prob = new Label(r.getProbleme());
         prob.setUIID("prob_nom"); 
         
         Label ligne = new Label("---------------------------------------------------");
         ligne.setUIID("ligne"); 
         
         Button show = new Button ("more..");
         show.setUIID("next_button"); 

         
         
          show.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ee) {
                 
                 Form f3=(Form) new Form(r.getNomproduit(),new BoxLayout(BoxLayout.Y_AXIS));
                 
/****************************************************** Back ********************************************************************************/
                 
                 f3.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         ReclamationForm avis = new ReclamationForm(current);
                         avis.getF().show();
                     }
                 });
                 
                 
                 if(r.getEtat().equals("En_attend")){
                     
/****************************************************** Delete ********************************************************************************/
                     f3.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_DELETE, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent ee) {
                         if (Dialog.show("Alert", "You Want To Delete", "OK", "Cancel"))
                         {
                             ServiceReclamation ser = new ServiceReclamation();
                             ser.suppReclamation(r.getId());
                             ReclamationForm h=new ReclamationForm(current);
                             h.getF().show();
                         }}
                 });
                     
/****************************************************** Edit ********************************************************************************/
                     
                     f3.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent ee) {
                         f2 = new Form("Edit");
                         f2.setLayout(BoxLayout.y());
                         
                         TextField  tfpb = new TextField("","Your Problem");
                         tfpb.setUIID("yourproblem"); 

                         Button b = new Button("Edit");
                         b.setUIID("add_rec");

                         tfpb.setText(r.getProbleme());
                         f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         f3.show();
                     }
                 });
                         f2.add(tfpb);
                         f2.add(b);
                         f2.show();
                         b.addActionListener((e)->{
                             Reclamation s = new Reclamation ();
                             int   id = s.getId();
                             s.setProbleme(tfpb.getText());
                             s.setId(id);
                             ServiceReclamation ser = new ServiceReclamation();
                             ser.modifReclamation(s, r.getId());
                             Dialog.show("Alert", "You Want To Edit ? ", "OK", "Cancel");
                             tfpb.clear();
                             ReclamationForm h=new ReclamationForm(current);
                             h.getF().show();
                         });}
                 });
                     
 /*************************************************************************** detail ***************************************************************************/ 

                     
                 }
                 
                 Label pb_titre = new Label("Problem :");
                 pb_titre.setUIID("prob_titre"); 
                 
                 Label pb = new Label(r.getProbleme());
                 pb.setUIID("prob_nom"); 
                 
                 Label prod_titre = new Label("Product Name :");
                 prod_titre.setUIID("prob_titre"); 
                 
                 Label prod = new Label(r.getNomproduit());
                 prod.setUIID("prob_nom"); 
                 
                 Label desc_titre = new Label("Description :");
                 desc_titre.setUIID("prob_titre"); 
                 
                 Label desc = new Label(r.getDesc());
                 desc.setUIID("prob_nom");
                 
                 Label etat_titre = new Label("Etat :");
                 etat_titre.setUIID("prob_titre"); 
                 
                 Label etat = new Label(r.getEtat());
                 etat.setUIID("prob_nom");
                 
                 Button delete = new Button("Delete");
                 //delete.setUIID("btndown");

                 Button edit = new Button("Update");
                 //edit.setUIID("btnup");


         
         Image imgUrl; 
         Image placeholder = Image.createImage(600, 550); 
         EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
         imgUrl = URLImage.createToStorage(encImage, "" + r.getImage(), "http://localhost/projet_3a/symfony/web/images/" + r.getImage());
         ImageViewer img = new ImageViewer(imgUrl);
         
         Container cimg = new Container(BoxLayout.y());
         cimg.add(img);
         cimg.setUIID("image");

         
         Container cinfo = new Container(BoxLayout.y());
         cinfo.addAll( prod_titre, prod, desc_titre, desc, pb_titre, pb , etat_titre, etat);
         
         Container caction = new Container(BoxLayout.x());
         caction.addAll(edit, delete);

                 f3.addAll(cimg, cinfo);
                 f3.show();
                 ;;}
           });
         
         
         if (r.getUser()==MyApplication.user.getId())
         {
             current.addAll(problem, prob, show, ligne);
         }
         else
         {
             current.add("");
         }
         
         current.show();
         
         
}
/*************************************************************************** Add ***************************************************************************/ 
 Button add_rec = new Button("New Troubleshooting");
 add_rec.setUIID("add_rec");
 add_rec.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent evt) {
         Form fProduit=(Form) new Form("TroubleShooting",new BoxLayout(BoxLayout.Y_AXIS));
         
         fProduit.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                                ReclamationForm sav = new ReclamationForm(current);
                                sav.getF().show();                          
                            
                            }
                        });
         
         ProductService es = new ProductService();
         List<Product> listp = new ArrayList<>();
         listp = es.getAllProduit();
         
         for (Product p : listp) {
                
                Image placeholder2 = Image.createImage(320,300); 
                EncodedImage encImage2 = EncodedImage.createFromImage(placeholder2, false);
                URLImage imgUrl2 = URLImage.createToStorage(encImage2, "http://localhost/projet_3a/symfony/web/images/" + p.getImage(), "http://localhost/projet_3a/symfony/web/images/" + p.getImage());
                ImageViewer img2 = new ImageViewer(imgUrl2);

                
                Label prod_titre = new Label("Product Name :");
                prod_titre.setUIID("prob_titre"); 
                
                Label prod_nom = new Label(p.getNompr());
                prod_nom.setUIID("prob_nom"); 
                
                Label desc_titre = new Label("Description:");
                desc_titre.setUIID("prob_titre"); 
                
                Label desc_nom = new Label(p.getDescrip());
                desc_nom.setUIID("prob_nom"); 
                
                
                
                Button next = new Button("next");
                next.setUIID("next_button"); 



                Label ligneP = new Label("___________________________________________________________");
                ligneP.setUIID("ligne"); 

                Container cdetail = new Container(BoxLayout.y());
                cdetail.addAll(prod_titre,prod_nom,desc_titre,desc_nom);
                
                Container c_img_deatil = new Container(BoxLayout.x());
                c_img_deatil.addAll(img2,cdetail);
                
                
                
                
                next.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent evt) {
                          
                          Form addReclamation=(Form) new Form("New Troubleshooting",new BoxLayout(BoxLayout.Y_AXIS));

                          
                          addReclamation.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                                fProduit.show();                            }
                        });
                          
                          
                          Label nom_prod = new Label(p.getNompr());
                          nom_prod.setUIID("prob_titre"); 

                          
                          ImageViewer imgrec=new ImageViewer();
                          imgrec.setImage(theme.getImage("add_rec.png"));
                          
                          Container cimgrec  = new Container(BoxLayout.y());
                          cimgrec.add(imgrec);
                          cimgrec.setUIID("add_rec_img"); 

                                  
                          
                          Label text = new Label("Please fill this form to send your problem.");
                          text.setUIID("yourproblem"); 

                          TextField tfp = new TextField("","Your Problem ....");
                          tfp.setUIID("yourproblem"); 

                          
                          Button btnValider = new Button("Add ");
                          btnValider.setUIID("add_rec");

                          
                          
                          btnValider.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent evt) {
                                  if ((tfp.getText().length()==0))
                                      Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                                  else
                                  {
                                      Reclamation t = new Reclamation(tfp.getText());
                                      if( ServiceReclamation.getInstance().addReclamation(t , MyApplication.user.getId(), p.getId()))
                                          Dialog.show("Success"," Your problem : "+tfp.getText()+" Was successfully added ", new Command("OK"));
                                      else
                                          Dialog.show("ERROR", "Server error", new Command("OK"));
                                      new ReclamationForm(current).show();
                                  }
                              }
                          });
                          addReclamation.addAll( tfp, btnValider);
                          addReclamation.show();
                      }
                  });
                


                
                fProduit.addAll(c_img_deatil,next,ligneP);
                fProduit.show();
                
            }
         }
     });
 
 current.add(add_rec);
 
 }

    
public Form getF() {
    return current;
}


}
