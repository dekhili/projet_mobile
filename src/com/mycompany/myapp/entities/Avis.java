/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author sarra
 */
public class Avis {
    private int id;
    private int produit;
    private int user;
    private String note;
    private String nomproduit;
    private String image;
    private String nomuser;
    private int rate;

    public Avis(int id, int produit, int user, String nomproduit, int rate, String image) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.nomproduit = nomproduit;
        this.rate = rate;
        this.image = image;

    }
    

     public Avis( int rate) {
        this.rate = rate;
    }

    public Avis(int produit, int rate) {
        this.produit = produit;
        this.rate = rate;
    }

    public Avis(int id, int produit, int user, String nomproduit, String nomuser, int rate,String image ) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
        this.rate = rate;
        this.image = image;

    }

    

    

   
    
    
    

    public Avis(int produit, int user, String rate, String nomproduit, String nomuser) {
        this.produit = produit;
        this.user = user;
        this.note = rate;
        this.nomproduit = nomproduit;
        this.nomuser = nomuser;
    }

    public Avis(int id, int produit, int user, String note) {
        this.id = id;
        this.produit = produit;
        this.user = user;
        this.note = note;
    }

    public Avis(int produit, int user, String note) {
        this.produit = produit;
        this.user = user;
        this.note = note;
    }

    public Avis() {
        
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

  
    
    
}
