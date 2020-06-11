
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Category;
import com.mycompany.myapp.services.CategoryService;
import java.util.ArrayList;

/**
 *
 * @author MONDHER
 */

public class ListCategoryForm extends HomeForm {
    
     private Form current;
     private int id_cat;
    
    public ListCategoryForm() {
    
       setTitle("List Of Categories");
      current=this;
        
       current.getToolbar().addCommandToOverflowMenu("Logout", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
                          new HomeForm().show();

         }
     });
       
           current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new StoreForm();
                hf.getF().show();
           
        });
           
        //Find products By Category
        Container c8 = new Container(BoxLayout.y());
        final FontImage sr = FontImage.createMaterial(FontImage.MATERIAL_FILTER, "Label", 6);
        c8.add(sr);
        Container c9 = new Container(BoxLayout.x());
        TextField InputCat = new TextField("","Filter By Category");
        Button Filterbtn = new Button("Filter");
        Filterbtn.setUIID("ButtonSearch");
        Filterbtn.addActionListener((e) -> {
            String a = InputCat.getText();
            new FilterByCatForm(current,a).show();
        });
        c9.add(InputCat);
        c9.add(Filterbtn);
        c8.add(c9);
        current.add(c8); 
          
        CategoryService ps = new CategoryService();
        ArrayList<Category> listCats = new ArrayList<>();
        listCats = ps.getAllCats();
      

        for (Category c : listCats) {
         
        this.id_cat = c.getId();
        Container c0 = new Container(BoxLayout.y());
        final FontImage des = FontImage.createMaterial(FontImage.MATERIAL_CATEGORY, "Label", 6);
        c0.add(des);
             Label nomCat = new Label("Category Name :       " +c.getNomcat());
             c0.add(nomCat);
     
        //UnderCategory
        Container c2 = new Container(BoxLayout.x());
        c2.add(new Label("Name Under Category : "));
        c2.add(new Label(c.getSouscat()));
        c0.add(c2);
        current.add(c0);

        }
    }
    
     public Form getF() {
        return current;
    }

    public void setF(Form current) {
        this.current = current;
    }   
}
