
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author fedy
 */

public class StoreForm extends HomeForm {
    
Form current;

 public StoreForm() {
     
    current=this;
        current.setTitle("Store");

        current.setLayout(BoxLayout.y());
        
         Button btnListProds = new Button("List Products",FontImage.createMaterial(FontImage.MATERIAL_LIST, "Label", 6));
         btnListProds.addActionListener(e-> new ListProdsForm().show());
        
          Button btnListCats = new Button("List Categories", FontImage.createMaterial(FontImage.MATERIAL_LIST, "Label", 6));
          btnListCats.addActionListener(e-> new ListCategoryForm().show());
        
          Button btnTri = new Button("Sort Products",FontImage.createMaterial(FontImage.MATERIAL_SORT, "Label", 6));
          btnTri.addActionListener(e-> new SortListProdForm().show());
        
          Button btnMailing = new Button("Contact Administration",FontImage.createMaterial(FontImage.MATERIAL_MAIL, "Label", 6));
          btnMailing.addActionListener(e-> new SendEmailForm().show());
          
          addAll(btnListProds,btnTri,btnListCats,btnMailing); 
        
          current.setLayout(BoxLayout.y());
          Toolbar tb = current.getToolbar();
        
           current.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
                HomeForm hf = new HomeForm();
                hf.getF().show();
           
        });
 }

    public Form getF() {
        return current;
    }
    
}
