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
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.services.ServiceAvis;

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
     current.setUIID("bg9");
     current.setLayout(BoxLayout.y());
     current.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             AddReclamationForm addR = new AddReclamationForm();
             addR.getF().show();
         }
     });
     
     
     current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             SavForm sav = new SavForm();
             sav.getF().show();
         }
     });
     
     ServiceReclamation es = new ServiceReclamation();
     ArrayList<Reclamation> listReclamation = new ArrayList<>();
     listReclamation = es.getAllreclamation();
     for (Reclamation r : listReclamation) 
     {
         Container c1 = new Container(BoxLayout.y());
         Container c2 = new Container(BoxLayout.x());
          
         Label problem = new Label("Problem :"+r.getProbleme());
         problem.setUIID("problemerec"); 

         
         Label nompr = new Label("Product :   "+r.getNomproduit());
         nompr.setUIID("problemerec"); 
         Label pb = new Label("Problem :"+r.getProbleme());
         pb.setUIID("problemerec"); 
         Label nometat = new Label("Etat :   "+r.getEtat());
         nometat.setUIID("problemerec"); 


         Label ligne = new Label("_______________________________________________________________________________________");
         Label ligne2 = new Label("_______________________________________________________________________________________");
         Label ligne3 = new Label("________________________________________________________________________________________");
         Label ligne4 = new Label("________________________________________________________________________________________");
         
         Image imgUrl; 
                 Image placeholder = Image.createImage(600, 550); 
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 imgUrl = URLImage.createToStorage(encImage, "" + r.getImage(), "http://localhost/projet_3a/symfony/web/images/" + r.getImage());
                 ImageViewer img = new ImageViewer(imgUrl);
                 Container cimg = new Container(BoxLayout.yCenter());cimg.add(img);
         
         Button btnshow = new Button("show");
         //if(r.getEtat().equals("En_attend"))

         c2.add(problem);
         
           c2.addPointerPressedListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ee) {
                 Form f3=(Form) new Form(r.getNomproduit(),new BoxLayout(BoxLayout.Y_AXIS));
                 f3.setUIID("bg8");
                 f3.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         ReclamationForm avis = new ReclamationForm(current);
                         avis.getF().show();
                     }
                 });
                 if(r.getEtat().equals("En_attend")){
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
                     f3.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent ee) {
                         f2 = new Form("Edit");
                         f2.setUIID("bg1");
                         f2.setLayout(BoxLayout.yCenter());
                         
                         TextField  tfpb = new TextField("","Your Problem");
                         Button b = new Button("Edit");
                         tfpb.setText(r.getProbleme());
                         f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
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
                     
                 }

                 
         
                 f3.addAll(cimg, nompr,  pb, nometat);
                 
         
                
                 f3.show();
                  
             
                 ;;}
           });
           
               
           current.addAll(c2, ligne);
     }   
 }

    
public Form getF() {
    return current;
}
}
