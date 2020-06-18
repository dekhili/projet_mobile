/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.notifications.LocalNotification;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;
import com.mycompany.myapp.services.ServiceReservation;
import com.teknikindustries.bulksms.SMS;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Fedy
 */
public class ReservationForm extends Form {

    Form current;
    Reservations rs = new Reservations();
    int event_id;
    Events e = new Events(event_id);
    private Resources theme;

    public ReservationForm(Events events) {
        theme = UIManager.initFirstTheme("/theme_2_1");

        this.e = events;
        //  this.event_id = id;
        current = this;
        current.setTitle("Reservation");

        current.setLayout(BoxLayout.y());
        setUIID("bg2");
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
                PanierForm panier = new PanierForm();
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

        current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EventsForm2 hf = new EventsForm2();
                hf.getF().show();
            }
        });

        setLayout(BoxLayout.y());
        System.out.println(events.getId());

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_TITLE, "Label", 6);
        c3.add(adr);
        //e = se.getEvent2(event_id);
        c3.add(new Label("               "));
        c3.add(new Label(events.getTitre()));
        current.add(c3);

        Container c2 = new Container(BoxLayout.x());
        final FontImage adr1 = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c2.add(adr1);
        c2.add(new Label("Price : "));

        c2.add(new Label(Double.toString(events.getPrix())));
        c2.add(new Label("dinars"));

        current.add(c2);

        Container c0 = new Container(BoxLayout.y());
        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image(), "http://localhost/projet_3a/symfony/web/images/" + events.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);

        c0.add(img1);
        current.add(c0);

        TextField tfquantite = new TextField("", "Places you want to book :");
        //  TextField tfprix= new TextField("", "Prix");
        TextField tx1 = new TextField("", "Phone number :");
        Button btnValider = new Button(" ");
        btnValider.setUIID("ButtonNew");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfquantite.getText().length() == 0)) {
                    Dialog.show("Alert", "Empty fields !", new Command("OK"));
                } else {
                    try {
                        Reservations r = new Reservations(Integer.parseInt(tfquantite.getText()), (Integer.parseInt(tfquantite.getText()) * e.getPrix()), e.getId());

                        ServiceReservation sr = new ServiceReservation();
                        List<Reservations> listUV = new ArrayList<>();
                        listUV = sr.FindByUserID(MyApplication.user.getId(), events.getId());
                        if (!listUV.toString().equals("[]")) {
                            Dialog.show("ERROR", "Already booked", new Command("OK"));

                        } else {
                            if (ServiceReservation.getInstance().addReservation(r, events, MyApplication.user.getId())) {
                                Dialog.show("Success", "Reservation made You'll pay the day of the event an amount of : " + r.getPrixpaye() + "Dinars.", new Command("OK"));
                                //  int idRes = r.getId();
                                /*   ShowReservationForm hf = new ShowReservationForm(r);
                         hf.getF().show();*/
                                //  int quantite = r.getQuantite();
                                //     int nbrPlaces= events.getNbrPlaces()- r.getQuantite() ;

                                //  Events e1 = new Events(events.getId(), nbrPlaces);
                                //  System.out.println(e1.getNbrPlaces());
                                //e.setNbrPlaces(e1.getNbrPlaces());
                                Random rand = new Random();
                                int upperbound = 1000;
                                int int_random = rand.nextInt(upperbound);
                                System.out.println("random number : " + int_random);
                                SMS sms = new SMS();
                                String nt = "+216" + tx1.getText();
                                sms.SendSMS("fediii", "Fedi123456", "Vous avez bien reservé une place à l'évenement  " + e.getTitre() + " votre code est : " + int_random + ". Veuillez le montrer le jour de l'évenememnt pour pouvoir assister.", nt, "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

                                ShowReservationsForm ef = new ShowReservationsForm(events, r);
                                ef.show();
                                                            System.out.println(listUV.toString().equals("[]"));

                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            }
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Ouuuuuups !! quantité  doit être un entier", new Command("OK"));
                    }

                }

            }
        });

        current.addAll(tfquantite, tx1, btnValider);

    }


    /* public boolean exist() {

        UserEventService ues = new UserEventService();
        
        

        List<UserEvent> listUV = new ArrayList<>();
        listUV = ues.getListEventsByUserID2(MyApplication.user.getId());

        if (listUV != null) {
            for (UserEvent uv : listUV) {
                if (uv.getEventId() == this.id_event) {
                    return true;
                }
            }
        }
        return false;

    }*/
    public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }

}
