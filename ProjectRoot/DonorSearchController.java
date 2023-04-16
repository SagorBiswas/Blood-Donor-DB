
package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DonorSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField BG;
    
    @FXML
    private void Searchdonor(ActionEvent event){
        String ServAdd="192.168.0.111";
        int port=4545;
        System.out.println("Started Searching");
        String s = "Search"+BG.getText();
        NetworkUtil nc = new NetworkUtil(ServAdd,port);
        nc.write(s);
        //start readerThread
        //hide this page
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
