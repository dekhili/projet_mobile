/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author gogo-
 */
public class Lignepanier {
    private int idlp,product_id,panier_id, quantite;
    private String nompr,descrip,image;
    private double prix,prixTotal;

    
/*--------------------------------------------------*/

    public Lignepanier() {
    }

    public Lignepanier(int idlp, int product_id, String nompr, int quantite) {
        this.idlp = idlp;
        this.product_id = product_id;
        this.quantite = quantite;
        this.nompr = nompr;
    }

    public Lignepanier( int idlp,String nompr, String descrip, double prix, int quantite) {
         this.idlp = idlp;
        this.quantite = quantite;
        this.nompr = nompr;
        this.descrip = descrip;
        this.prix = prix;
    }

    public Lignepanier(int panier_id, int quantite) {
        this.panier_id = panier_id;
        this.quantite = quantite;
       
    }
    
    
    

    public Lignepanier(int idlp, String nompr, double prix, int quantite) {
        this.idlp = idlp;
        this.quantite = quantite;
        this.nompr = nompr;
        this.prix = prix;
    }

    public Lignepanier( String nompr, double prix, int quantite) {
       this.prix = prix;
        this.nompr = nompr;
         this.quantite = quantite;
    }
    
    public Lignepanier( int product_id, int panier_id, int quantite) {
      
        this.product_id = product_id;
        this.panier_id = panier_id;
        this.quantite = quantite;
    }
    
    

    public Lignepanier(int idlp, int product_id, int panier_id, int quantite) {
        this.idlp = idlp;
        this.product_id = product_id;
        this.panier_id = panier_id;
        this.quantite = quantite;
    }

   /* public Lignepanier(int idlp, int product_id, int quantite) {
        this.idlp = idlp;
        this.product_id = product_id;
        this.quantite = quantite;
    }
*/

    public Lignepanier(int idlp, int product_id, int panier_id, int quantite, String nompr, String descrip, String image, double prix, double prixTotal) {
        this.idlp = idlp;
        this.product_id = product_id;
        this.panier_id = panier_id;
        this.quantite = quantite;
        this.nompr = nompr;
        this.descrip = descrip;
        this.image = image;
        this.prix = prix;
        this.prixTotal = prixTotal;
    }
   

  
   
   

   
  /*---------------------------------------------------*/  

    public int getIdlp() {
        return idlp;
    }

    public void setIdlp(int idlp) {
        this.idlp = idlp;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(int panier_id) {
        this.panier_id = panier_id;
    }
    
    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNompr() {
        return nompr;
    }

    public void setNompr(String nompr) {
        this.nompr = nompr;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
    
    
    
    /*---------------------------------------------------*/

    @Override
    public String toString() {
        return "Lignepanier{" + "idlp=" + idlp + ", product_id=" + product_id + ", panier_id=" + panier_id + ", quantite=" + quantite + ", nompr=" + nompr + ", descrip=" + descrip + ", image=" + image + ", prix=" + prix + ", prixTotal=" + prixTotal + '}';
    }


    
}
