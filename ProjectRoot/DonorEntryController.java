/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DonorEntryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField name;
    @FXML private TextField age;
    @FXML private TextField weight;
    @FXML private TextField mbl;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private ComboBox<String>BGcmbx;
    @FXML private DatePicker dp;
    @FXML private ComboBox<String>hallcmbx;
    
    ObservableList<String>groups = FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-","AB+","AB-");
    ObservableList<String>halls = FXCollections.observableArrayList("AhsanUllah Hall","Titumir Hall","Sher-e-Bangla Hall","Suhrawardy Hall","Dr.M.A.Rashid Hall","Nazrul Hall","Shaheed Smrity Hall","Chatri Hall");
    
    @FXML
    private void btnctrl(ActionEvent event){
        String ServAdd="127.0.0.1";
        int port=4545;
        String dts[] = dp.getValue().toString().split("\\-");
        String s = "Entry"+"$"+name.getText()+"$"+BGcmbx.getValue()+"$"+age.getText()+"$"+weight.getText()+"$"+hallcmbx.getValue()+"$"+mbl.getText()+"$"+(dts[1]+"/"+dts[2]+"/"+dts[0]);
        NetworkUtil nc = new NetworkUtil(ServAdd,port);
        nc.write(s);
        System.out.println("Request Sent");    //(name,BG,age,weight,hall,mobile,date)
        String s2 = (String) nc.read();
        s2 = s2.trim();
        if(s2.equals("Successful")){
            label2.setText("");
            label1.setText("Entry Successful");
        }else{
            label1.setText("");
            label2.setText("Entry failed. Try Again");
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
        name.setText("");
        age.setText("");
        weight.setText("");
        mbl.setText("");
        label1.setText("");
        label2.setText("");
        BGcmbx.setPromptText("Blood Group");
        hallcmbx.setPromptText("Recidential Hall");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        set();
    }    
    
    void set(){
        BGcmbx.setItems(groups);
        hallcmbx.setItems(halls);
    }
    
}
