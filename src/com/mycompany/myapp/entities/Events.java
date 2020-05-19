/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author h^
 */
public class Events {

    public int id;
    private String titre;
    private String description;
    private String date;
    public int nbrPlaces;
    private double prix;
    private String localisation;
    private int idPro;
    private String nom_image;

    public Events(int id, String titre, String description, int nbrPlaces, double prix, String localisation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
    }

    public Events(int id) {
        this.id = id;
    }
    
    
    
    
    
    public Events() {
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = 1;
    }
    
    public Events (String titre) {
        this.titre = titre;
    }

    public Events(int id, int nbrPlaces) {
        
        this.nbrPlaces=nbrPlaces;
        this.id = id;
    }

    public Events(int id, String titre) {
        this.id = id;
        this.titre = titre;
    }
    
    
    

    public Events(int id, String titre, String description, String date, int nbrPlaces, double prix, String localisation, String nom_image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.nom_image = nom_image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro, String nom_image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = idPro;
        this.nom_image = nom_image;
    }

    public Events(int id, String titre, String description, String date, int nbrPlaces, double prix, String localisation, int idPro, String nom_image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.idPro = idPro;
        this.nom_image = nom_image;
    }
    
    

    
    public Events(int id ,String titre, String description, double prix, String date, int nbrPlaces, String nom_image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.prix = prix;
        this.nbrPlaces = nbrPlaces;
        this.nom_image = nom_image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation, String nom_image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
        this.nom_image = nom_image;
    }

    public Events(String titre, String description, String date, int nbrPlaces, double prix, String localisation) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.localisation = localisation;
    }

    public Events(int id ,String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
    
    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    @Override
    public String toString() {
        return "Events{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", nbrPlaces=" + nbrPlaces + ", prix=" + prix + ", localisation=" + localisation + ", idPro=" + idPro + ", Image=" + nom_image + '}';
    }
    
    

    
    

}
