/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zineb Regragui
 */
public class DaoMatiere {
       private DAO dao;
    private Connection MaConnexion;
    public DaoMatiere(){
        dao=new DAO();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setURL("jdbc:mysql://localhost/absence");
        dao.setLogin("root");
        dao.setPasswd("");
        dao.ToConnect();
        MaConnexion=dao.getConnexion();
    }
     public boolean Connex(String login, String mdp) throws SQLException
    {
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            PreparedStatement pst = MaConnexion.prepareStatement("Select login from personne where login = ? and mdp = ?");
            pst.setString(1,login);
            pst.setString(2,mdp);
            Rs=pst.executeQuery();
        }
        catch(SQLException ex){
            System.err.println("Pb dans la conex." + ex.getMessage());
        }
        if (Rs.next()) return true;
        return false;
    }
       public void addMatiere(String idMat,String nom, String module , int prof){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into matiere  values(?,?,?,?)");
            pst.setString(1,idMat);
            pst.setString(2,module);
            pst.setInt(3,prof);
            pst.setString(4,nom);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout Matiere "+ex.getMessage());
        }
       }
       public void updateMatiere( String id_module ,String Nom ,int id_prof, String id_matiere) 
    {
    try 
        {
            PreparedStatement pst;
            pst = MaConnexion.prepareStatement("update Matiere set id_module = ? , id_personne = ? , nom = ? where id_matiere=?");
            pst.setString(1, id_module);
            pst.setInt(2,id_prof) ;
             pst.setString(3,Nom) ;
             pst.setString(4,id_matiere);
           
            pst.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            System.out.println("erreur d'update matiere"+ex);
        }
        
    }
       public void deleteMatiere (String id_matiere){
            try{
                System.out.println("deleting");
            PreparedStatement pst = MaConnexion.prepareStatement("delete from matiere where id_matiere=?");
            pst.setString(1,id_matiere);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("Erreur dans la requete delete matiere");
        }
       }
       public ResultSet PrintM(){
                
        ResultSet Rs = null;
        
       
        try{
              PreparedStatement St=MaConnexion.prepareStatement("Select matiere.id_matiere , matiere.nom,module.id_module,personne.nom,personne.prenom from matiere join module on matiere.id_module=module.id_module join personne on matiere.id_personne=personne.id_personne where personne.type='prof'");
            
              Rs=St.executeQuery();
            
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select matiere."+ex.getMessage());
        }
        
        return Rs;
       }
       
       public ResultSet printIDModule(){
            ResultSet Rs =null;
           
             try{
            String Req = "select id_module  from module ";
            Statement St = MaConnexion.createStatement();
            Rs = St.executeQuery(Req);
             }
             
            catch (SQLException ex)
              {
                  System.out.println(ex+"aaaaaaaaaaa");}
             return Rs;
         
       }
       public ResultSet printIDMatiere(){
            ResultSet Rs =null;
           
             try{
            String Req = "select id_matiere from matiere ";
            Statement St = MaConnexion.createStatement();
            Rs = St.executeQuery(Req);
             }
             
            catch (SQLException ex)
              {
                  System.out.println(ex+"aaaaaaaaaaa");}
             return Rs;
         
       }
       public ResultSet PrintNomProf(){
           ResultSet Rs=null;
            try{
            String Req = "select nom   from personne where type='prof'; ";
            Statement St = MaConnexion.createStatement();
            Rs = St.executeQuery(Req);
             }
             
            catch (SQLException ex)
              {
                  System.out.println(ex+"aaaaaaaaaaa");}
             return Rs;
       }
         public ResultSet PrintPreomProf(){
           ResultSet Rs=null;
            try{
            String Req = "select prenom   from personne where type='prof'; ";
            Statement St = MaConnexion.createStatement();
            Rs = St.executeQuery(Req);
             }
             
            catch (SQLException ex)
              {
                  System.out.println(ex+"aaaaaaaaaaa");}
             return Rs;
       }
       public int getIdProf(String name){
            int id=55;
              
              ResultSet Rs =null;
              try
              {
                  PreparedStatement pst;
                  pst = MaConnexion.prepareStatement("select id_personne from personne where nom=?");
                   
                  pst.setString(1,name) ;
                 // System.out.println("iduser !!"+L.getIdUser());
                  
                  
                  Rs= pst.executeQuery();
                  while(Rs.next())
                      id= Rs.getInt(1);
                     // System.out.println("idcine"+Rs.getInt(1));
              
              }
              catch (SQLException ex)
              {
                  System.out.println(ex);}
              return id;
       }
        public ResultSet PrintA(){
                
        ResultSet Rs = null;
        
       
        try{
              PreparedStatement St=MaConnexion.prepareStatement("Select student.cne, personne.nom, personne.prenom,s_absente.justificatif  from personne join module on matiere.id_module=module.id_module join personne on matiere.id_personne=personne.id_personne where personne.type='prof'");
            
              Rs=St.executeQuery();
            
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select matiere."+ex.getMessage());
        }
        
        return Rs;
       }
       
}

    
    
    
    

