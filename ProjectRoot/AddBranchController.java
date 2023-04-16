/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddBranchController implements Initializable {
    
    @FXML private TextField name;
    @FXML private TextField pass;
    @FXML private Label label1;
    @FXML private Label label2;

    @FXML
    private void submitter(ActionEvent event){
        String ServAdd="127.0.0.1";
        int port=4545;
        String s = "newbrnch"+"$"+name.getText()+"$"+pass.getText();
        NetworkUtil nc = new NetworkUtil(ServAdd,port);
        nc.write(s);
        System.out.println("Request Sent");    //(name,BG,age,weight,hall,mobile,date)
        String s2 = (String) nc.read();
        s2 = s2.trim();
        if(s2.equals("Successful")){
            label2.setText("");
            label1.setText("Your name is the Username");
        }else{
            label1.setText("");
            label2.setText("Entry failed. Try Again");
        }
    }
    
    @FXML
    private void backctrl(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        
        Parent root1 = FXMLLoader.load(getClass().getResource("TextContent/Offices.fxml")); /* Exception */
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
