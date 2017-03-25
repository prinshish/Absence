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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.proteanit.sql.DbUtils;

/**
 * FXML Controller class
 *
 * @author Zineb Regragui
 */
public class GestionStructureController implements Initializable {
    DaoProduit dao = new DaoProduit();
    
    private Stage local_stage ;
    @FXML
    private Hyperlink linkAccueil;
    private File file;
    private FileInputStream fileInput ;
    
    @FXML
    private TextField txtFiliere;
    @FXML
    private TextArea FDesc;
    @FXML
    private Button DeleteF;
    @FXML
    private Button btnCancel;
    @FXML
    private Button BtnOk;
    @FXML
    private TableView<Filiere> TableF;
    private ObservableList <Filiere> Data_F = FXCollections.observableArrayList();
    private ObservableList <Filiere> Data_Box = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<Filiere, String> ColNomF;
    @FXML
    private TableColumn<Filiere, String> ColDescr;
    
    
    
    @FXML
    private Button btnDelete;
    @FXML
    private Button okbutton;
    @FXML
    private Button btnCancelC;
    @FXML
    private ComboBox<String> comboFiliere;
    @FXML
    private TextField NomC;
    @FXML
    private TableColumn<Classe, String> colNameC;
    @FXML
    private TableColumn<Classe, String> colFilClass;
    @FXML
    private TableColumn<Classe, Integer> colEff;
    private String filedir;
    @FXML
    private TableView<Classe> TableC;
    private ObservableList <Classe> Data_C = FXCollections.observableArrayList();
    private ObservableList <Classe> Data_Box_C = FXCollections.observableArrayList();
    private ObservableList <String> comboString = FXCollections.observableArrayList();
    @FXML
    private Button uploadClass;
    @FXML
    private Button okUpload;
    @FXML
    private Button cancelUpload;

    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ColNomF.setCellValueFactory(new PropertyValueFactory<Filiere,String> ("id_filiere"));
       ColDescr.setCellValueFactory(new PropertyValueFactory<Filiere,String> ("nom"));
       colNameC.setCellValueFactory(new PropertyValueFactory<Classe,String> ("id_classe"));
       colFilClass.setCellValueFactory(new PropertyValueFactory<Classe,String> ("id_filiere"));
       colEff.setCellValueFactory(new PropertyValueFactory<Classe,Integer> ("effectif"));
       comboFiliere.setPromptText("Choisissez la filière"); 
       actualiser_F();
       actualiser_C();
       uploadClass.setVisible(false);
       show_in_textfiled(); 
       show_in_textfiledC();
       cancelUpload.setVisible(false);
        okUpload.setVisible(false);
       fillCombo();
    }    
    
    //this is the code to get les info dial les classes w t3mrihoum f tableview
    private void actualiser_C(){
        TableC.getItems().clear();
        ResultSet Rs = dao.printClasse();
         try {
         while ( Rs.next())
           {
           Data_C.add(new Classe( Rs.getString(1),Rs.getString(2),Rs.getInt(3))); //I didn't include id_filiere
           TableC.setItems(null);
           TableC.setItems(Data_C);   
           }
         btnCancelC();
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };
    
    private void clear_F(){
        txtFiliere.clear();
        FDesc.clear();
    }
    
    private void clear_C(){
        NomC.clear();
        comboFiliere.valueProperty().set(null);
    }
    
    private void actualiser_F(){
        TableF.getItems().clear();
        ResultSet Rs = dao.printFiliere();
         try {
         while ( Rs.next())
           {
           Data_F.add(new Filiere(Rs.getString(1),Rs.getString(2)));
           TableF.setItems(null);
           TableF.setItems(Data_F);   
           }
         btnCancel();
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
    };
    
    @FXML
    private void onDeleteClass(ActionEvent event) {
        dao.DeleteClasse(NomC.getText());
         actualiser_C();
    }

    @FXML
    private void onOkclasse(ActionEvent event) {
       
       if(okbutton.getText()=="Edit"){
           dao.editClasse(NomC.getText(), comboFiliere.getValue());
       }
       else {  
           dao.addClasse(NomC.getText(), comboFiliere.getValue()); 
       }
        actualiser_C();
    }

    @FXML
    private void onDeleteFil(ActionEvent event) {
         dao.DeleteFil(txtFiliere.getText());
         actualiser_F();
    }

    @FXML
    private void btnOkFil(ActionEvent event) {
        if(BtnOk.getText()=="Edit"){
        dao.editFiliere(txtFiliere.getText(), FDesc.getText());
        }
        else {
        dao.addFiliere(txtFiliere.getText(), FDesc.getText());
        }
        actualiser_F();
    }
    
    @FXML
    private void mouseclickFil(MouseEvent event) {
        DeleteF.setVisible(true);
        btnCancel.setVisible(true);
        BtnOk.setText("Edit");
        txtFiliere.setDisable(true);
    }
    
    @FXML
    private void mouseclickClass(MouseEvent event) {
        btnDelete.setVisible(true);
        btnCancelC.setVisible(true);
        okbutton.setText("Edit");
        NomC.setDisable(true);
        uploadClass.setVisible(true);
    }
    
    @FXML
    private void btnCancelC(){
        btnDelete.setVisible(false);
        btnCancelC.setVisible(false);
        okbutton.setText("Ok");
        NomC.setDisable(false);
        uploadClass.setVisible(false);
        cancelUpload.setVisible(false);
        okUpload.setVisible(false);
        clear_C();   
    }
    
    @FXML
    private void btnCancel(){
        DeleteF.setVisible(false);
        btnCancel.setVisible(false);
        BtnOk.setText("Ok");
        txtFiliere.setDisable(false);
        fillCombo();
        clear_F();   
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
       comboFiliere.getItems().clear();
        ResultSet Rs = dao.idFiliere();
         try {
         while ( Rs.next())
           {
           Data_Box.add(new Filiere(Rs.getString("id_filiere")));//(new User(rs.getString("username")).getUserName()
           comboString.add(Rs.getString("id_filiere"));
           comboFiliere.setItems(null);
           //comboFiliere.getItems().addAll()
           comboFiliere.setItems(comboString);   
           }
        // clear_F();
         }
      catch (SQLException ex) 
        {  
        System.err.println(ex); }
   }
   
    private boolean checkEmpty ()
    {
       if ( FDesc.getText().isEmpty() || txtFiliere.getText().isEmpty() )
      
       {
         return true ;
       }
       return false ;
    }
    
    private Optional controlleDialogues ( int type , String contenu )
    {
      Optional <ButtonType > action= null;      
        if ( type==1)
        {
            
          Alert alert = new Alert ( Alert.AlertType.ERROR);
          alert.setTitle("erreur");
          alert.setHeaderText(null);
          alert.setContentText(contenu);
         action = alert.showAndWait() ; 
          
        }
        else if (type==2)
        {
         
          Alert alert = new Alert ( Alert.AlertType.CONFIRMATION);
          alert.setTitle(" confirmation" );
          alert.setHeaderText(null);
          alert.setContentText(contenu);
          action = alert.showAndWait() ;
          
        }
        return action ;
    }
    
    private void update_row(MouseEvent  event) {
         
        boolean check;
        check=checkEmpty();
        if (check==false )
        {
            dao.editFiliere(txtFiliere.getText(), FDesc.getText());
//        admin.update(file ,Integer.parseInt(film.getText()) , titre.getText(), duree.getText(), cat.getText(), desc.getText());
//        load_data ();
        }
        else 
        {
         controlleDialogues( 1 , "auccune ligne est selectionné , veuillez selectionner une ligne  ");
        }
        clear_F();
    }
    
    private void  show_in_textfiled()
    { 
       
       TableF.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Filiere> () {
       @Override
    
    public void changed(ObservableValue<? extends Filiere> observableValue, Filiere oldValue, Filiere newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(TableF.getSelectionModel().getSelectedItem() != null) 
        {
       
        txtFiliere.setText(newValue.getId_filiere());
        FDesc.setText(newValue.getNom());
       
       // film.setText(String.valueOf(newValue.getId_film()));
       
        }
    }

     });
         
    }
    
    private void  show_in_textfiledC()
    { 
       
       TableC.getSelectionModel().selectedItemProperty().addListener(new ChangeListener <Classe> () {
       @Override
    
    public void changed(ObservableValue<? extends Classe> observableValue, Classe oldValue, Classe newValue) {
        //Check whether item is selected and set value of selected item to Label
        if(TableC.getSelectionModel().getSelectedItem() != null) 
        {
       
        NomC.setText(newValue.getId_classe());
        comboFiliere.setValue(newValue.getId_filiere());
        }
    }

     });
         
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
            cancelUpload.setVisible(true);
            okUpload.setVisible(true);
    }

    @FXML
    private void onBtnOkUpload(ActionEvent event) throws IOException {
        String fil = comboFiliere.getValue();
        String classe= NomC.getText();
        try { 
        BufferedReader bReader = new BufferedReader(new FileReader(filedir));
        String line = ""; 
        while ((line = bReader.readLine()) != null) {
            if (line != null)
            {
               int i = 0;
            String id ="x",nom="x", prenom="x", cin="x", cne="x";
                String[] array = line.split(";"); 
                for(String result:array)
                {
                    if(i==0) id=result;
                    if (i==1) nom=result;
                    if (i==2) prenom=result;
                    if (i==3) cin = result;
                    if (i==4) {
                        cne = result;
                        dao.addStudent(id, nom, prenom, cin, id, cin, "student", fil, classe, cne);
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
    private void onBtnCancelUpload(ActionEvent event) {
        cancelUpload.setVisible(false);
        okUpload.setVisible(false);
    }
    
}
    
    

