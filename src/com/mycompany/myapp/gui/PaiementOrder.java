package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Facture;
import com.mycompany.myapp.entities.Lignepanier;
import com.mycompany.myapp.entities.PaiementStripe;
import com.mycompany.myapp.entities.Panier;
import com.mycompany.myapp.services.FactureService;
import com.mycompany.myapp.services.PanierService;


import com.stripe.model.Charge;
import com.stripe.model.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author berrahal
 */
public class PaiementOrder extends Form{
   
    int id;
   Panier e = new Panier(id);
   
Form f;
  private Resources theme;
    Container c3 = new Container(BoxLayout.y());
    Container c1 = new Container(BoxLayout.x());

    Button btnaff = new Button("Payer");
 
    public PaiementOrder(Panier panier) {
        theme = UIManager.initFirstTheme("/theme_panier");
       //final FontImage back = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,"Label", 6);
       final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 11);
       final FontImage bike = FontImage.createMaterial(FontImage.MATERIAL_PAYMENT, "Label", 11);
          
              
       f = new Form("Payment  ",new FlowLayout(CENTER, CENTER));
        f.setUIID("panierform");
          
        TextField num = new TextField("", "Numéro de carte") ;
        TextField mois = new TextField("", "mois d'expirtion") ;
        TextField annee = new TextField("", "annee d'expiration") ;
        TextField cvv = new TextField("", "CVC") ;
        TextField email = new TextField("", "Adresse email") ;
               
                btnaff.addActionListener((evt) -> {
                    if (num.getText()=="" || mois.getText()=="" || annee.getText()=="" || email.getText()=="" ) {
                        Dialog.show("Erreur", "Merci de vérifier vos informations" , "OK", null); 
                    }
                     else if ((!email.getText().contains("@")) || (!email.getText().contains("."))) {
                           Dialog.show("Erreur", "Email non valide", "OK", null);}
                    else if (isNotInteger(cvv.getText())) {
                           Dialog.show("Erreur", "CVC ne peut contenir que des chiffres", "OK", null);}
                    else if (cvv.getText().length() != 3) {
                           Dialog.show("Erreur", "CVC doit contenir 3 chiffres", "OK", null);}
                    else if (num.getText().length() != 16) {
                           Dialog.show("Erreur", "Code erroné ", "OK", null);
                    } else{ 
                        int mois0 = Integer.parseInt(mois.getText());
                        int annee0 = Integer.parseInt(annee.getText());
                       
        
        
         Token token = PaiementStripe.getToken("pk_test_TYooMQauvdEDq54NiTphI7jx", num.getText(), mois0, annee0, cvv.getText(), email.getText());
         if(token !=null){ 
                 PanierService ps = new PanierService();
               List<Panier> listp = new ArrayList<>();
               listp = ps.getAllPanier();  
            for (Panier p : listp) {
            Charge ch= PaiementStripe.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", p.getPrixtotal(),"sk_test_4eC39HqLyjWDarjtT1zdp7dc",num.getText(), mois0, annee0, cvv.getText(), email.getText());
       }
             FactureService fs = new FactureService();
               List<Facture> listf = new ArrayList<>();
               listf = fs.getAllFacture();  
            for (Facture f : listf) {
            fs.payer(f,f.getIdfact());
            }
            
            Dialog.show("Succès", "Le paiement a été effectué avec succès" , "OK", null); 
            new HomeForm().show();
           
       
 }
 else
 {
     Dialog.show("Erreur", "Merci de vérifier vos informations" , "OK", null); 
 }
                 } });
            
            c1.add(prx);
            c1.add(bike);
           // c3.add();
                c3.add(num);
                c3.add(mois);
                c3.add(annee);
                c3.add(cvv) ;
                c3.add(email);
                c3.add(btnaff);
              

f.add(c1);
f.add(c3);
    }
         public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
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
