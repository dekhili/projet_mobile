
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.email.durgesh.Email;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;

/**
 *
 * @author MONDHER
 */

public class SendEmailForm extends HomeForm {
     Form f;

    SendEmailForm() {
        
       setTitle("Send Email");
      f = new Form();
     f.setLayout(BoxLayout.y());
      Toolbar tb = f.getToolbar();
      f=this;

       f.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
       
             current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new StoreForm();
                hf.getF().show();
           
        });
             
   
    Container c0 = new Container(BoxLayout.y());
    final FontImage sub = FontImage.createMaterial(FontImage.MATERIAL_SUBJECT, "Label", 6);
    c0.add(sub);
    TextField tfSubj = new TextField("","Subject");
    c0.add(tfSubj);
    Container c1 = new Container(BoxLayout.y());
    final FontImage rec = FontImage.createMaterial(FontImage.MATERIAL_MAIL, "Label", 6);
    c1.add(rec);
    TextField tfSender = new TextField("", "Sender Address", 20, TextField.EMAILADDR);
    c1.add(tfSender);
    Container c2 = new Container(BoxLayout.y());
    final FontImage sd = FontImage.createMaterial(FontImage.MATERIAL_FINGERPRINT, "Label", 6);
    c2.add(sd);
    TextField tfPassword = new TextField("","Password", 20, TextField.PASSWORD);
    c2.add(tfPassword);
    Container c3 = new Container(BoxLayout.y());
    final FontImage msg = FontImage.createMaterial(FontImage.MATERIAL_MESSAGE, "Label", 6);
    c3.add(msg);
    TextField tfMsg = new TextField("","Message");
    c3.add(tfMsg);
    
    Button SendEmail = new Button("SEND",FontImage.createMaterial(FontImage.MATERIAL_SEND, "Label", 6));
    Button ClearFields = new Button("REFRESH",FontImage.createMaterial(FontImage.MATERIAL_REFRESH, "Label", 6));
      
     SendEmail.addActionListener( new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               try {
                   Email email = new Email (tfSender.getText(),tfPassword.getText()); //Email Adress Sender + password
                   
                   //FROM
                   email.setFrom(tfSender.getText() , "Client Service");
                   
                   //Subject
                   
                   email.setSubject(tfSubj.getText());
                   
                   //Message
                   
                   email.setContent(tfMsg.getText() , "text/html");
                   
                   //TO
                   
                   email.addRecipient("huntkingdom.store@gmail.com");
                   
                   //Send
                   email.send();
            
               } catch (MessagingException ex) {
                  System.out.println(ex);
               } catch (UnsupportedEncodingException ex) {
                  System.out.println(ex);
               }
             } 
             });
     
       ClearFields.addActionListener( new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
              tfSubj.clear();
              tfSender.clear();
              tfPassword.clear();
              tfMsg.clear();
           }
      });
             
     f.add(c0);
     f.add(c1);
     f.add(c2);
     f.add(c3);
     f.addAll(SendEmail,ClearFields);
 
             }
    
     @Override
     public Form getF() {
        return f;
    }

    @Override
    public void setF(Form f) {
        this.f = f;
    }     
}
