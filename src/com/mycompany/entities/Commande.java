/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Mohamed
 */
public class Commande {
    
    private int id;
    private int prix;
    private String etat;

    public Commande() {
    }

    public Commande(int id, int prix, String etat) {
        this.id = id;
        this.prix = prix;
        this.etat = etat;
    }

    public Commande(int prix, String etat) {
        this.prix = prix;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", prix=" + prix + ", etat=" + etat + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
