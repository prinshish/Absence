/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

import DAO.DaoProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.proteanit.sql.DbUtils;

/**
 * FXML Controller class
 *
 * @author Zineb Regragui
 */
public class GestionModuleController implements Initializable {
    DaoProduit dao = new DaoProduit();
    private Stage local_stage ;
    @FXML
    private Hyperlink linkAccueil;
    
    private ObservableList <Module> Data_Mod = FXCollections.observableArrayList();
    private ObservableList <Classe> Data_Box = FXCollections.observableArrayList(); 
    
    @FXML
    private Button btnOkMod;
    @FXML
    private Button btnDeleteMod;
    @FXML
    private TableView<Module> TableMod;
    @FXML
    private TableColumn<Module, String> colNomMod;
    @FXML
    private TableColumn<Module, String> colClasseMod;
    @FXML
    private TableColumn<Module, String> colIdMod;
    @FXML
    private TextArea txtNomMod;
    @FXML
    private Button btnCancelMod;
    @FXML
    private TextField txtModule;
    @FXML
    private ComboBox<String> comboClasse;
    private ObservableList <String> comboString = FXCollections.observableArrayList();
    
    
//    
//    @FXML
//    private Button btnDelete;
//    @FXML
//    private Button okbutton;
//    @FXML
//    private Button btnCancelC;
//    @FXML
//    private ComboBox<String> comboFiliere;
//    @FXML
//    private TextField NomC;
//    @FXML
//    private TableColumn<Classe, String> colNameC;
//    @FXML
//    private TableColumn<Classe, String> colFilClass;
//    @FXML
//    private TableColumn<Classe, Integer> colEff;
//    @FXML
//    private TableView<Classe> TableC;
//    private ObservableList <Classe> Data_C = FXCollections.observableArrayList();
//    private ObservableList <Classe> Data_Box_C = FXCollections.observableArrayList();
//    private ObservableList <String> comboString = FXCollections.observableArrayList();
    @FXML
    private Button btnDelete;
    @FXML
    private Button okbutton;
    @FXML
    private ComboBox<?> comboFiliere;
    @FXML
    private TableView<?> TableC;
    @FXML
    private TableColumn<?, ?> colNameC;
    @FXML
    private TableColumn<?, ?> colFilClass;
    @FXML
    private TableColumn<?, ?> colEff;
    @FXML
    private TextField NomC;
    @FXML
    private Button btnCancelC;

    
    
    
    
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       colIdMod.setCellValueFactory(new PropertyValueFactory<Module,String> ("id_module"));
       colNomMod.setCellValueFactory(new PropertyValueFactory<Module,String> ("nom"));
       colClasseMod.setCellValueFactory(new PropertyValueFactory<Module,String> ("id_classe"));
//       colNameC.setCellValueFactory(new PropertyValueFactory<Classe,String> ("id_classe"));
//       colFilClass.setCellValueFactory(new PropertyValueFactory<Classe,String> ("id_filiere"));
//       colEff.setCellValueFactory(new PropertyValueFactory<Classe,Integer> ("effectif"));
//       comboFiliere.setPromptText("Choisissez la filière"); 
       actualiser_Mod();
//       actualiser_C();
       show_in_textfiled_Mod(); 
//       show_in_textfiledC();
       fillCombo();
    }    
    
    //this is the code to get les info dial les classes w t3mrihoum f tableview
//    private void actualiser_C(){
//        TableC.getItems().clear();
//        ResultSet Rs = dao.printClasse();
//         try {
//         while ( Rs.next())
//           {
//           Data_C.add(new Classe( Rs.getString(1),Rs.getString(2),Rs.getInt(3))); //I didn't include id_filiere
//           TabwhileleC.setItems(null);
//           TableC.setItems(Data_C);   
//           }
//         btnCancelC();
//         }
//      catch (SQLException ex) 
//        {  
//        System.err.println(ex); }
//    };
    
    private void clear_Mod(){
        txtModule.clear();
        txtNomMod.clear();
        comboClasse.valueProperty().set(null);
    }
    
