/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

import DAO.DaoProduit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kukaa
 */
public class AddUsersController implements Initializable {

    @FXML
    private TableColumn<Personne2, Integer> colID;
    @FXML
    private TableColumn<Personne2, String> colCIN;
    @FXML
    private TableColumn<Personne2, String> colNom;
    @FXML
    private TableColumn<Personne2, String> colPrenom;
    @FXML
    private TableColumn<Personne2, String> colLogin;
    @FXML
    private TableColumn<Personne2, String> colMdp;
    @FXML
    private TableColumn<Personne2, String> colEmail;
    @FXML
    private Button btnUploadUsers;
    @FXML
    private TextField txtCIN;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtMdp;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnUpload;
    @FXML
    private Button btnCancelUpload;
    private FileChooser  fileChooser ;
    private File file ;
    private FileInputStream fileInput ; 
    private Stage local_stage ;
    private DaoProduit dao;
    private String filedir;
    @FXML
    private Hyperlink linkAccueil;
    @FXML
    private Hyperlink linkStructures;
    @FXML
    private TableView<Personne2> tableProfs;
    private ObservableList <Personne2> Data = FXCollections.observableArrayList();
    @FXML
    private Hyperlink linkModule;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DaoProduit();
        colCIN.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("CIN"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("email"));
        colID.setCellValueFactory(new PropertyValueFactory<Personne2,Integer> ("id_personne"));
        colLogin.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("login"));
        colMdp.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("mdp"));
        colNom.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Personne2,String> ("prenom"));
        btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        btnCancelUpload.setVisible(false);
        btnUpload.setVisible(false);
        show_in_textfield();
        actualiser();
        // TODO
    }
    
    private void cancelUpload(){
        btnUpload.setVisible(false);
        btnCancelUpload.setVisible(false);
    }
    
    private void cancelEntry(){
         btnDelete.setVisible(false);
        btnCancel.setVisible(false);
        btnOk.setText("Ok");
        txtID.setDisable(false);
        
    }

private void actualiser(){
        tableProfs.getItems().clear();
        ResultSet Rs = dao.printProfs();
         try {
         while ( Rs.next())
           {/////////////////////////ID///////////NOM////////////////PRENOM////////CIN//////////////email///////////login//////////////mdp/////
           Data.add(new Personne2( Rs.getInt(1),Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(7),Rs.getString(8))); 
           tableProfs.setItems(null);
           tableProfs.setItems(Data);   
           }
         cancelEntry();
         cancelUpload();
         
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };   

void clearFields(){
    txtCIN.clear();
         txtEmail.clear();
         txtID.clear();
         txtLogin.clear();
         txtMdp.clear();
         txtNom.clear();
         txtPrenom.clear();
}
    
    void load_data() throws FileNotFoundException
   {
//       fileChooser=new FileChooser ();
//       FileChooser fileChooser = new FileChooser();
//       fileChooser.setTitle("Upload Utilisateurs");
//       fileChooser.getExtensionFilters().addAll(
//       new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.CSV")   
//       );
//       file = fileChooser.showOpenDialog(this.getStage());
//       if ( file!=null)
//       {
//          // my_file = new file("path");
////         image = new Image( file.toURI().toString(),150,150,false ,false); 
////         my_image.setImage(image); 
//         fileInput = new FileInputStream (file);
//       }
       
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            final File selectedDirectory = directoryChooser.showDialog(this.getStage());
            if (selectedDirectory != null) {
                filedir = selectedDirectory.getAbsolutePath();
            }
            btnCancelUpload.setVisible(true);
    }
    
    
    
    public void setStage (Stage my_stage )
   {
       local_stage=my_stage;
   }
   public Stage getStage ()
   {
       return this.local_stage;
   }

    @FXML
    private void onBtnUploadUsers(ActionEvent event) throws FileNotFoundException {
//        final DirectoryChooser directoryChooser = new DirectoryChooser();
//            final File selectedDirectory = directoryChooser.showDialog(this.getStage());
//            if (selectedDirectory != null) {
//                filedir = selectedDirectory.getAbsolutePath();
//            }
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Upload Utilisateurs");
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.CSV"));
       file = fileChooser.showOpenDialog(this.getStage());
       
       if ( file!=null)
       {
          // my_file = new file("path");
//         image = new Image( file.toURI().toString(),150,150,false ,false); 
//         my_image.setImage(image); 
           filedir = file.getAbsolutePath();
           System.out.println("THIS IS THE FILE DIR"+filedir);
         fileInput = new FileInputStream (file);
       }
            btnCancelUpload.setVisible(true);
            btnUpload.setVisible(true);
    }

    @FXML
    private void onBtnOkUpload(ActionEvent event) throws FileNotFoundException, IOException {
        try { 
        BufferedReader bReader = new BufferedReader(new FileReader(filedir));
        String line = ""; 
        while ((line = bReader.readLine()) != null) {
            if (line != null)
            {
               int i = 0;
            String id ="x",nom="x", prenom="x", cin="x"; 
                String[] array = line.split(";"); 
                for(String result:array)
                {
                    if(i==0) id=result;
                    if (i==1) nom=result;
                    if (i==2) prenom=result;
                    if (i==3) {
                        cin = result;
                        dao.addPersonne(id, nom, prenom, cin,id,cin,"prof");
                        //System.out.println("id: "+id+" nom: "+nom+" prenom: "+prenom+" cin: "+cin);
                        i=-1;
                    }
                    i++;
                }
            }
            if (bReader == null)
            {
                bReader.close();
            }
        }
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    }
        actualiser();
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

    @FXML
    private void onStructures(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Structure.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }

    private void  show_in_textfield()
    { 
       
       tableProfs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Personne2> () {
       @Override
    
    public void changed(ObservableValue<? extends Personne2> observableValue, Personne2 oldValue, Personne2 newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(tableProfs.getSelectionModel().getSelectedItem() != null) 
        {
       
        txtCIN.setText(newValue.getCIN());
        txtEmail.setText(newValue.getEmail());
        txtID.setText(Integer.toString(newValue.getId_personne()));
        txtLogin.setText(newValue.getLogin());
        txtMdp.setText(newValue.getMdp());
        txtNom.setText(newValue.getNom());
        txtPrenom.setText(newValue.getPrenom());
       
       // film.setText(String.valueOf(newValue.getId_film()));
       
        }
    }

     });
         
    }
    
    @FXML
    private void onBtnCancelUpload(ActionEvent event) {
        cancelUpload();
    }

    @FXML
    private void onClickTable(MouseEvent event) {
         btnDelete.setVisible(true);
        btnCancel.setVisible(true);
        btnOk.setText("Edit");
        txtID.setDisable(true);
    }

    @FXML
    private void onCancel(ActionEvent event) {
        clearFields();
        txtID.setDisable(false);
        cancelEntry();
    }

    @FXML
    private void onDelete(ActionEvent event) {
        dao.deleteProf(txtID.getText());
         actualiser();
         clearFields();
    }

    @FXML
    private void onOk(ActionEvent event) {
        if(btnOk.getText()=="Edit"){
        dao.editPersonne( txtID.getText(),txtNom.getText(), txtPrenom.getText(), txtCIN.getText(), txtLogin.getText(), txtMdp.getText(), "prof");
        }
        else {
        dao.addPersonne( txtID.getText(),txtNom.getText(), txtPrenom.getText(), txtCIN.getText(), txtLogin.getText(), txtMdp.getText(), "prof");
        }
        actualiser();
        clearFields();
    }

    @FXML
    private void onLinkModule(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("gestionModule_1.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();
    }
    
    
    
}
