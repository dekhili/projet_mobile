/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.services.FactureService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cyrine
 */
public class CommandeForm extends Form{
     Form f1;
     private Resources theme;
 public CommandeForm(Form previous) {
    theme = UIManager.initFirstTheme("/theme_2");
    
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font smallPlainSystemFontbi = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Font smallPlainSystemFontbi1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
        Label num=new Label("Phone number");
        num.getUnselectedStyle().setFont(smallPlainSystemFontbi);
        TextField numtf = new TextField(""," +216 ");
        numtf.setConstraint(TextField.PHONENUMBER);
        Label adr=new Label("Adress");
        adr.getUnselectedStyle().setFont(smallPlainSystemFontbi);
        TextField adrtf= new TextField("", "Adress");
        Label datel=new Label("Delivery date");
        datel.getUnselectedStyle().setFont(smallPlainSystemFontbi);
       Picker date = new Picker();
        
        
       // TextField adrtf= new TextField(d);
        
        Button btnValider = new Button("Add task");
        
      /*  btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfStatus.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Task t = new Task(Integer.parseInt(tfStatus.getText()), tfName.getText());
                        if( ServiceTask.getInstance().addTask(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });*/
      
      
      btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((numtf.getText().length() == 0) || (adrtf.getText().length() == 0)) {
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                } else {
                   
                        Facture e = new Facture(numtf.getText(), adrtf.getText(),date.getText());
                        FactureService.getInstance().newfact(e);
                 
                           Dialog.show("Validate information ", "to confirm ! ", new Command("ok"));
                        new PayerForm(f1).show();
                      
                }

            }
        });
      
      
        
        addAll(num,numtf,adr,adrtf,datel,date,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
          
 }
}
