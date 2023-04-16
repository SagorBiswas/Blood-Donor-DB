/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ServerGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextArea txt;
    
    @FXML
    private void show(ActionEvent event) {
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
            String s =(String) os.readObject();
            txt.setText(s);
            os.close();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
