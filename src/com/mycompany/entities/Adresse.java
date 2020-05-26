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
public class Adresse {
    
    private int id;
    private int numTel;
    private String mail;
    private String pays;
    private String ville;
    private int pinCode;

    public Adresse() {
    }

    public Adresse(int numTel, String mail, String pays, String ville, int pinCode) {
        this.numTel = numTel;
        this.mail = mail;
        this.pays = pays;
        this.ville = ville;
        this.pinCode = pinCode;
    }

    public Adresse(int id, int numTel, String mail, String pays, String ville, int pinCode) {
        this.id = id;
        this.numTel = numTel;
        this.mail = mail;
        this.pays = pays;
        this.ville = ville;
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Adresse{" + "id=" + id + ", numTel=" + numTel + ", mail=" + mail + ", pays=" + pays + ", ville=" + ville + ", pinCode=" + pinCode + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
    
}
