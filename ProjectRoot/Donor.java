/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Donor implements Serializable{
    private String Name;
    private String Hall;
    private String Mobile;
    private String BGroup;
    private Date LDate;
    private int Age;
    private int Weight;
    
    Donor(String name,String BG,int age,int weight,String hall,String mobile,String date){
        this.Name = name;
        this.Hall = hall;
        this.Mobile = mobile;
        this.BGroup = BG;
        this.Age = age;
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            date = date+" "+"12:00:00";
            Date d = null;
            try {
                d = format.parse(date);
            } catch (ParseException ex) {
                System.out.println("Error Occured while writing Date");
            }
        this.LDate = d;
        this.Weight = weight;
    }
    
    boolean IsAble(){
        //  converting String to Date
//            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//            date = date+" "+"12:00:00";
//            Date d = null;
//            try {
//                d = format.parse(date);
//            } catch (ParseException ex) {
//                System.out.println("Error Occured while writing Date");
//            }
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        long diff = date.getTime() - LDate.getTime();
        long diffdays = diff/(1000*3600*24);
        
        
        if(diffdays<120)return false;
        else return true; 
    }
    
    void UpdateName(String name){
        this.Name = name;
        System.out.print("After Updating:");
    }
    void UpdateHall(String hall){
        this.Hall = hall;
        System.out.print("After Updating:");
    }
    void UpdateMobile(String mobile){
        this.Mobile = mobile;
        System.out.print("After Updating:");
    }
    void UpdateAge(int age){
        this.Age = age;
        System.out.print("After Updating:");
    }
    void UpdateWeight(int weight){
        this.Weight = weight;
        System.out.print("After Updating:");
    }
    void UpdateDate(String date){
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            date = date+" "+"12:00:00";
            Date d = null;
            try {
                d = format.parse(date);
            } catch (ParseException ex) {
                System.out.println("Error Occured while writing Date");
            }
        this.LDate = d;
        System.out.print("After Updating:");
        System.out.println(Name+" Donated Blood last on: "+date);
    }
    String GetBGroup(){
        return BGroup;
    }
    String GetMobile(){
        return Mobile;
    }
    String GetName(){
        return Name;
    }
    int GetAge(){
        return Age;
    }
    int GetWeight(){
        return Weight;
    }
    String GetHall(){
        return Hall;
    }
    String GetDate(){
        return LDate.toString();
    }
    public static void main(String[] args) {
        System.out.println("All right");
    }
    
    void ShowInfo(){        //String name,String BG,int age,int weight,String hall,String mobile,String date
        System.out.println("Name: "+Name+"  Age: "+Age+" Blood Group: "+BGroup);
        System.out.println(Hall+"Mobile No.: "+Mobile);
    }
    void ShowName(){
        System.out.println("Name: "+Name);
    }
    void ShowAge(){
        System.out.println("Age: "+Age);
    }
    void ShowBGroup(){
        System.out.println("Blood Group: "+BGroup);
    }
    void ShowHall(){
        System.out.println("Hall: "+Hall);
    }
    void ShowMobile(){
        System.out.println("Mobile No.: "+Mobile);
    }
    void ShowLDate(){
        System.out.println(Name+" Donated Blood last on: "+LDate);
    }
    void ShowWeight(){
        System.out.println("Weight : "+Weight);
    }
}

