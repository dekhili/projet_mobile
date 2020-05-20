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
import com.codename1.ui.Toolbar;
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
import com.mycompany.myapp.entities.Avis;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.ServiceAvis;
import com.mycompany.myapp.services.ServiceEvents;

/**
 *
 * @author fedy
 */
public class EvaluationForm  extends Form {

    Form current;
    Form f;
    private Resources theme;

    Evaluations r = new Evaluations();
    int event_id;
    Events e = new Events(event_id);

    public EvaluationForm(Events events) {
            theme = UIManager.initFirstTheme("/theme_2");
       Container c = new Container(BoxLayout.y());

        //  this.event_id = id;
        current = new Form("Evaluations  ",new FlowLayout(CENTER, CENTER));
        
        current.setUIID("panierform");
        current.setLayout(BoxLayout.yCenter());
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

        current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            EventsForm1 hf = new EventsForm1();
            hf.getF().show();

        });

        setLayout(BoxLayout.y());

        ServiceEvents se = new ServiceEvents();

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c3.add(adr);
        //e = se.getEvent2(event_id);
        c3.add(new Label("Titre : ","calandarfont"));
        c3.add(new Label(events.getTitre(),"calandarfont"));
        current.add(c3);

        Container c0 = new Container(BoxLayout.y());
        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);

        c0.add(img1);
        

        TextField tfnote = new TextField("", "note");
        TextField tfcmnt = new TextField("", "comment");
        Button btnValider = new Button(" ");
         btnValider.setUIID("ButtonNew");

        /*btnValider.addPointerPressedListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnote.getText().length() == 0) || (tfcmnt.getText().length() == 0)) {
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                } else {
                    try {
                        if(Integer.parseInt(tfnote.getText())<=10){
                              Evaluations ev = new Evaluations(Integer.parseInt(tfnote.getText()), tfcmnt.getText());
                        if(Dialog.show("CONFIRMATION !! ","Voullez vous vraiment attribuer la note "+ev.getNote()+" à cet evenement?","OUI","NON" ))
                        {
                            System.out.println(ev.getId());
                          //  boolean addEvaluation = EvaluationService.getInstance().addEvaluation(ev, events.getId());
                            Dialog.show("SUCCESS", "Vous avez bien evaluer l'evenement "+events.getTitre()+" avec une note de "+ev.getNote(),new Command("ok"));
                            ShowEvaluationForm ef = new ShowEvaluationForm(events,ev);
                            ef.show();                        }
                        }
                        else{
                          Dialog.show("Error", "Ouuuuuups !! note doit être inférieur à 10 ",new Command("ok"));

                        }
                      
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Ouuuuuups !! note doit être des entiers", new Command("OK"));
                    }

                }

            }
        });*/
        
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
    
   Label note=new Label("Note : ","calandarfont");
    Label lbnote=new Label("Note : ","calandarfont");
    Button btnval = new Button(" ");
        btnval.setUIID("ButtonNew");
    
    starRank.addActionListener((e)->{
    lbnote.setText("Note :"+starRank.getProgress());
        
        
      btnval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
               
                  
                        Evaluations t = new Evaluations(starRank.getProgress(),tfcmnt.getText() );

                        if( EvaluationService.getInstance().addEvaluation(t, events.getId()))
                            
                            Dialog.show("Success"," you rated : "+starRank.getProgress(), new Command("OK"));

                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    new AvisForm(current).show();

                    
                    
                    
                 ShowEvaluationForm ef = new ShowEvaluationForm(events,t);
                            ef.show();         
                
                
            }
        });   
        
        });
    
   
   
    c.addAll(FlowLayout.encloseCenter(starRank),btnval);
    
    current.addAll(tfcmnt,note,c);
    
        
        
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

    public void setF(Form current) {
        this.current = current;
    }

}
