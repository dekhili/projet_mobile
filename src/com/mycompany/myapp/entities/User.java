/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author cyrine
 */

public class User {

    private int idu;
    private String nom;
    private String email;
    private String password;
    private String roles;
    
    
    
    /*-------------------------------------------*/

    public User() {
    }

    public User(int idu, String nom, String email, String password, String roles) {
        this.idu = idu;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User( String nom, String email, String password) {
        
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles="user";
        }

   
    
    
    /*----------------------------------------------*/
     public int getId() {
      return idu;
     }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    
    
    /*-------------------------------------------------*/

    @Override
    public String toString() {
        return "User{" + "idu=" + idu + ", nom=" + nom + ", email=" + email + ", password=" + password + ", roles=" + roles + '}';
    }

   

  
    
    
    
}
