package absence;

/***********************************************************************
 * Module:  Classe.java
 * Author:  Kukaa
 * Purpose: Defines the Class Classe
 ***********************************************************************/

import java.util.*;

public class Classe {
   private String id_classe;
   private int effectif;
   private String id_filiere;
   public java.util.Collection<Module> module;

    public Classe( String id_filiere, String id_classe, int effectif) {
        this.id_filiere=id_filiere;
        this.id_classe = id_classe;
        this.effectif = effectif;
    }

    public Classe(String id_classe) {
        this.id_classe = id_classe;
    }
    
    
   
//   
//   
//   /** @pdGenerated default getter */
//   public java.util.Collection<Module> getModule() {
//      if (module == null)
//         module = new java.util.HashSet<Module>();
//      return module;
//   }
//   
//   /** @pdGenerated default iterator getter */
//   public java.util.Iterator getIteratorModule() {
//      if (module == null)
//         module = new java.util.HashSet<Module>();
//      return module.iterator();
//   }
//   
//   /** @pdGenerated default setter
//     * @param newModule */
//   public void setModule(java.util.Collection<Module> newModule) {
//      removeAllModule();
//      for (java.util.Iterator iter = newModule.iterator(); iter.hasNext();)
//         addModule((Module)iter.next());
//   }
//   
//   /** @pdGenerated default add
//     * @param newModule */
//   public void addModule(Module newModule) {
//      if (newModule == null)
//         return;
//      if (this.module == null)
//         this.module = new java.util.HashSet<Module>();
//      if (!this.module.contains(newModule))
//         this.module.add(newModule);
//   }
//   
//   /** @pdGenerated default remove
//     * @param oldModule */
//   public void removeModule(Module oldModule) {
//      if (oldModule == null)
//         return;
//      if (this.module != null)
//         if (this.module.contains(oldModule))
//            this.module.remove(oldModule);
//   }
//   
//   /** @pdGenerated default removeAll */
//   public void removeAllModule() {
//      if (module != null)
//         module.clear();
//   }

    public String getId_classe() {
        return id_classe;
    }

    public void setId_classe(String id_classe) {
        this.id_classe = id_classe;
    }
    
    public String getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(String id_filiere) {
        this.id_filiere = id_filiere;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

   
}


