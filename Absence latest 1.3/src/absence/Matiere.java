package absence;

/***********************************************************************
 * Module:  Matiere.java
 * Author:  Kukaa
 * Purpose: Defines the Class Matiere
 ***********************************************************************/

import java.util.*;


public class Matiere {
   private int id_matiere;
   private String Nomprof,preProf,NomMatiere;
     private String Module;

    public Matiere(String NomMatiere, String Module , String Nomprof, String preProf) {
      //  this.id_matiere = id_matiere;
        this.Nomprof = Nomprof;
        this.preProf = preProf;
        this.NomMatiere = NomMatiere;
        this.Module = Module;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public String getNomprof() {
        return Nomprof;
    }

    public void setNomprof(String Nomprof) {
        this.Nomprof = Nomprof;
    }

    public String getPreProf() {
        return preProf;
    }

    public void setPreProf(String preProf) {
        this.preProf = preProf;
    }

    public String getNomMatiere() {
        return NomMatiere;
    }

    public void setNomMatiere(String NomMatiere) {
        this.NomMatiere = NomMatiere;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String Module) {
        this.Module = Module;
    }

  
}