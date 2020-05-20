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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evaluations;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.EvaluationService;
import com.mycompany.myapp.services.ServiceEvents;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author sarra
 */
public class AddReclamationForm extends Form{
    Form current;
    private Resources theme;
 public AddReclamationForm (){
     
    current=this;
    theme = UIManager.initFirstTheme("/theme_2");
        setTitle("New Troubleshooting");
        setUIID("bg9");
        setLayout(BoxLayout.yCenter());
      
         current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
           ReclamationForm reclamation = new ReclamationForm(current);
           reclamation.getF().show();
            }
        });
        
        TextField tfp = new TextField("","Your Problem ....");
        Button btnValider = new Button("Add ");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfp.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                  
                        Reclamation t = new Reclamation(tfp.getText());
                        if( ServiceReclamation.getInstance().addReclamation(t))
                            
                            Dialog.show("Success"," Your problem : "+tfp.getText()+" Was successfully added ", new Command("OK"));

                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    new ReclamationForm(current).show();

                    
                    
                    
                }
                
                
            }
        });
        
        addAll(tfp, btnValider);
                
    }
        
             
      
           
    
    
    
    
      public Form getF() {
               


        return current;
    }
}

