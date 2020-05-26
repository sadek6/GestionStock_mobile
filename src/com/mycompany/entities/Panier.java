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
public class Panier {
    
    private int id;
    private String nom;
    private int quantite;
    private int prix;

    public Panier() {
    }

    public Panier(int id, String nom, int quantite, int prix) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    
}
