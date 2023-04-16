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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DateUpdateController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private DatePicker dp;
    @FXML private Label LABEL;
    @FXML private Label LABEL1;
    @FXML private TextField mbl;
    @FXML private TextField nme;
    
    
    
     @FXML
    private void btnctrl(ActionEvent event){        
        String ServAdd="127.0.0.1";
        int port=4545;
        String dts[] = dp.getValue().toString().split("\\-");
        String s = "Update"+"$"+mbl.getText()+"$"+"Date"+"$"+(dts[1]+"/"+dts[2]+"/"+dts[0]);
        NetworkUtil nc = new NetworkUtil(ServAdd,port);
        nc.write(s);
        System.out.println("Update Request Sent");      //(mobile,choice,info)
        String s2 = (String) nc.read();
        s2 = s2.trim();
        if(s2.equals("Successful")){
            LABEL.setText("");
            LABEL1.setText("Update Successful");
        }else{
            LABEL.setText("Update Unsuccessful");
            LABEL1.setText("");
        }
    }
    
    @FXML
    private void backctrl(ActionEvent event) throws IOException{
        Node node = (Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        
        Parent root1 = FXMLLoader.load(getClass().getResource("TextContent/Choice.fxml")); /* Exception */
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void clear(){
        mbl.setText("");
        LABEL.setText("");
        LABEL1.setText("");
        nme.setText("");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
