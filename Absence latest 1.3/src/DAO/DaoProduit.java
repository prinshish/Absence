/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;

/**
 *
 * @author alaou
 */
public class DaoProduit {
    private DAO dao;
    private Connection MaConnexion;
    public DaoProduit(){
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
    
    public void addFiliere(String id, String nom){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into filiere(id_filiere,nom) values(?,?)");
            pst.setString(1,id);
            pst.setString(2,nom);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout filière "+ex.getMessage());
        }
    }
    
    public void addPersonne(String id, String nom, String prenom, String CIN, String login, String mdp, String type){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into personne(id_personne,nom,prenom,CIN,login,mdp,type) values(?,?,?,?,?,?,?)");
            pst.setString(1,id);
            pst.setString(2,nom);
            pst.setString(3,prenom);
            pst.setString(4,CIN);
            pst.setString(5,login);
            pst.setString(6,mdp);
            pst.setString(7,type);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout prof "+ex.getMessage());
        }
    }
    
    public void addStudent(String id, String nom, String prenom, String CIN, String login, String mdp, String type, String Filiere, String classe, String CNE){
        Statement St;
        try{
            this.addPersonne(id, nom, prenom, CIN, login, mdp, type);
            PreparedStatement pst = MaConnexion.prepareStatement("insert into student (id_personne,id_filiere,id_classe,CNE) values(?,?,?,?)");
            pst.setString(1,id);
            pst.setString(2,Filiere);
            pst.setString(3,classe);
            pst.setString(4,CNE);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout student "+ex.getMessage());
        }
    }
    
    public void addUser(String login, String mdp, String id){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into Personne(login,mdp) values(?,?) where id_personne=?");
            pst.setString(1,login);
            pst.setString(2,mdp);
            pst.setString(3,id);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout filière "+ex.getMessage());
        }
    }
    public void addModule(String id, String classe , String nom){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into module(id_module,id_classe,nom) values(?,?,?)");
            pst.setString(1,id);
            pst.setString(2,classe);
            pst.setString(3,nom);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout module "+ex.getMessage());
        }
    }
    
    public void editFiliere(String idFil,String nom){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("update filiere set nom=? where id_filiere=?");
            pst.setString(1,nom);
            pst.setString(2,idFil);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'update filière"+ex.getMessage());
        }
    }
    
    public void editModule(String idMod, String idClasse, String nom){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("update module set nom=? , id_classe=? where id_module=?");
            pst.setString(1,nom);
            pst.setString(2,idClasse);
            pst.setString(3,idMod);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("erreur d'update module: "+ex.getMessage());
        }
    }
    
    public void DeleteFil(String idFil){
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("delete from filiere where id_filiere=?");
            pst.setString(1,idFil);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("Erreur dans la requete delete filiere");
        }
    }
    public void DeleteMod(String idMod){
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("delete from module where id_module=?");
            pst.setString(1,idMod);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.err.println("Erreur dans la requete delete module");
        }
    }
    public ResultSet printFiliere(){
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            Rs=St.executeQuery("Select * from filiere");
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select filiere."+ex.getMessage());
        }
        return Rs;
    }
    
    public ResultSet printModule(){
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            Rs=St.executeQuery("Select * from module");
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select module."+ex.getMessage());
        }
        return Rs;
    }
    
    public void addClasse(String id_classe, String id_filiere){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("insert into classe(id_filiere,id_classe,effectif) values(?,?,?)");
            pst.setString(1,id_filiere);
            pst.setString(2,id_classe);
            pst.setInt(3,0);
            pst.executeUpdate();
            System.out.println("Classe ajoutée avec succès\n");
        }
        catch(SQLException ex){
            System.err.println("erreur d'ajout classe"+ex.getMessage());
        }
        
    }
    
    public void editClasse( String idClass, String idFil){
        Statement St;
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("update classe set id_filiere=? where id_classe=?");
            pst.setString(1,idFil);
            pst.setString(2,idClass);
            pst.executeUpdate();
            System.out.println("Classe updated successfully\n");
        }
        catch(SQLException ex){
            System.err.println("erreur d'update classe"+ex.getMessage());
        }
    }
     public ResultSet idFiliere(){
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            Rs=St.executeQuery("Select id_filiere from filiere");
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select id filiere."+ex.getMessage());
        }
        return Rs;
    }
     
    public ResultSet idClasse(){
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            Rs=St.executeQuery("Select id_classe from classe");
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select id classe."+ex.getMessage());
        }
        return Rs;
    } 
     
    public void DeleteClasse(String idClass){
        try{
            PreparedStatement pst = MaConnexion.prepareStatement("delete from classe where id_classe=?");
            pst.setString(1,idClass);
            pst.executeUpdate();
            System.out.println("Classe successfuly deleted\n");
        }
        catch(SQLException ex){
            System.err.println("Erreur dans la requete delete classe");
        }
    }
    
    public ResultSet printClasse(){
        Statement St;
        ResultSet Rs = null;
        try{
            St=MaConnexion.createStatement();
            Rs=St.executeQuery("Select * from classe");
        }
        catch(SQLException ex){
            System.err.println("Pb dans la requete Select classe."+ex.getMessage());
        }
        return Rs;
    }
}
