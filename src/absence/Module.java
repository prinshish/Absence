package absence;

/***********************************************************************
 * Module:  Module.java
 * Author:  Kukaa
 * Purpose: Defines the Class Module
 ***********************************************************************/

import java.util.*;
public class Module {
   private String id_module;
   private String nom;
   private String id_filiere;
   private String id_classe;
   private int nb_matiere;

    public Module(String id_module, String nom, String id_classe) {
        this.id_module = id_module;
        this.nom = nom;
        this.id_classe=id_classe;
    }
    

    public String getId_module() {
        return id_module;
    }

    public void setId_module(String id_module) {
        this.id_module = id_module;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(String id_filiere) {
        this.id_filiere = id_filiere;
    }

    public String getId_classe() {
        return id_classe;
    }

    public void setId_classe(String id_classe) {
        this.id_classe = id_classe;
    }

    public int getNb_matiere() {
        return nb_matiere;
    }

    public void setNb_matiere(int nb_matiere) {
        this.nb_matiere = nb_matiere;
    }
   
   

}