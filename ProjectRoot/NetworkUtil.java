
package javafxapplication1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class NetworkUtil {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public NetworkUtil(String s, int port) {
        try {
            this.socket=new Socket(s,port);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            //System.out.println("In NetworkUtil : " + e.toString());
            System.out.println("In NetworkUtil : Problem opening soccket");
        }
    }

    public NetworkUtil(Socket s) {
        try {
            this.socket = s;
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            //System.out.println("In NetworkUtil : " + e.toString());
            System.out.println("In NetworkUtil : Problem opening soccket");
        }
    }

    public Object read() {
        Object o = null;
        try {
                o=ois.readObject();
        } catch (Exception e) {
                //System.out.println("Reading Error in network : " + e.toString());
                System.out.println("Reading Error in Network");
                e.printStackTrace();
        }
        return o;
    }

    public void write(Object o) {
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            //System.out.println("Writing  Error in network : " + e.toString());
            System.out.println("Writting Error in Network");
        }
    }

    public void closeConnection() {
        try {
            ois.close();
            oos.close();
        } catch (Exception e) {
            //System.out.println("Closing Error in network : "  + e.toString());
            System.out.println("In NetworkUtil : Problem closing");
        }
    }    
}
