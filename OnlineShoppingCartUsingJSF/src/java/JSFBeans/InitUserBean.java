/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2usersFacade;
import entities.Group2users;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author karthik
 */
@ManagedBean(eager=true)
@javax.faces.bean.ApplicationScoped
public class InitUserBean {
    
    @EJB
    private Group2usersFacade group2usersFacade;
   
    @PostConstruct
    public void onStartup(){
        System.out.println("Server started, ready to creat users");
        this.creatDefaultUsers();
    }
    
    @PreDestroy
    public void onShutdown(){
        System.out.println("Server is shutting down");
    }
    
    //method to create default users in the database 
    public void creatDefaultUsers(){
        System.out.println("Creating default users");
        this.createUser("joe", "1D10T?", "customer", "this is the user on the notes");
        this.createUser("toor", "4uIdo0!", "admin", "this is the admin on the notes");
        this.createUser("john", "123456", "customer", "random user");
        this.createUser("Killian", "123456", "customer", "random user");
        this.createUser("kirby", "123456", "customer", "random user");
        System.out.println("User created");        
    }
    
    //method to create new users in the database
    private void createUser(String username, String password, String type, String profile) {
        Group2users newUsers = new Group2users();
        newUsers.setUsername(username);
        newUsers.setPassword(password);
        newUsers.setUsertype(type);
        if(null != profile){
            newUsers.setProfile(profile);
        }
        this.group2usersFacade.create(newUsers);
         Logger.getLogger("New user added: " + username + "type: " + type );
    }
    
}