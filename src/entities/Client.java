/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Client {

    private int id;
    private String surname;
    private String nameC;
    private String telephone;
    private String address;
    
    public void setID(int id){
        this.id = id;
    }
    
    public int getID(){
        return id;
    }
    
    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setNameC(String nameC){
        this.nameC = nameC;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getSurname(){
        return surname;
    }

    public String getNameC(){
        return nameC;
    }

    public String getTelephone(){
        return telephone;
    }

    public String getAddress(){
        return address;
    }
}
