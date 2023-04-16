/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class ServerGUImain extends Application {
    
    @Override
    public void start(Stage stage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TextContent/ServerGUI.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Server Log");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error in loading login page");
            //ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        Server sver = new Server();
        
    }
    
}