//    private void clear_C(){
//        NomC.clear();
//        comboFiliere.valueProperty().set(null);
//    }
    
    private void actualiser_Mod(){
        TableMod.getItems().clear();
        ResultSet Rs = dao.printModule();
         try {
         while ( Rs.next())
           {
           Data_Mod.add(new Module(Rs.getString(1),Rs.getString(3),Rs.getString(2)));
           TableMod.setItems(null);
           TableMod.setItems(Data_Mod);   
           }
         onBtnCancelMod();
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };
    
//    @FXML
//    private void onDeleteClass(ActionEvent event) {
//        dao.DeleteClasse(NomC.getText());
//         actualiser_C();
//    }

//    @FXML
//    private void onOkclasse(ActionEvent event) {
//       
//       if(okbutton.getText()=="Edit"){
//           dao.editClasse(NomC.getText(), comboFiliere.getValue());
//       }
//       else {  
//           dao.addClasse(NomC.getText(), comboFiliere.getValue()); 
//       }
//        actualiser_C();
//    }

    @FXML
    private void onDeleteMod(ActionEvent event) {
         dao.DeleteMod(txtModule.getText());
         actualiser_Mod();
    }

    @FXML
    private void onOkMod(ActionEvent event) {
        if(btnOkMod.getText()=="Edit"){
        dao.editModule(txtModule.getText(),comboClasse.getValue(), txtNomMod.getText());
        }
        else {
        dao.addModule(txtModule.getText(),comboClasse.getValue(), txtNomMod.getText());
        }
        actualiser_Mod();
    }
    
    @FXML
    private void mouseclickMod(MouseEvent event) {
        btnDeleteMod.setVisible(true);
        btnCancelMod.setVisible(true);
        btnOkMod.setText("Edit");
        txtModule.setDisable(true);
    }
//    
//    @FXML
//    private void mouseclickClass(MouseEvent event) {
//        btnDelete.setVisible(true);
//        btnCancelC.setVisible(true);
//        okbutton.setText("Edit");
//        NomC.setDisable(true);
//    }
    
//    @FXML
//    private void btnCancelC(){
//        btnDelete.setVisible(false);
//        btnCancelC.setVisible(false);
//        okbutton.setText("Ok");
//        NomC.setDisable(false);
//        clear_C();   
//    }
    
    @FXML
    private void onBtnCancelMod(){
        btnDeleteMod.setVisible(false);
        btnCancelMod.setVisible(false);
        btnOkMod.setText("Ok");
        txtModule.setDisable(false);
        fillCombo();
        clear_Mod();   
    }
    

    @FXML
    private void onAccueil(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }

    
    private void fillCombo(){
       comboClasse.getItems().clear();
        ResultSet Rs = dao.idClasse();
         try {
         while ( Rs.next())
           {
               
            
           Data_Box.add(new Classe(Rs.getString("id_classe")));//(new User(rs.getString("username")).getUserName()
           comboString.add(Rs.getString("id_classe"));
           comboClasse.setItems(null);
           comboClasse.setItems(comboString);   
           }
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
   }
   
//    private boolean checkEmpty ()
//    {
//       if ( FDesc.getText().isEmpty() || txtFiliere.getText().isEmpty() )
//      
//       {
//         return true ;
//       }
//       return false ;
//    }
    
//    private Optional controlleDialogues ( int type , String contenu )
//    {
//      Optional <ButtonType > action= null;      
//        if ( type==1)
//        {
//            
//          Alert alert = new Alert ( Alert.AlertType.ERROR);
//          alert.setTitle("erreur");
//          alert.setHeaderText(null);
//          alert.setContentText(contenu);
//         action = alert.showAndWait() ; 
//          
//        }
//        else if (type==2)
//        {
//         
//          Alert alert = new Alert ( Alert.AlertType.CONFIRMATION);
//          alert.setTitle(" confirmation" );
//          alert.setHeaderText(null);
//          alert.setContentText(contenu);
//          action = alert.showAndWait() ;
//          
//        }
//        return action ;
//    }
//    
//    private void update_row(MouseEvent  event) {
//         
//        boolean check;
//        check=checkEmpty();
//        if (check==false )
//        {
//            dao.editFiliere(txtFiliere.getText(), FDesc.getText());
////        admin.update(file ,Integer.parseInt(film.getText()) , titre.getText(), duree.getText(), cat.getText(), desc.getText());
////        load_data ();
//        }
//        else 
//        {
//         controlleDialogues( 1 , "auccune ligne est selectionné , veuillez selectionner une ligne  ");
//        }
//        clear_F();
//    }
    
    private void  show_in_textfiled_Mod()
    { 
       
       TableMod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Module> () {
       @Override
    
    public void changed(ObservableValue<? extends Module> observableValue, Module oldValue, Module newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(TableMod.getSelectionModel().getSelectedItem() != null) 
        {
       
        txtModule.setText(newValue.getId_module());
        txtNomMod.setText(newValue.getNom());
        comboClasse.setValue(newValue.getId_classe());
        }
    }

     });
         
    }
    
//    private void  show_in_textfiledC()
//    { 
//       
//       TableC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Classe> () {
//       @Override
//    
//    public void changed(ObservableValue<? extends Classe> observableValue, Classe oldValue, Classe newValue) {
//        //Check whether item is selected and set value of selected item to Label
//        if(TableC.getSelectionModel().getSelectedItem() != null) 
//        {
//       
//        NomC.setText(newValue.getId_classe());
//        comboFiliere.setValue(newValue.getId_filiere());
//        }
//    }
//
//     });
//         
//    }
    
  public void setStage (Stage my_stage )
   {
       local_stage=my_stage;
   }
  
   public Stage getStage ()
   {
       return this.local_stage;
    }

    @FXML
    private void onDeleteClass(ActionEvent event) {
    }

    @FXML
    private void onOkclasse(ActionEvent event) {
    }

    @FXML
    private void mouseclickClass(MouseEvent event) {
    }

    @FXML
    private void btnCancelC(ActionEvent event) {
    }


}
    
    

