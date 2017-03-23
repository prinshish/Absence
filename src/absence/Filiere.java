package absence;

/***********************************************************************
 * Module:  Filiere.java
 * Author:  Kukaa
 * Purpose: Defines the Class Filiere
 ***********************************************************************/

import java.util.*;

/** @pdOid 799e6a28-48f4-4840-83dd-1c7bb7bc52c2 */
public class Filiere {
   /** @pdOid 9784a61a-e422-41b3-aeec-99dd9db645fb */
   private String id_filiere;
   /** @pdOid 15746f0e-f9dd-445b-b008-ef2f0221aed0 */
   private String nom;

   public Filiere( String id_filiere) {
       this.id_filiere = id_filiere;
    } 
   
   public Filiere(String id_filiere, String nom) {
        this.id_filiere = id_filiere;
        this.nom = nom;
    }

    public String getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(String id_filiere) {
        this.id_filiere = id_filiere;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
   

}


