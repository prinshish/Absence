/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

import DAO.DaoStudent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zineb Regragui
 */
public class StudentListController implements Initializable {

    @FXML
    private Button precedent;
    @FXML
    private TableView<Student> TableS;
    @FXML
    private TableColumn<Student, String> ColNom;
    @FXML
    private TableColumn<Student, String> ColPrenom;
    @FXML
    private TableColumn<Student, String> ColCne;
    @FXML
    private TableColumn<Student, String> ColFiliere;
    @FXML
    private TableColumn<Student, String> ColClasse;
    @FXML
    private ComboBox<String> comboClasse;
    private ObservableList <Student> Data = FXCollections.observableArrayList();
    private ObservableList <Student> Data_Box = FXCollections.observableArrayList(); 
    private ObservableList <String> comboString = FXCollections.observableArrayList();
    DaoStudent dao=new DaoStudent();
    @FXML
    private Button Ok;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ColNom.setCellValueFactory(new PropertyValueFactory<Student,String> ("Nom"));
        ColPrenom.setCellValueFactory(new PropertyValueFactory<Student,String> ("Prenom"));
       ColCne.setCellValueFactory(new PropertyValueFactory<Student,String> ("CNE"));
       ColClasse.setCellValueFactory(new PropertyValueFactory<Student,String> ("Classe"));
       ColFiliere.setCellValueFactory(new PropertyValueFactory<Student,String> ("Filiere"));
        fillCombo();
        actualiser();
    }    

    @FXML
    private void OnPrecedent(ActionEvent event) throws IOException {
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("Structure.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }

   private void fillCombo(){
       comboClasse.getItems().clear();
        ResultSet Rs = dao.PrintClasse();
         try {
         while ( Rs.next())
           {
           Data_Box.add(new Student(Rs.getString(1)));//(new User(rs.getString("username")).getUserName()
           comboString.add(Rs.getString(1));
           comboClasse.setItems(null);
           //comboFiliere.getItems().addAll()
           comboClasse.setItems(comboString);   
           }
        // clear_F();
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
   }
   private void actualiser(){
        TableS.getItems().clear();
        ResultSet Rs;
        if("AP1".equals(comboClasse.getValue())|| null == comboClasse.getValue()) // add a value in db with all option to use here instead of AP1
        Rs = dao.PrintStudent(); //AP1 was just to test !!!!!!!!
        else
          Rs=dao.PrintStudent1(comboClasse.getValue());
         try {
         while ( Rs.next())
           {
           Data.add(new Student( Rs.getString(1),Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5))); //I didn't include id_filiere
           TableS.setItems(null);
           TableS.setItems(Data);   
           }
         
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };

    private void Oncombo(DragEvent event) {
         TableS.getItems().clear();
        ResultSet Rs = dao.PrintStudent1(comboClasse.getValue());
         try {
         while ( Rs.next())
           {
           Data.add(new Student( Rs.getString(1),Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5))); //I didn't include id_filiere
           TableS.setItems(null);
           TableS.setItems(Data);   
           }
         
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };

    @FXML
    private void OnOk(ActionEvent event) {
//           TableS.getItems().clear();
//        ResultSet Rs = dao.PrintStudent1(comboClasse.getValue());
//         try {
//         while ( Rs.next())
//           {
//           Data.add(new Student( Rs.getString(1),Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5))); //I didn't include id_filiere
//           TableS.setItems(null);
//           TableS.setItems(Data);   
//           }
//         
//         }
//      catch (SQLException ex) 
//        {  
//        System.err.println(ex); }
//    };
        actualiser();
    }
    
    
}
