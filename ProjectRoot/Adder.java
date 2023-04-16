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
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Adder implements Runnable{
    private String Name;
    private String Hall;
    private String Mobile;
    private String BGroup;
    private int Age;
    private int Weight;
    private String date;
    private NetworkUtil nc;
    private ArrayList<Donor>Dlist;
    
    Adder(String name,String BG,String age,String weight,String hall,String mobile,String date,Socket cs){
        this.Name = name;
        this.Hall = hall;
        this.Mobile = mobile;
        this.BGroup = BG;
        this.Age = Integer.parseInt(age);
        this.date = date;
        this.Weight = Integer.parseInt(weight);
        nc = new NetworkUtil(cs);
        Dlist = new ArrayList<Donor>();
    }
    
    
    @Override
    public void run() {
        String filename = "TextContent/Database.txt";
        int flag = 0;
        System.out.println("Inside Adder");
        try {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                Donor dn;
                
                while((dn = (Donor) is.readObject())!=null){
                    Dlist.add(dn);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found while loading data");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        } catch (IOException ex) {
            System.out.println("Null found");
        } finally{
            Donor dnr = new Donor(Name,BGroup,Age,Weight,Hall,Mobile,date);
            Dlist.add(dnr);
            flag = 1;
            System.out.println("Successfully Added");
            nc.write("Successful");
        }
        
        if(flag != 1)nc.write("Unsuccessful");
    
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
                for(Donor donor : Dlist){
                    os.writeObject(donor);
                }
                os.flush();
                os.close();
            }catch(FileNotFoundException e){
                System.out.println("file not found");
            }catch(NullPointerException e){
                System.out.println("Null found");
            }catch (IOException ex) {
                System.out.println("Problem is severe");
            }
    }
}
