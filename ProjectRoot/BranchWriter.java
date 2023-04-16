/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class BranchWriter implements Runnable {
    private String filename = "TextContent/Branch.txt";
    private ArrayList<Branch>Blist;
    private String bid;
    private String bpass;
    
    BranchWriter(String s1,String s2){
        bid = s1;
        bpass = s2;
    }
    
    @Override
    public void run() {
        Branch off;
        try {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                while((off = (Branch)is.readObject())!=null){
                    Blist.add(off);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally{
            Blist.add(new Branch(bid,bpass));
        }
        
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            for(Branch brnch : Blist){
                    os.writeObject(brnch);
                }
                os.flush();
                os.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
