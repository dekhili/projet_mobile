/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.ServiceEvents;
import com.mycompany.myapp.services.ServiceAvis;
import com.mycompany.myapp.services.ServiceReclamation;

import java.util.ArrayList;

/**
 *
 * @author sarra
 */
public class AvisForm extends Form {
    
Form current;
Form f2;

private Resources theme;


 public AvisForm(Form previous) {
     
     theme = UIManager.initFirstTheme("/theme_2");
     current=this;
     current.setTitle("Rating");
     current.setLayout(BoxLayout.y());
     
     current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             SavForm sav = new SavForm();
             sav.getF().show();
         }
     });
     
     ServiceAvis es = new ServiceAvis();
     ArrayList<Avis> listAvis = new ArrayList<>();
     listAvis = es.getAllavis();
     for (Avis a : listAvis) {
         
         Container c1 = new Container(BoxLayout.x());
         
         Label nompr = new Label(""+a.getNomproduit()+ " :    ");
         nompr.setUIID("prob_titre"); 
         
         Label ligne = new Label("---------------------------------------------------");
         ligne.setUIID("ligne"); 
        
         
         ImageViewer img1=new ImageViewer();
         img1.setImage(theme.getImage("note.png"));
         
         ImageViewer img2=new ImageViewer();
         img2.setImage(theme.getImage("note.png"));
         
         ImageViewer img3=new ImageViewer();
         img3.setImage(theme.getImage("note.png"));
         
         ImageViewer img4=new ImageViewer();
         img4.setImage(theme.getImage("note.png"));
         
         ImageViewer img5=new ImageViewer();
         img5.setImage(theme.getImage("note.png"));
         
         if(a.getRate()==1)
             c1.addAll(nompr, img1);
         else if(a.getRate()==2)
             c1.addAll(nompr, img1, img2);
         else if(a.getRate()==3)
             c1.addAll(nompr, img1, img2, img3);
         else if(a.getRate()==4)
             c1.addAll(nompr, img1, img2, img3, img4);
         else if(a.getRate()==5)
             c1.addAll(nompr, img1, img2, img3, img4, img5);
        

         Container c2 = new Container();
         
         
         if(a.getUser()== MyApplication.user.getId())
         c2.addAll(c1 , ligne);
        c2.addPointerPressedListener(new ActionListener() {
             
             @Override
             public void actionPerformed(ActionEvent ee) {
                 Form f3=(Form) new Form(a.getNomproduit(),new BoxLayout(BoxLayout.Y_AXIS));
                 f3.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         AvisForm avis = new AvisForm(current);
                         avis.getF().show();
                     }
                 });
                  f3.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_DELETE, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent ee) {
                         if (Dialog.show("Alert", "You Want To Delet ?", "OK", "Cancel")){
                             ServiceAvis ser = new ServiceAvis();
                             ser.suppAvis(a.getId());
                             AvisForm h=new AvisForm(current);
                             h.getF().show();
                         }}
                 });
                  f3.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent ee) {
                         f2 = new Form("Edit",new BoxLayout(BoxLayout.Y_AXIS));

                         f2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent evt) {
                         f3.show();
                     }
                 });
                         setLayout(BoxLayout.y());
                         TextField  tfpb = new TextField("","Your Problem");
                         Container c = new Container(BoxLayout.y());
                         Slider starRank = new Slider();
                         starRank.setEditable(true);
                         starRank.setMinValue(1);
                         starRank.setMaxValue(6);
                         Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                                 derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
                         Style s = new Style(0xffff33, 0, fnt, (byte)0);
                         Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                         s.setOpacity(100);
                         s.setFgColor(0);
                         Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                         initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                         initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                         initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                         initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                         starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                         
                         Label lbnote=new Label("Note :0");
                                          lbnote.setUIID("prob_titre"); 

                         Button btnval=new Button("valider");
                         
                             lbnote.setText("Note :"+starRank.getProgress());
                             
                             btnval.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent evt) {
                
               
                  
                         Avis s = new Avis ();
                             int   id = s.getId();
                             s.setRate(starRank.getProgress());
                             s.setId(id);
                             ServiceAvis ser = new ServiceAvis();
                             ser.modifAvis(s, a.getId());
                             Dialog.show("Alert", "You Want To Edit ? ", "OK", "Cancel");
                             tfpb.clear();
                             AvisForm h=new AvisForm(current);
                             h.getF().show();

                    
                    
                    
                
                
                
            }
        });   
                               //  tfpb.setText(a.getRate());
                       
                       Container c2 = new Container();
         
         
         
    c.addAll(FlowLayout.encloseCenter(starRank),btnval);
                             f2.add(c);


                         f2.show();
                 
                                 ;}
                 });
                 
                 
                 
                 Label prod_titre = new Label("Product Name :");
                 prod_titre.setUIID("prob_titre"); 
                 
                 Label prod = new Label(a.getNomproduit());
                 prod.setUIID("prob_nom"); 
                 
                 Label desc_titre = new Label("Description :");
                 desc_titre.setUIID("prob_titre"); 
                 
                 Label desc = new Label(a.getDesc());
                 desc.setUIID("prob_nom");
                 
                 
                 
                 Image imgUrl; 
                 Image placeholder = Image.createImage(600, 550); 
                 EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                 imgUrl = URLImage.createToStorage(encImage, "" + a.getImage(), "http://localhost/projet_3a/symfony/web/images/" + a.getImage());
                 ImageViewer img = new ImageViewer(imgUrl);
                 Container cimg = new Container(BoxLayout.y());
                 cimg.add(img);
                          cimg.setUIID("image");

                 
                 
                 
                 
                 Container c2 = new Container(BoxLayout.x());
                 
                 ImageViewer img1=new ImageViewer();
                 img1.setImage(theme.getImage("note.png"));
         
                 ImageViewer img2=new ImageViewer();
                 img2.setImage(theme.getImage("note.png"));
         
                 ImageViewer img3=new ImageViewer();
                 img3.setImage(theme.getImage("note.png"));
         
                 ImageViewer img4=new ImageViewer();
                 img4.setImage(theme.getImage("note.png"));
         
                 ImageViewer img5=new ImageViewer();
                 img5.setImage(theme.getImage("note.png"));
                 
                 if(a.getRate()== 1 )
                     c2.add( img1);
                 else if(a.getRate()== 2)
                     c2.addAll( img1, img2);
                 else if(a.getRate()== 3 ) 
                     c2.addAll( img1, img2, img3);
                 else if(a.getRate()== 4 )
                     c2.addAll( img1, img2, img3, img4);
                 else if(a.getRate()== 5 )
                     c2.addAll( img1, img2, img3, img4, img5);
                 
                 
                 Button b = new Button("Edit");
                 Button d = new Button("Delet");
                 
                 Container cinfo = new Container(BoxLayout.y());
         cinfo.addAll( prod_titre, prod, desc_titre, desc);
                 f3.addAll( cimg, cinfo, c2);
                 f3.show();
                
   
                 ;}
         });
         

         current.add(c2 );
         
     }
 }
 
 public static boolean isNumeric (String strNum)
     {
         try
         {double d = Double.parseDouble(strNum); }
         catch (NumberFormatException | NullPointerException nfe) {
             return false;
         }
         return true;
     } 
 public Form getF() {
        return current;
    }
 private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}     
    
    
}
