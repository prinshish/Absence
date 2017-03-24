package absence;

/***********************************************************************
 * Module:  Matiere.java
 * Author:  Kukaa
 * Purpose: Defines the Class Matiere
 ***********************************************************************/

import java.util.*;


public class Matiere {
  
   private String id_matiere, Nomprof,preProf,NomMatiere;
     private String Module;

    public String getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(String id_matiere) {
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

    public Matiere(String id_matiere, String Module, String Nomprof, String preProf, String NomMatiere) {
        this.id_matiere = id_matiere;
        this.Nomprof = Nomprof;
        this.preProf = preProf;
        this.NomMatiere = NomMatiere;
        this.Module = Module;
    }

}