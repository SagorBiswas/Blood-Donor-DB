
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PageShowController implements Initializable {
    
    @FXML private Label label;
    @FXML private ComboBox<String>BGcmbx;
    //define table
    @FXML TableView Table = new TableView<Info>();
    @FXML TableColumn<Info,String>name;
    @FXML TableColumn<Info,Integer>age;
    @FXML TableColumn<Info,Integer>weight;
    @FXML TableColumn<Info,String>BG;
    @FXML TableColumn<Info,String>hall;
    @FXML TableColumn<Info,String>mobile;
    
    //create table data
    ObservableList<String>groups = FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-","AB+","AB-");
    final ObservableList<Info>data = FXCollections.observableArrayList( );
    //String name,Integer age,String BG,Integer weight,String hall,String mobile   
   
    
    @FXML
    private TextField BGroup;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
//        data.removeAll();
//        Table.refresh();
        String ServAdd="127.0.0.1";
        int port=4545;
        NetworkUtil nc = new NetworkUtil(ServAdd,port);
        String s = "Search"+"$"+BGcmbx.getValue();
        nc.write(s);
        System.out.println("Search Request Sent");
        Info tbl;
        while(true){
            String sfd = (String) nc.read();
            System.out.println(sfd);
            if(sfd.equals("end"))break;
//            dn = (Donor) nc.read();
//            tbl = new Info(dn.GetName(),dn.GetAge(),dn.GetBGroup(),dn.GetWeight(),dn.GetHall(),dn.GetMobile());
            String []parts = sfd.split("\\$");
            for(int j=0;j<6;j++){
                System.out.println(parts[j]);
            }
            
            System.out.println("Declaring Table");
            tbl = new Info(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5]);
            
            data.add(tbl);
            
        }
    }    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        BGcmbx.setItems(groups);
        
        name.setCellValueFactory(new PropertyValueFactory<Info,String>("name"));
        age.setCellValueFactory(new PropertyValueFactory<Info,Integer>("age"));
        BG.setCellValueFactory(new PropertyValueFactory<Info,String>("BG"));
        weight.setCellValueFactory(new PropertyValueFactory<Info,Integer>("weight"));
        hall.setCellValueFactory(new PropertyValueFactory<Info,String>("hall"));
        mobile.setCellValueFactory(new PropertyValueFactory<Info,String>("mobile"));
        
        Table.setItems(data);
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
    private void Terminate(ActionEvent event){
        System.exit(0);
    }
}
