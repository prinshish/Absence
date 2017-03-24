package absence;

/***********************************************************************
 * Module:  Student.java
 * Author:  Kukaa
 * Purpose: Defines the Class Student
 ***********************************************************************/

import java.util.*;

/** @pdOid 9020cd8d-724c-44f2-ac89-fec8812862db */
public class Student extends Personne {
   /** @pdOid acc23cb1-5d0b-4d35-9341-7318118c0a16 */
   private String CNE,Nom,Prenom,Filiere,Classe;

    public Student(String Classe) {
        this.Classe = Classe;
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

    public String getFiliere() {
        return Filiere;
    }

    public void setFiliere(String Filiere) {
        this.Filiere = Filiere;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public Student(String Nom, String Prenom,String CNE,  String Filiere, String Classe) {
        this.CNE = CNE;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Filiere = Filiere;
        this.Classe = Classe;
    }
   

}