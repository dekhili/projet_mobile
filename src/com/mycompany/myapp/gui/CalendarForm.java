/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.services.ServiceEvents;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Emir
 */
public class CalendarForm extends HomeForm{
     Form f;
    private Resources theme;
    Events e = new Events();
    ServiceEvents se = new ServiceEvents();
    public CalendarForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public CalendarForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        theme = UIManager.initFirstTheme("/theme_2_1");
        initGuiBuilderComponents(resourceObjectInstance);
        setLayout(BoxLayout.y());
        setScrollableY(true);
        getContentPane().setScrollVisible(false);
        getToolbar().setUIID("Container");
        Button b = new Button(" ");
        b.setUIID("Container");
        getToolbar().setTitleComponent(b);
        getTitleArea().setUIID("Container");
       //  installSidemenu(resourceObjectInstance);
        gui_Calendar_1.setTwoDigitMode(true);
        
        Picker p = new Picker();
        b.addActionListener(e -> {
            p.pressed(); 
            p.released();
        });
        p.addActionListener(e -> {
            gui_Calendar_1.setCurrentDate(p.getDate());
            gui_Calendar_1.setSelectedDate(p.getDate());
            gui_Calendar_1.setDate(p.getDate());
            
        });
        p.setFormatter(new SimpleDateFormat("MMMM"));
        p.setDate(e.getDate());
        p.setUIID("CalendarDateTitle");
        Container cnt = BoxLayout.encloseY(
                p,
                new Label(resourceObjectInstance.getImage("calendar-separator.png"), "CenterLabel")
        );
        
        BorderLayout bl = (BorderLayout)gui_Calendar_1.getLayout();
        Component combos = bl.getNorth();
        gui_Calendar_1.replace(combos, cnt, null);
        
        
        Label jour = new Label("Daily events :");
        jour.setUIID("CalendarTitle");
        add(jour);

        
        Image imgUrl1;

        ArrayList<Events> listEvents = new ArrayList<>();
        Date date = p.getDate();
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate1 = formatter1.format(date);  
        listEvents = se.getEventsByDate(strDate1);
        for (Events e : listEvents) {
            Date d = e.getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(d);  
            Image placeholder1 = Image.createImage(300, 250);
            EncodedImage encImage2 = EncodedImage.createFromImage(placeholder1, false);

            imgUrl1 = URLImage.createToStorage(encImage2, "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image());
            ImageViewer img2 = new ImageViewer(imgUrl1);


        add(createEntry(resourceObjectInstance, false, strDate, e.getLocalisation(), e.getTitre(), e.getDescription()+" "+ e.getNbrPlaces()+ " places disponibles."));
        add(img2);
       // add(createEntry(resourceObjectInstance, true, "12:20", "13:20", "Taco Bell", "Lunch", "Detra Mcmunn, Ami Koehler", "contact-b.png", "contact-c.png"));
       // add(createEntry(resourceObjectInstance, false, "16:15", "17:10", "3B, 2nd Floor", "Design Meeting", "Bryant Ford, Ami Koehler, Detra Mcmunn", "contact-a.png"));
    
          }
        
         Label sep = new Label("_____________________________________________________:");
        sep.setUIID("Quotes");
        add(sep);
        
         Label venir = new Label("Upcoming events :");
        venir.setUIID("CalendarTitle");
        add(venir);
        
        
        
         ArrayList<Events> listEvents1 = new ArrayList<>();
        listEvents = se.getAllEvents2();
        for (Events e : listEvents) {
            Date d = e.getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(d);  
            Image placeholder1 = Image.createImage(300, 250);
            EncodedImage encImage2 = EncodedImage.createFromImage(placeholder1, false);

            imgUrl1 = URLImage.createToStorage(encImage2, "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + e.getNom_image());
            ImageViewer img2 = new ImageViewer(imgUrl1);
            


        add(createEntry(resourceObjectInstance, false, strDate, e.getLocalisation(), e.getTitre(), e.getDescription()+" "+ e.getNbrPlaces()+ " places disponibles."));
        add(img2);
       // add(createEntry(resourceObjectInstance, true, "12:20", "13:20", "Taco Bell", "Lunch", "Detra Mcmunn, Ami Koehler", "contact-b.png", "contact-c.png"));
       // add(createEntry(resourceObjectInstance, false, "16:15", "17:10", "3B, 2nd Floor", "Design Meeting", "Bryant Ford, Ami Koehler, Detra Mcmunn", "contact-a.png"));
    
          }
      
        
        
        
        
    }

    private Container createEntry(Resources res, boolean selected, String date, String location, String title, String description, String... images) {
       /* Component time = new Label(startTime, "CalendarHourUnselected");
        if(selected) {
            time.setUIID("CalendarHourSelected");
        }*/
        
        Container circleBox = BoxLayout.encloseY(new Label(res.getImage("label_round-selected.png")),
                new Label("-", "OrangeLine"),
                new Label("-", "OrangeLine")
        );
        
        Container cnt = new Container(BoxLayout.x());
        for(String att : images) {
            cnt.add(res.getImage(att));
        }
        Container mainContent = BoxLayout.encloseY(
                BoxLayout.encloseX(
                        new Label(title, "calandarfont"), 
                        new Label("-", "calandarfont1"), 
                        new Label(date, "calandarfont1"), 
                        new Label("-", "calandarfont1")),
                new Label(description, "calandarfont1"),
                cnt
        );
        
        Label redLabel = new Label("", "RedLabelRight");
        FontImage.setMaterialIcon(redLabel, FontImage.MATERIAL_LOCATION_ON);
        Container loc = BoxLayout.encloseY(
                redLabel,
                new Label("Location:", "TinyThinLabelRight"),
                new Label(location, "calandarfont1")
        );
        
        mainContent= BorderLayout.center(mainContent).
                add(BorderLayout.WEST, circleBox);
        
        return BorderLayout.center(mainContent).
             //   add(BorderLayout.WEST, FlowLayout.encloseCenter(time)).
                add(BorderLayout.EAST, loc);
    }
    
//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Calendar gui_Calendar_1 = new com.codename1.ui.Calendar();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
         setLayout(new com.codename1.ui.layouts.GridLayout(2, 1));
         setTitle("");
         setName("CalendarForm");
         addComponent(gui_Calendar_1);
        gui_Calendar_1.setName("Calendar_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    protected boolean isCurrentCalendar() {
        return true;
    }

    protected void initGlobalToolbar() {
        setToolbar(new Toolbar(true));
    }
    
    
    
    
}
