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
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GestionAbsenceController implements Initializable {

    DaoProduit dao = new DaoProduit();
    DaoMatiere dm = new DaoMatiere();
    private ObservableList <String> comboString = FXCollections.observableArrayList();
    private ObservableList <String> comboString3 = FXCollections.observableArrayList();
    private ObservableList <String> comboString2 = FXCollections.observableArrayList();
    private ObservableList <Classe> Data_Box = FXCollections.observableArrayList();
    private DaoMatiere daoM=new DaoMatiere();
    @FXML
    private Button valider;

    @FXML
    private TableColumn<?, ?> checkBoxTableColumn;
    
    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> avp;

    @FXML
    private TableColumn<?, ?> app;

    @FXML
    private TableColumn<?, ?> just;
    @FXML
    private ComboBox<String> matiereCombo;

    @FXML
    private ComboBox<String> classeCombo;

    @FXML
    private Hyperlink linkAccueil;

    @FXML
    private ComboBox<?> periodCombo;

//    private void fillComboM(){
//       matiereCombo.getItems().clear();
//        ResultSet Rs = daoM.PrintNomProf();
//         try {
//         while ( Rs.next())
//           {
//               
//            
//           Data_Box2.add(new Prof(Rs.getString(1)));//(new User(rs.getString("username")).getUserName()
//           comboString3.add(Rs.getString(1));
//           matiereCombo.setItems(null);
//           matiereCombo.setItems(comboString3);   
//           }
//         }
//      catch (SQLException ex) 
//        {  
//        System.err.println(ex); }
//   }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

