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
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
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
    private TableColumn<Personne, String> colID;
    @FXML
    private TableColumn<Personne, String> colCIN;
    @FXML
    private TableColumn<Personne, String> colNom;
    @FXML
    private TableColumn<Personne, String> colPrenom;
    @FXML
    private TableColumn<Personne, String> colLogin;
    @FXML
    private TableColumn<Personne, String> colMdp;
    @FXML
    private TableColumn<Personne, String> colEmail;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new DaoProduit();
        // TODO
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
    
}
