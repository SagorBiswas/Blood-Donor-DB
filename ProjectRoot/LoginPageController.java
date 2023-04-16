/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginPageController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextField UserText;
    @FXML private PasswordField PassText;
    @FXML private Button btn;
    

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try{
            String ServAdd="127.0.0.1";
            int port=4545;
            String s = "login"+"$"+UserText.getText();
            NetworkUtil nc = new NetworkUtil(ServAdd,port);
            nc.write(s);
            System.out.println("Request Sent");    //(name,BG,age,weight,hall,mobile,date)
            String s2 = (String) nc.read();
            s2 = s2.trim();
            System.out.println(s2);
        if(s2.equals(PassText.getText())){
            Node node = (Node) event.getSource();

            Stage stage=(Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("TextContent/LoginSuccess2.fxml"));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            label.setText("Wrong UserName or Password");
        }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error happened");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
