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
import com.codename1.ui.Display;
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
import com.mycompany.myapp.entities.Panier;
import static com.mycompany.myapp.gui.PaiementOrder.isNotInteger;
import com.mycompany.myapp.services.FactureService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cyrine
 */
public class CommandeForm extends Form{
     Form f1;
     private Resources theme;
 public CommandeForm(Form previous) {
    theme = UIManager.initFirstTheme("/theme_2_1");
    
        Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        Font smallPlainSystemFontbi = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        Font smallPlainSystemFontbi1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        
        setTitle("Enter information");
        setLayout(BoxLayout.y());
       setUIID("bg1");
        //phone number
        Label num=new Label("Phone number","labelcommande");
        num.getUnselectedStyle().setFont(smallPlainSystemFontbi);
        TextField numtf = new TextField(""," +216 ");
        numtf.setConstraint(TextField.PHONENUMBER);
        // adress
        Label adr=new Label("Adress","labelcommande");
        adr.getUnselectedStyle().setFont(smallPlainSystemFontbi);
        TextField adrtf= new TextField("", "Adress");
        //date
        Label datel=new Label("Delivery date","labelcommande");
        datel.getUnselectedStyle().setFont(smallPlainSystemFontbi);
        Picker date = new Picker(); 
        date.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        System.out.println(date.getText()+"\n");
        String jour=date.getText().substring(0,2);
        String mois=date.getText().substring(3,5)+"-";
        String annee="20"+date.getText().substring(6,8)+"-";
        String dates=annee+mois+jour;
        
        //btn valider et annuler
       Container btn = new Container(BoxLayout.xCenter());
        Button btnValider = new Button("validate");
        Button Cancel= new Button("Cancel");
        
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
       
       Cancel.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                 HomeForm h = new HomeForm();
                    h.getF().show();

            }  });
         
      
      btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((numtf.getText().length() == 0) || (adrtf.getText().length() == 0)) {
                    Dialog.show("Alert", "Oups Champs vide !", new Command("OK"));
                } else if (isNotInteger(numtf.getText()) || numtf.getText().length()!=8) {
                   Dialog.show("Erreur", "Phone Number can only contain 8 numbers", "OK", null);}
                
                else {
                    try {
                        Facture e = new Facture(numtf.getText(), adrtf.getText(),dates);
                        Panier p = new Panier();
                        if( FactureService.getInstance().newfact(e))
                        { Dialog.show("Validate information ", "to confirm ! ", new Command("ok"));
                        PaiementOrder w = new PaiementOrder(p);
                    w.getF().show();
                        }else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));}
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Ouuuuuups !! note doit être des entiers", new Command("OK"));
                    }
                   
                }

            }
        });
      
      btn.addAll(Cancel,btnValider);
        
        addAll(num,numtf,adr,adrtf,datel,date,btn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
          
 }
 
   public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
}
