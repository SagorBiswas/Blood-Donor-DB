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
public class Updater implements Runnable{
    //private Donor donor;
    private String Choice;
    //private int Choice ; 
    private String info ;
    private String mobile;
    private NetworkUtil nc;
    private ArrayList<Donor>Dlist;
    
    Updater(String mobile,String choice,String info,Socket cs){
        this.mobile = mobile;
        this.Choice = choice;
        this.info = info ;
        nc = new NetworkUtil(cs);
        //donor = null;
        Dlist = new ArrayList<>();
        new Thread(this).start();
    }
    
//    Updater(String mobile,String choice,String info,Socket cs){
//        this.mobile = mobile;
//        this.Choice = Integer.parseInt(choice);
//        this.info = info ;
//        nc = new NetworkUtil(cs);
//        //donor = null;
//        Dlist = new ArrayList<>();
//        new Thread(this).start();
//    }
    
    @Override
    public void run(){
        int i;
        String filename = "TextContent/Database.txt";
        System.out.println("Reached to Updater");
        try {            
//            Donor donor = (Donor) is.readObject();
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                            //Donor donor = (Donor) is.readObject();
                Donor dn;
                while((dn = (Donor) is.readObject())!=null){
                    Dlist.add(dn);
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found while loading data");
            //ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class not found");
        } catch (IOException ex) {
            System.out.println("Null found");
        } finally{
            int r=0;
            for(Donor donor : Dlist){
                if(donor.GetMobile().equals(mobile)){
                    System.out.println("Mobile no. matched");
                        switch(Choice){
                            case "Name":donor.UpdateName(info);
                                           break;

                            case "Age":  i = Integer.parseInt(info);
                                       donor.UpdateAge(i);
                                           break;
                            case "Weight": i = Integer.parseInt(info);
                                       donor.UpdateWeight(i);
                                           break;
                            case "Hall":donor.UpdateHall(info);
                                           break;
                            case "Mobile":donor.UpdateMobile(info);
                                           break;
                            case "Date":donor.UpdateDate(info);
                                           break;
                        }
                    r=1;
                    System.out.println("Updated");
                    String sk = "Successful";
                    nc.write(sk);
                    break;
                }
            }
            if(r !=1)nc.write("Unsuccessful");
        }
         


//                         switch(Choice){
//                                case 1:donor.UpdateName(info);
//                                               break;
//                                case 2:  i = Integer.parseInt(info);
//                                           donor.UpdateAge(i);
//                                               break;
//                                case 3: i = Integer.parseInt(info);
//                                           donor.UpdateWeight(i);
//                                               break;
//                                case 4 :donor.UpdateHall(info);
//                                               break;
//                                case 5:donor.UpdateMobile(info);
//                                               break;
//                                case 6:donor.UpdateDate(info);
//                                               break;
//                        }


        try {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
                for(Donor donor : Dlist){
                    os.writeObject(donor);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("file not found");
        }catch(NullPointerException e){
            System.out.println("Null found");
        }catch (IOException ex) {
            System.out.println("Problem is severe");
        }
        
    }
    public static void main(String[] args) {
        System.out.println("All right");
    }
}
//    System.out.println("What do you want to update?");
//    System.out.println("1. Name.");
//    System.out.println("2. Age");
//    System.out.println("3. Weight");
//    System.out.println("4. Recidential Hall");
//    System.out.println("5. MObile Number");
//    System.out.println("6. Donate Date");