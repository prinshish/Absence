/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

/**
 *
 * @author Zineb Regragui
 */
public class absence1 {
    String CNE, Nom , Prenom,Justificatif;
   String absence;

    public absence1(String CNE, String Nom, String Prenom, String Justificatif, String absence) {
        this.CNE = CNE;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Justificatif = Justificatif;
        this.absence = absence;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getJustificatif() {
        return Justificatif;
    }

    public void setJustificatif(String Justificatif) {
        this.Justificatif = Justificatif;
    }

    public String isAbsence() {
        return absence;
    }

    public void setAbsence(String absence) {
        this.absence = absence;
    }
    
    
}
