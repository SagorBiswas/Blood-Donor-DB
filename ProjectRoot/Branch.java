/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Branch implements Serializable{
    private String id;
    private String pass;
    Branch(String id,String pass){
        this.id = id;
        this.pass = pass;
    }
    void Setid(String id){
        this.id = id;
    }
    void Setpass(String pass){
        this.pass = pass;
    }
    String Getid(){
        return id;
    }
    String Getpass(){
        return pass;
    }
}
