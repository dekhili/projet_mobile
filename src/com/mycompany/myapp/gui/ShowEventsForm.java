/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
//import com.mycompany.Entity.UserEvent;
import com.mycompany.myapp.services.ServiceEvents;
//import com.mycompany.Service.UserEventService;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.utils.Share;


/**
 *
 * @author fedy
 */
public class ShowEventsForm {

    Form f;
    int id_event;
    Events e = new Events(id_event);
    int user_id;

    //int SelectedID;
    public ShowEventsForm(int id) {

        Toolbar.setGlobalToolbar(true);

        this.id_event = id;
        f = new Form();
f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            EventsForm sb = new EventsForm();
            sb.getF().show();
        });
        ServiceEvents es = new ServiceEvents();

        e = es.getEvent2(id);

        Container c1 = new Container(BoxLayout.y());

        Label label = new Label(e.getTitre());
        c1.add(label);
        

        Image imgUrl;
        Image placeholder = Image.createImage(400, 300);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/projet_pidev/symfony/web/Events/" + e.getNom_image(), "http://localhost/projet_pidev/symfony/web/Events/" + e.getNom_image());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

        Container c2 = new Container(BoxLayout.x());
        
        final FontImage time = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, "Label", 6);
        c2.add(time);
        c2.add(new Label("Date : "));
        c2.add(new Label(e.getDate()));

        Container c3 = new Container(BoxLayout.x());
        final FontImage adr = FontImage.createMaterial(FontImage.MATERIAL_ROOM, "Label", 6);
        c3.add(adr);
        c3.add(new Label("Adresse : "));
        c3.add(new Label(e.getLocalisation()));
        

        Container c4 = new Container(BoxLayout.x());
        final FontImage descimg = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c4.add(descimg);
        c4.add(new Label("Description : "));
        Label desc = new Label("voir description ...");
        String s = e.getDescription();

        desc.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Dialog.show("Description", s, "OK", null);

            }
        });

        c4.add(desc);

        Container c5 = new Container(BoxLayout.x());

        Button btnPart = new Button("Participer");
        btnPart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                 EventsForm sb = new EventsForm();
                 sb.getF().show();

              /* if (MyApplication.user == null) {

                    Dialog.show("Description", "Vous devez connecter pour participer", "OK", null); 

                } else {

                    if (e.getType().equals("Custom")) {

                        if (!exist()) {

                            UserEventService uvs = new UserEventService();
                            int i = uvs.addUserEvent(MyApplication.user.getId(), id);

                            GenarateQR qr = new GenarateQR(i);

                        } else {

                            Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

                        }

                    } else if (e.getType().equals("places")) {

                        if ((e.getNbPart()) < (e.getNbPlaces())) {

                            if (!exist()) {

                                UserEventService uvs = new UserEventService();
                                int i = uvs.addUserEvent(MyApplication.user.getId(), id);
                                GenarateQR qr = new GenarateQR(i);

                            } else {

                                Dialog.show("Accée réfusée", "Vous avez déjà participé à cet événement!", "OK", null);

                            }

                        } else {

                            Dialog.show("Accée réfusée", "Nous sommes désolé il ya plus de places !", "OK", null);

                        }

                    }

                }*/
            }
        });

        Button btnPartager = new Button("Partager");
        btnPartager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt1) {
                
                Share s = new Share();
                s.shareFacebook(e.getNom_image(),id);

            }
        });
        

        
        c5.add(btnPart);
        c5.add(btnPartager);


        c1.add(c2);
        c1.add(c3);
        c1.add(c4);
        c1.add(c5);

        f.add(c1);
        

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
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

}





