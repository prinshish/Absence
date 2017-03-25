/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

import DAO.DaoMatiere;
import DAO.DaoProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GestionAbsenceController implements Initializable {

    DaoProduit dao = new DaoProduit();
   // DaoMatiere dm =new DaoMatiere();
    private ObservableList <String> comboString = FXCollections.observableArrayList();
    private ObservableList <String> comboString3 = FXCollections.observableArrayList();
    private ObservableList <String> comboString2 = FXCollections.observableArrayList();
    private ObservableList <Classe> Data_Box = FXCollections.observableArrayList();
    private ObservableList <absence1> Data = FXCollections.observableArrayList();
    private ObservableList <Matiere> Data_Box1 = FXCollections.observableArrayList();

    private DaoMatiere daoM=new DaoMatiere();
    @FXML
    private Button valider;

    
    @FXML
    private TableView<absence1> table;


    @FXML
    private TableColumn<absence1, String> just;
    @FXML
    private ComboBox<String> matiereCombo;

    @FXML
    private ComboBox<String> classeCombo;

    @FXML
    private Hyperlink linkAccueil;

    @FXML
    private ComboBox<String> periodCombo;
    private TableColumn<absence1, String> abs;
    @FXML
    private TableColumn<absence1, String> CNE;
    private TableColumn<absence1, String> colNom;
    private TableColumn<absence1, String> ColPrenom;
    @FXML
    private TableColumn<?, ?> avant;
    @FXML
    private TableColumn<?, ?> apres;
    @FXML
    private Button done;
    @FXML
    private TextField justT;
    @FXML
    private TextField cneT;
    

    private void fillComboM(){
       matiereCombo.getItems().clear();
        ResultSet Rs = daoM.printIDMatiere();
         try {
         while ( Rs.next())
           {
               
            
           Data_Box1.add(new Matiere(Rs.getString(1)));//(new User(rs.getString("username")).getUserName()
           comboString2.add(Rs.getString(1));
           
           matiereCombo.setItems(null);
           matiereCombo.setItems(comboString2);   
           }
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
   }
    private void fillComboClass(){
       classeCombo.getItems().clear();
        ResultSet Rs = dao.idClasse();
         try {
         while ( Rs.next())
           {
               
            
           Data_Box.add(new Classe(Rs.getString("id_classe")));//(new User(rs.getString("username")).getUserName()
           comboString.add(Rs.getString("id_classe"));
           classeCombo.setItems(null);
           classeCombo.setItems(comboString);   
           }
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
   }
     
    @FXML
    void onAccueil(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    public void actualiser(){
         table.getItems().clear();
        ResultSet Rs;
      
        Rs = daoM.PrintA(); //AP1 was just to test !!!!!!!!
        
         
         try {
         while ( Rs.next())
           {
           Data.add(new absence1( Rs.getString(1),Rs.getString(2),Rs.getString(3),Rs.getString(4))); //I didn't include id_filiere
           table.setItems(null);
           table.setItems(Data);   
           }
            
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         colNom.setCellValueFactory(new PropertyValueFactory<absence1,String> ("Nom"));
        ColPrenom.setCellValueFactory(new PropertyValueFactory<absence1,String> ("Prenom"));
       CNE.setCellValueFactory(new PropertyValueFactory<absence1,String> ("CNE"));
       just.setCellValueFactory(new PropertyValueFactory<absence1,String> ("Justificatif"));
      abs.setCellValueFactory(new PropertyValueFactory<absence1,String> ("absence"));
        // TODO
        fillComboClass();
        fillComboM();
        actualiser();
    }    
    
    private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
   // other columns here

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public java.lang.Boolean getChecked() {
        return this.checkedProperty().get();
    }

    public void setChecked(final java.lang.Boolean checked) {
        this.checkedProperty().set(checked);
    }
}

