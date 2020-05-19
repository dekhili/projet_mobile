
package com.mycompany.myapp.entities;


/**
 *
 * @author MONDHER
 */
public class Product {
    private int id;
    private String nompr;
    private int quantity;
    private String descrip;
    private float prix;
    private String image;
    private int idCategory;
    private String barcode;
    
  

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }
    
    
    public Product(int id, String nompr, String descrip, float prix) {
        this.id = id;
        this.nompr = nompr;
        this.descrip = descrip;
        this.prix = prix;
    }

   

    public Product(int id, String nompr, int quantity, String descrip, float prix, String image,int idCategory) {
        this.id = id;
        this.nompr = nompr;
        this.quantity = quantity;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
        this.idCategory=idCategory;
       
    }

   public Product(int id, String nompr, int quantity, String descrip, float prix, String image) {
        this.id = id;
        this.nompr = nompr;
        this.quantity = quantity;
        this.descrip = descrip;
        this.prix = prix;
        this.image = image;
        
       
    }
    
    
    
       public Product(int id, String nompr, float prix) {
        this.id = id;
        this.nompr = nompr;
        this.prix = prix;
    }

    public Product(int id, String nompr) {
        this.id = id;
        this.nompr = nompr;
    }
    

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
    
    public String getImage() {
        return image;
    }
    
    

    public void setImage(String image) {
        this.image = image;
    }

   

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNompr() {
        return nompr;
    }

    public void setNompr(String nompr) {
        this.nompr = nompr;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", nompr=" + nompr + ", quantity=" + quantity + ", descrip=" + descrip + ", prix=" + prix + ", image=" + image + ", idCategory=" + idCategory + ", barcode=" + barcode + '}';
    }
    
    
    
    

   
     public String toString(double prix) {
         String ch;
        return ch = toString(prix);
                }
    
    
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   

   
      
}
