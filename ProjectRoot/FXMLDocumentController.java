/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
//import java.util.Hashtable;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private TextField Usertext;
    @FXML private PasswordField passtext;
    private Hashtable<String,String>lock = new Hashtable<String,String>();
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try{
        //if(Usertext.getText().equals("sagor") && passtext.getText().equals("1234")) {
        //if(lock.get(Usertext.getText()).equals(passtext.getText())){
            Node node = (Node) event.getSource();

            Stage stage=(Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("TextContent/LoginSuccess2.fxml"));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        //}
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error happened");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    lock.put("AhsanUllah", "1234");
    lock.put("Titumir", "1234");
    lock.put("Nazrul", "1234");
    lock.put("Sher-e-bangla", "1234");
    lock.put("Shohorawardi", "1234");
    lock.put("Rashid", "1234");
    }    
    public static void main(String[] args) {
        System.out.println("all right");
    }
    
}
