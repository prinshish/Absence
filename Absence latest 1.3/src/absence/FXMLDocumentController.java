/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package absence;

import DAO.DaoProduit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Kukaa
 */
public class FXMLDocumentController implements Initializable {
    
    DaoProduit dao= new DaoProduit();
    
    @FXML
    private Label label;
    @FXML
    private Button btnCnx;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPwd;
    
    Scene home, login;
    Stage thestage;
    @FXML
    private Label invalid_label;
    
    @FXML
    private void Connexion(ActionEvent event) throws IOException, SQLException {
          Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           if (dao.Connex(txtLogin.getText(),txtPwd.getText()))
            {
                app_stage.hide(); //optional
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            }
            else
            {
                txtLogin.clear();
                txtPwd.clear();
                invalid_label.setText("Sorry, invalid credentials"); 
            }
    }
       
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


}
