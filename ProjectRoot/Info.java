/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ASUS
 */
public class Info {
    private final SimpleStringProperty name;
    private final SimpleStringProperty age;
    private final SimpleStringProperty BG;
    private final SimpleStringProperty weight;
    private final SimpleStringProperty hall;
    private final SimpleStringProperty mobile;
    
    Info(String name,String age,String BG,String weight,String hall,String mobile){
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleStringProperty(age);
        this.BG = new SimpleStringProperty(BG);
        this.weight = new SimpleStringProperty(weight);
        this.hall = new SimpleStringProperty(hall);
        this.mobile = new SimpleStringProperty(mobile);
    }
    
    //getters 
    
    public String getName(){
        return name.get();
    }
    public String getAge(){
        return age.get();
    } 
    public String getBG(){
        return BG.get();
    } 
    public String getWeight(){
        return weight.get();
    } 
    public String getHall(){
        return hall.get();
    } 
    public String getMobile(){
        return mobile.get();
    }
    
    //and setters
    
    public void setName(String s){
        name.set(s);
    }
    public void setAge(String n){
        age.set(n);
    }
    public void setBG(String s){
        BG.set(s);
    }
    public void setWeight(String n){
        weight.set(n);
    }
    public void setHall(String s){
        hall.set(s);
    }
    public void setMobile(String s){
        mobile.set(s);
    }
}
