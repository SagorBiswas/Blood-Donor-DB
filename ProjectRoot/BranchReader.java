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
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BranchReader implements Runnable{
    private String filename = "TextContent/Branch.txt";
    private ArrayList<Branch>Blist;
    private String bid;
    private String bpass;
    
    BranchReader(String s1){
        bid = s1;
    }
    
    @Override
    public void run() {
        Branch off;
        try {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                while((off = (Branch)is.readObject())!=null){
                    if(off.Getid().equals(bid)){
                        System.out.println("id found");
                       // nc.write(off.Getpass());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
}
