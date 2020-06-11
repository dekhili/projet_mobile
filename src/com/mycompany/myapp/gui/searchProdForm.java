
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
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
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.searchProdService;

/**
 *
 * @author MONDHER
 */

public class searchProdForm extends HomeForm{
    
    Form current;
    
    public searchProdForm(Form previous,String i)  {
        current = this;
 
        current.setTitle("Search : "+i);
        current.setLayout(BoxLayout.y());
   

        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        
           tb.addMaterialCommandToOverflowMenu("Logout", FontImage.MATERIAL_INPUT, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();

            }
        });
        
          current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new ListProdsForm();
                hf.getF().show();
           
        });

        Container c1 = new Container(BoxLayout.y());
        Image imgUrl;
        
        for (Product p : searchProdService.getInstance().getAllProds(i)) {

            Label label0 = new Label(" ");
            c1.add(label0);
            Label label1 = new Label(" ");
            c1.add(label1);

            Label label2 = new Label("Product Name :            "+p.getNompr());
            c1.add(label2);
            Image placeholder = Image.createImage(800, 700);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);

            imgUrl = URLImage.createToStorage(encImage, "http://localhost/huntkingdom/web/images/" + p.getImage(), "http://localhost/huntkingdom/web/images/" + p.getImage());
            ImageViewer img1 = new ImageViewer(imgUrl);

            
        //Quantite
        Container c2 = new Container(BoxLayout.x());
        final FontImage qt = FontImage.createMaterial(FontImage.MATERIAL_STORE, "Label", 6);
        c2.add(qt);
        c2.add(new Label("Quantity : "));
        Integer desc = p.getQuantity();
        String aux = desc.toString(desc);
        c2.add(new Label(aux));

            
        //Description
        Container c3 = new Container(BoxLayout.x());
        final FontImage des = FontImage.createMaterial(FontImage.MATERIAL_DESCRIPTION, "Label", 6);
        c3.add(des);
        c3.add(new Label("Description : "));
        c3.add(new Label(p.getDescrip()));
        

        //PRIX
        Container c4 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c4.add(prx);
        c4.add(new Label("Prix : "));
        Float prix = p.getPrix();
        String s1 = Float.toString(prix);
        c4.add(new Label(s1));
        c4.add(new Label("DT"));
          
            c1.add(img1);
            c1.add(c2);
            c1.add(c3);
            c1.add(c4);
        }
        
        current.add(c1);
       
}
}