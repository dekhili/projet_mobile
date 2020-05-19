/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.mycompany.myapp.entities.Events;
import com.mycompany.myapp.entities.Reservations;

/**
 *
 * @author h^
 */
public class ShowReservationForm {

    Form f;
    int id_event;
    Events e = new Events(id_event);
    int id_reservation;
    Reservations r = new Reservations();

    public ShowReservationForm(Reservations reservation){
        this.id_event = r.getEvent_id();
        f = new Form();
         Toolbar.setGlobalToolbar(true);
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            EventsForm sb = new EventsForm();
            sb.getF().show();
        });
        
        Label l = new Label(Integer.toString(id_event));
        f.add(l);
    }
 public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
