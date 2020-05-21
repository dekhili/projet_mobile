/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Objects;

/**
 *
 * @author h^
 */
public class Reservations {
    
    private int id;
    private int quantite;
    private double prixpaye;
    private int event_id;
    private int user_id;
    private String titre;
    private String description;
    private String username;

    public Reservations(int id) {
        this.id = id;
    }

    public Reservations(int quantite, double prixpaye, int event_id) {
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.event_id = event_id;
    }

    public Reservations(int id, int event_id) {
        this.id = id;
        this.event_id = event_id;
    }
    
    
    

    public Reservations() {
    }
    
    

    public Reservations(int quantite, double prixpaye) {
        this.quantite = quantite;
        this.prixpaye = prixpaye;
    }
    
    

    public Reservations( int quantite, double prixpaye, String titre ,  String username) {
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.titre = titre;
        this.username = username;
        
    }

    public Reservations(int id, int quantite, double prixpaye, int event_id) {
        this.id = id;
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.event_id = event_id;
    }
    
    

    public Reservations(int id,int quantite, double prixpaye, String titre) {
        this.id= id;
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.titre = titre;
    }

    public Reservations(int quantite, double prixpaye, int event_id , int user_id) {
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.event_id = event_id;
        this.user_id = user_id;
    }

   public Reservations(int quantite, double prixpaye, String titre) {
        this.quantite = quantite;
        this.prixpaye = prixpaye;
        this.titre = titre;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixpaye() {
        return prixpaye;
    }

    public void setPrixpaye(double prixpaye) {
        this.prixpaye = prixpaye;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        final Reservations other = (Reservations) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
   
        if (this.event_id != other.event_id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

 /*   @Override
    public String toString() {
        return "Reservations{"+ ", quantite=" + quantite + ", prixpaye=" + prixpaye + ", event_id=" + event_id +'}';
    }*/

    @Override
    public String toString() {
        return "Reservations{" + "id=" + id + ", quantite=" + quantite + ", prixpaye=" + prixpaye + ", event_id=" + event_id + '}';
    }
    
    

    
    

 

    
    
    
    
}
