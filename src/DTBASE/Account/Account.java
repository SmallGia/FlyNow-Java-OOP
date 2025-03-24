/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTBASE.Account;

/**
 *
 * @author Tam
 */
public class Account {
    private String username, password, email;
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public Account() {
        super();
    }
    
     public String getUsername() {
        return username;
    }

     public String getPassword() {
        return password;
    }
    
    public String getemail() {
        return email;
    }
    
     public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String Password) {
        this.password = Password;
    } 
     
     public void setEmail(String Email) {
        this.email = Email;
    } 
    
}
