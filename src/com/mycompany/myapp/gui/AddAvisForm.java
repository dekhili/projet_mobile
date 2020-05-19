/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceAvis;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
/**
 *
 * @author sarra
 */
public class AddAvisForm extends Form {
    
Form current;
Form f3;
private Resources theme;

 public AddAvisForm() {
    theme = UIManager.initFirstTheme("/theme_2");

     
    current=this;
        setTitle("New Rating");
        setLayout(BoxLayout.yCenter());

         current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           AvisForm avis = new AvisForm(current);
           avis.getF().show();
            }
        });

       
        /*Container c1 = new Container();
        Container c2 = new Container();
        Container c3 = new Container();
        Container c4 = new Container();
        Container c5 = new Container();

        




         ImageViewer img1=new ImageViewer();
         img1.setImage(theme.getImage("aa.png"));
         c1.add(img1);
         
         ImageViewer img2=new ImageViewer();
         img2.setImage(theme.getImage("aa.png"));
         c2.add(img2);

         
         ImageViewer img3=new ImageViewer();
         img3.setImage(theme.getImage("aa.png"));
         c3.add(img3);

         
         ImageViewer img4=new ImageViewer();
         img4.setImage(theme.getImage("aa.png"));
         c4.add(img4);

         
         ImageViewer img5=new ImageViewer();
         img5.setImage(theme.getImage("aa.png"));
         c5.add(img5);

       
       

         c1.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                   
                        Avis t = new Avis();
                        if( ServiceAvis.getInstance().addAvis1(t))
                        {
                         Dialog.show("Success","Bad" , new Command("OK"));
                            f3 = new Form("show");
                            TextField tpb = new TextField("","Decrivez le probleme");
                            TextField tpt = new TextField("","titre");

                            f3.getToolbar().addCommandToLeftBar("Plus tard", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    AvisForm avis = new AvisForm(current);
                                    avis.getF().show();
                                }
                            });
                            f3.getToolbar().addCommandToRightBar("Envoyer", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if ((tpb.getText().length()==0))
                                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                                    else
                                    {
                                        Reclamation t = new Reclamation(tpb.getText(), tpt.getText());
                                        if( ServiceReclamation.getInstance().addReclamation(t))
                                            Dialog.show("Success"," Your problem : "+tpb.getText()+" Was successfully added ", new Command("OK"));
                                        else
                                            Dialog.show("ERROR", "Server error", new Command("OK"));
                                        new AvisForm(current).show();
                                    }
                                }
                            });
                            
          
                            f3.addAll(tpb , tpt);
                            f3.show(); 
                            //AvisForm h=new AvisForm(current);
                            //h.getF().show();
                        }
            }
        });     
        
        c2.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                   
                        Avis t = new Avis();
                        if( ServiceAvis.getInstance().addAvis2(t)){
                         Dialog.show("Success","Way" , new Command("OK"));

                            //new AddReclamationForm().show();
AvisForm h=new AvisForm(current);
                        h.getF().show();
                        }

                   
            }
        });     
        
        c3.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                   
                        Avis t = new Avis();
                        if( ServiceAvis.getInstance().addAvis3(t)){
                            Dialog.show("Success","Good" , new Command("OK"));
                        AvisForm h=new AvisForm(current);
                        h.getF().show();
                        }

                    
            }
        });     
        
        c4.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                    
                        Avis t = new Avis();
                        if( ServiceAvis.getInstance().addAvis4(t)){
                            Dialog.show("Success","Very Good" , new Command("OK"));
                        AvisForm h=new AvisForm(current);
                        h.getF().show();
                        }

                   
            }
        });     
        
        c5.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                    
                        Avis t = new Avis();
                        if( ServiceAvis.getInstance().addAvis5(t)){
                            Dialog.show("Success","Exellent" , new Command("OK"));
                        
                        AvisForm h=new AvisForm(current);
                        h.getF().show();
                        }
                   
            }
        });   
        
        current.addAll(c1, c2, c3, c4, c5); 
        //current.addAll(img1, img2, img3, img4, img5);  
*/
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
    Button btnval=new Button("valider");
    
    starRank.addActionListener((e)->{
    lbnote.setText("Note :"+starRank.getProgress());
        
        
      btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               
                  
                        Avis t = new Avis(starRank.getProgress());

                        if( ServiceAvis.getInstance().ajouterAvis(t))
                            
                            Dialog.show("Success"," you rated : "+starRank.getProgress(), new Command("OK"));

                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    new AvisForm(current).show();

                    
                    
                    
                
                
                
            }
        });   
        
        });
   
    
    
    current.add(FlowLayout.encloseCenter(starRank));
    current.add(btnval);
    
            


    
    
    
    

        
            
    }
            
        
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}     
        
                 
       
        
        
       

      
            
      

    public Form getF() {
               


        return current;
    }
    
    
}
