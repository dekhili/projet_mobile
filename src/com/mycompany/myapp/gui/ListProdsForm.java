
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.services.ProductService;
import java.util.ArrayList;


/**
 *
 * @author MONDHER
 */

public class ListProdsForm extends HomeForm{

    Form f;
    private int id_prod;

    ListProdsForm() {
        
      setTitle("List Of Products");
      f = new Form();
      f.setLayout(BoxLayout.y());
      f.setUIID("bg1");
      Toolbar tb = f.getToolbar();
      f=this;
      setTitle("Products");
     
           current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new StoreForm();
                hf.getF().show();
           
        });
           
        //RECHERCHE
        Container c8 = new Container(BoxLayout.y());
        final FontImage sr = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, "Label", 6);
        c8.add(sr);
        Container c9 = new Container(BoxLayout.x());
        TextField search = new TextField("","Search");
        Button searchbtn = new Button("search");
        searchbtn.setUIID("ButtonSearch");
        searchbtn.addActionListener((e) -> {
            String a = search.getText();
            new searchProdForm(f,a).show();
        });
        c9.add(search);
        c9.add(searchbtn);
        c8.add(c9);
        f.add(c8);
        
        ProductService ps = new ProductService();
        ArrayList<Product> listProds = new ArrayList<>();
        listProds = ps.getAllProds();
     
        for (Product p : listProds) {
            
        
        this.id_prod = p.getId();

        Container c0 = new Container(BoxLayout.y());
        Container c1 = new Container(BoxLayout.x());

        Label vide = new Label("      ");
             Label nomProd = new Label("Product Name :       " +p.getNompr());
             c1.add(vide);
             c0.add(nomProd);
        
        //IMAGE 
        Image imgUrl;
        Image placeholder = Image.createImage(800, 700);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        imgUrl = URLImage.createToStorage(encImage, "http://localhost/huntkingdom/web/images/" + p.getImage(), "http://localhost/huntkingdom/web/images/" + p.getImage());
        ImageViewer img1 = new ImageViewer(imgUrl);
        c1.add(img1);

        
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
        Container c6 = new Container(BoxLayout.x());
        final FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, "Label", 6);
        c6.add(prx);
        c6.add(new Label("Prix : "));
        Float prix = p.getPrix();
        String s1 = Float.toString(prix);
        c6.add(new Label(s1));
        c6.add(new Label("DT"));

        c0.add(c1);
        c0.add(c2);
        c0.add(c3);
        c0.add(c6);
        f.add(c0);
        }
  
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
