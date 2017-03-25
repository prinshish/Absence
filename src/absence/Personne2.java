package absence;

/***********************************************************************
 * Module:  Personne.java
 * Author:  Kukaa
 * Purpose: Defines the Class Personne
 ***********************************************************************/

import java.util.*;

/** @pdOid 649eb19c-0283-4078-8c2f-609c8c656e7e */
public  class Personne2  {
   /** @pdOid d0b04790-be35-42f3-8158-140b66804b74 */
   private int id_personne;
   /** @pdOid 724631f5-4012-4a03-8e5a-fdf5fbdaeb55 */
   private String nom;
   /** @pdOid ac48be98-619a-41a7-8f24-3c74a16cd83e */
   private String prenom;
   /** @pdOid 3dbbdce2-6e7b-4898-872f-fab1341be47e */
   private String CIN;
   /** @pdOid c4a2cca9-c55f-4067-b544-b9c247c03925 */
   private String email;
   /** @pdOid a4bc388c-9d1f-4fb5-862c-96f55ea60406 */
   private String login;
   /** @pdOid c1141253-f11c-45ad-8536-c285a8fcf794 */
   private String mdp;

    public Personne2(int id_personne, String nom, String prenom, String CIN, String email,  String login, String mdp) {
        this.id_personne = id_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.email = email;
        this.login = login;
        this.mdp = mdp;
    }

    public int getId_personne() {
        return id_personne;
    }

    public void setId_personne(int id_personne) {
        this.id_personne = id_personne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    
}