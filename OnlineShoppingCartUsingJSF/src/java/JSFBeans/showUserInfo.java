/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2usersFacade;
import entities.Group2users;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author karthik
 */
@Named(value = "showUserInfo")
@RequestScoped
public class showUserInfo {

    @EJB
    private Group2usersFacade group2usersFacade;

    /**
     * Creates a new instance of showUserInfo
     */
    private Group2users user;
    
    //default constructor
    public showUserInfo() {
        this.user = new Group2users();
    }
    
    //getter for user
    public Group2users getUser(){
        return user;
    }
    
    //getter for username
    public String getUsername(){
        return group2usersFacade.findAll().get(1).getProfile();
    }
    
    //method to create user
    public String createUser() {
     
        this.group2usersFacade.create(user);
        return "index";
    }
    
}
