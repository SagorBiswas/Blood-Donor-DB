
package javafxapplication1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author SAGOR
 */

public class Server {
    private ServerSocket servsock;
    private int port;
    private NetworkUtil nc;
    private ArrayList<Donor>Dlist;
    private ArrayList<Branch>Blist;
    
    Server() {
        System.out.println("Server Started");
        port = 4545;
        String filename = "TextContent/Database.txt";
       // Dlist = new ArrayList<Donor>();
        try {
            servsock = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Problem creating server");
        }
        while(true){
            try {
                Socket clientsock = servsock.accept();
                System.out.println("received");
                nc = new NetworkUtil(clientsock);
                String s = (String) nc.read();
                        System.out.println(s);
                s = s.trim();
                String []parts = s.split("\\$");
                        System.out.println(parts[0] + " " +parts[1]);
                        
                        
                if(parts[0].equals("Search")){
//                    Finder find = new Finder(parts[1],clientsock);

                    //Finder Thread
                    {
                        Donor donor ;
                        System.out.println("done");
                        try {
                            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                                ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
                                String slvk =(String) osssd.readObject();
                                osssd.close();
                                ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("TextContent/servlog.txt"));
                                iss.writeObject(slvk+"\n"+parts[1]+" Group Searched");
                                while((donor = (Donor)is.readObject())!= null){

                                    if(donor.GetBGroup().equals(parts[1])&& donor.IsAble()){ 
                                        System.out.println("matched");
                                        donor.ShowInfo();

                                        String msg = donor.GetName()+"$"+donor.GetAge()+"$"+donor.GetBGroup()+"$"+donor.GetWeight()+"$"+donor.GetHall()+"$"+donor.GetMobile();

                                        nc.write(msg);
                                        System.out.println("written to client");
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found while loading data");
                        }catch (ClassNotFoundException ex) {
                            System.out.println("Class not found while loading data");
                        } catch (IOException ex) {
                            System.out.println("EOF Reached");
                            //ex.printStackTrace();
                        } finally{
                            nc.write("end");
                            System.out.println("All member Uploaded");
                            
                        }
                    }                                 
                }
                
                
                
                else if(parts[0].equals("Update")){
                    //Updater updater = new Updater(parts[1],parts[2],parts[3],clientsock);//mbl//choice//info                   
                        //UPdater Thread
                        {
                            int i;
                            System.out.println("Inside Updater");
                            Dlist = new ArrayList<Donor>();
                            try { 
                                
                                try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
                                    Donor dn;
                                    while((dn = (Donor) is.readObject())!=null){
                                        Dlist.add(dn);
                                    }
                                }

                            } catch (FileNotFoundException ex) {
                                System.out.println("File not found while loading data");
                                //ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                System.out.println("Class not found while loading data");
                            } catch (IOException ex) {
                                System.out.println("EOF Reached");
                            } finally{
                                int r=0;
                                for(Donor donor : Dlist){
                                    if(donor.GetMobile().equals(parts[1])){
                                        System.out.println("Mobile no. matched");
                                            switch(parts[2]){
                                                case "Name":donor.UpdateName(parts[3]);
                                                               break;

                                                case "Age":  i = Integer.parseInt(parts[3]);
                                                           donor.UpdateAge(i);
                                                               break;
                                                case "Weight": i = Integer.parseInt(parts[3]);
                                                           donor.UpdateWeight(i);
                                                               break;
                                                case "Hall":donor.UpdateHall(parts[3]);
                                                               break;
                                                case "Mobile":donor.UpdateMobile(parts[3]);
                                                               break;
                                                case "Date":donor.UpdateDate(parts[3]);
                                                               break;
                                            }
                                        r=1;
                                        System.out.println("Updated");
                                        ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
                                        String slvk;
                                        try {
                                            slvk = (String) osssd.readObject();
                                            osssd.close();
                                        ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("TextContent/servlog.txt"));
                                        iss.writeObject(slvk+"\n"+donor.GetName()+"Updated");
                                        } catch (ClassNotFoundException ex) {
                                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        nc.write("Successful");
                                        break;
                                    }
                                }
                                if(r !=1)nc.write("Unsuccessful");
                            }

                            try {
                                try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
                                    for(Donor donor : Dlist){
                                        os.writeObject(donor);
                                    }
                                }
                            }catch(FileNotFoundException e){
                                System.out.println("file not found while writing data");
                            }catch(NullPointerException e){
                                System.out.println("Null found while writing data");
                            }catch (IOException ex) {
                                System.out.println("Problem writinig Data to file");
                            }

                        }
                }
                
                
                
                else if(parts[0].equals("Entry")){
                    //Adder adr = new Adder(parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],parts[7],clientsock);               
                    //Adder Thread
                    {
                        int flag = 0;
                        System.out.println("Inside Adder");
                        Dlist = new ArrayList<Donor>();
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
                            System.out.println("Class not found while loading data");
                        } catch (IOException ex) {
                            System.out.println("EOF Reached");
                        } finally{                               
                            Donor dnr = new Donor(parts[1],parts[2],Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),parts[5],parts[6],parts[7]);
                            Dlist.add(dnr);
                            flag = 1;
                            ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
                                String slvk;
                            try {
                                slvk = (String) osssd.readObject();
                                 osssd.close();
                            ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("TextContent/servlog.txt"));
                            iss.writeObject(slvk+"\n"+dnr.GetName()+"Added to Database");
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
                               
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
                            System.out.println("file not found while writing data");
                        }catch(NullPointerException e){
                            System.out.println("Null found while writing data");
                        }catch (IOException ex) {
                            System.out.println("Problem writinig Data to file");
                        }
                    }
                }
                else if(parts[0].equals("login")){
                    //BranchReader tred = new BranchReader(parts[1]);
                    {
                    Branch off;
                    String filename2 = "TextContent/Branch.txt";
                    String bid = parts[1];
                    int flag = 0 ;
                        try {
                            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename2))) {
                                while((off = (Branch)is.readObject())!=null){
                                    if(off.Getid().equals(bid)){
                                        System.out.println("id found");
                                        nc.write(off.Getpass());
                                        flag = 1;
                                        ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("servlog.txt"));
                                        String slvk =(String) osssd.readObject();
                                        osssd.close();
                                        ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("servlog.txt"));
                                        iss.writeObject(slvk+"\n"+parts[1]+"Tried to Login");
                                    }
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("File not found");
                        } catch (IOException ex) {
                            //ex.printStackTrace();
                            System.out.println("End of File");
                        } catch (ClassNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("Class not found");
                        }
                        
                        if(flag == 0)nc.write("-1not found1-");
                    }
                }
                
                
                else if(parts[0].equals("newbrnch")){
                    //BranchWriter tred = new BranchWriter(parts[1],parts[2]);
                    {
                    Branch off;
                    String filename2 = "TextContent/Branch.txt";
                    Blist = new ArrayList<Branch>();
                    String bid = parts[1];
                    String bpass = parts[2];
                    int flag = 0;
                        try {
                            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename2))) {
                                while((off = (Branch)is.readObject())!=null){
                                    Blist.add(off);
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("File not found");
                        } catch (IOException ex) {
                            //ex.printStackTrace();
                            System.out.println("End of File");
                        } catch (ClassNotFoundException ex) {
                           //ex.printStackTrace();
                           System.out.println("Class not found");
                        }finally{
                            Blist.add(new Branch(bid,bpass));
                            nc.write("Successful");
                            flag = 1;
                            ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
                                String slvk;
                        try {
                            slvk = (String) osssd.readObject();
                            osssd.close();
                            ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("TextContent/servlog.txt"));
                                iss.writeObject(slvk+"\n"+parts[1]+" Unit Added to jone");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                
                        }
                        if(flag == 0)nc.write("Unsuccessful");

                        try{
                            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename2));
                            for(Branch brnch : Blist){
                                    os.writeObject(brnch);
                            }
                                os.flush();
                                os.close();
                        } catch (FileNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("File not found");
                        } catch (IOException ex) {
                           // ex.printStackTrace();
                            System.out.println("End of File");
                        }
                    }
                }
                
                
                else if(parts[0].equals("passchange")){
                    //PassChanger tred = new PassChanger(parts[1],parts[2],parts[3]);
                    {
                    Branch off;
                    String filename2 = "TextContent/Branch.txt";
                    Blist = new ArrayList<Branch>();
                    String bid = parts[1];
                    String bpass = parts[2];
                    String npass = parts[3];
                    int flag = 0;
                        try {
                            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename2))) {
                                while((off = (Branch)is.readObject())!=null){
                                    Blist.add(off);
                                }
                            }
                        } catch (FileNotFoundException ex) {
                            System.out.println("File not found");
                            //ex.printStackTrace();
                        } catch (IOException ex) {
                            //ex.printStackTrace();
                            System.out.println("End of File");
                        } catch (ClassNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("Class not found");
                        }finally{
                            for(Branch brnch : Blist){
                               if(brnch.Getid().equals(bid) && brnch.Getpass().equals(bpass)){
                                   brnch.Setpass(npass);
                                   flag = 1;
                                   nc.write("Successful");
                                   break;
                            
                               }
                            }
                            if(flag == 0)nc.write("Unsuccessful");
                            else{
                                ObjectInputStream osssd = new ObjectInputStream(new FileInputStream("TextContent/servlog.txt"));
                                String slvk;
                                try {
                                    slvk = (String) osssd.readObject();
                                    osssd.close();
                                ObjectOutputStream iss = new ObjectOutputStream(new FileOutputStream("TextContent/servlog.txt"));
                                iss.writeObject(slvk+"\n"+parts[1]+" Unit Changed it's Password");
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                        }

                        try{
                            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename2));
                            for(Branch brnch : Blist){
                                os.writeObject(brnch);
                            }
                            os.flush();
                            os.close();
                        } catch (FileNotFoundException ex) {
                            //ex.printStackTrace();
                            System.out.println("File not found");
                        } catch (IOException ex) {
                            //ex.printStackTrace();
                            System.out.println("End of File");
                        }
                    }
                }
                else {
                    System.out.println("No Item matched");
                    nc.write("No Items matched");
                }
                
    //End of server processing
                
            } catch (IOException ex) {
                //ex.printStackTrace();
                System.out.println("Problem loading Server");
            }
        }
    }
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost());
        Server s = new Server();
    }
}
