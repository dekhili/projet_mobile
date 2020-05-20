/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.fos_user;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author berrahal
 */
public class editprofile {

    Form f;
    private Resources theme;
    Button btnaff;

    public editprofile() {
        /*f.getToolbar().addCommandToRightBar("retour", null, (ev) -> {

            Profile h = new Profile();

            h.getF().show();
        });
         */
        theme = UIManager.initFirstTheme("/theme_panier");
        f = new Form("Mon profil");
        f.setLayout(BoxLayout.yCenter());
        f.setUIID("bg3");
        Label l = new Label();
        TextField nom = new TextField("", "Nom d'utilisateur");
        TextField email = new TextField("", "Email");
        btnaff = new Button("Modifier");
        f.add(nom);
        f.add(email);
        f.add(btnaff);
        Label password = new Label("if you want to change your passworld please visit our webapplication");
        password.setAutoSizeMode(true);
        f.add(password);
        System.err.println(MyApplication.user.getPassword());
        email.setText(MyApplication.user.getEmail());
        nom.setText(MyApplication.user.getUsername());
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
             Profile sav = new Profile();
             sav.getF().show();
         }
     });

        btnaff.addActionListener((evt) -> {
            UserService us = new UserService();
            fos_user u = new fos_user();
            u.setUsername(nom.getText());
            u.setEmail(email.getText());
            u.setId(MyApplication.user.getId());
            us.updateUser(u);
            System.out.println(MyApplication.user.getId());
            HomeForm h = new HomeForm();

            h.show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
