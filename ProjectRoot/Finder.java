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
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class Finder implements Runnable{
    //Donor donor;
    String BGroup;
    String filename;
   // Socket ClientSocket ;
    NetworkUtil nc;
    
    Finder(String BG,Socket cs){
        this.BGroup = BG;
        this.filename = "TextContent/Database.txt";
        //donor = null;
        //this.ClientSocket = cs;
        nc = new NetworkUtil(cs);
        System.out.println("Finder Started");
        new Thread(this).start();
    }
    Finder(String BG){
        this.BGroup = BG;
        this.filename = "Database.txt";
        //donor = null;
        System.out.println("Finder Started");
        new Thread(this).start();
    }

    @Override
    public void run() {
        Donor donor ;
        System.out.println("done");
        try {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                while((donor = (Donor)is.readObject())!= null){
                //while(true){
//                System.out.println("Inside Try");
//                    System.out.println(donor.GetBGroup());
//                    System.out.println(donor.GetDate());
                    //donor = (Donor) is.readObject();
                    if(donor.GetBGroup().equals(BGroup)&& donor.IsAble()){ //
                        System.out.println("matched");
                        //donor.GetDate();
                        donor.ShowInfo();
                        
                        nc.write("Hello World");
                        //nc.write(donor);
                        System.out.println("written to client");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found while loading data");
        }catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        } catch (IOException ex) {
            System.out.println("Error Occured");
            //ex.printStackTrace();
        } 
        
    }

    public static void main(String[] args) {
        System.out.println("All right");
    }
}
