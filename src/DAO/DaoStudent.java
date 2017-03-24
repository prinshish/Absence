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
public class DaoStudent {
       private DAO dao;
    private Connection MaConnexion;
    public DaoStudent(){
        dao=new DAO();
        dao.setPilote("com.mysql.jdbc.Driver");
        dao.setURL("jdbc:mysql://localhost:3306/absence");
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
       public ResultSet PrintStudent(){
                
        ResultSet Rs = null;
        
       
        try{
              PreparedStatement St=MaConnexion.prepareStatement("Select personne.nom , personne.prenom,student.CNE , student.id_filiere,student.id_classe from student join personne on student.id_personne=personne.id_personne  ");
             // St.setString(1, Classe);
              Rs=St.executeQuery();
            
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select student."+ex.getMessage());
        }
        
        return Rs;
       }
        public ResultSet PrintStudent1(String Classe){
                
        ResultSet Rs = null;
        
       
        try{
              PreparedStatement St=MaConnexion.prepareStatement("Select personne.nom , personne.prenom,student.CNE , student.id_filiere,student.id_classe from student join personne on student.id_personne=personne.id_personne where student.id_classe=? ");
              St.setString(1, Classe);
              Rs=St.executeQuery();
            
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select student."+ex.getMessage());
        }
        
        return Rs;
       }
        public ResultSet PrintClasse(){
                
        ResultSet Rs = null;
        
       
        try{
              PreparedStatement St=MaConnexion.prepareStatement("Select id_classe from classe");
             // St.setString(1, Classe);
              Rs=St.executeQuery();
            
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select Classe."+ex.getMessage());
        }
        
        return Rs;
       }
    
}
