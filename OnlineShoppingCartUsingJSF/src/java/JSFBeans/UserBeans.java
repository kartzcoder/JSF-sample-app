/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2usersFacade;
import entities.Group2users;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author karthik
 */
@Named(value = "userBeans")
@RequestScoped
public class UserBeans {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @EJB
    private Group2usersFacade group2productsFacade;
    private Group2users group2user;
    private StartBean startBean;
    private String loginName;

    //getter for users
    public Group2users getGroup2user() {
        return group2user;
    }

    //setter for users
    public void setGroup2user(Group2users group2user) {
        this.group2user = group2user;
    }
    private String searchName;
    private String username;
	//getter for username
    public String getUsername() {
        return username;
    }

    
    //setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    //getter for profile
    public String getProfile() {
        return profile;
    }

    
    //setter for profile
    public void setProfile(String profile) {
        this.profile = profile;
    }
    private String profile;

	//getter for searchname
    public String getName() {
        return searchName;
    }

    
    //setter for searchname
    public void setName(String name) {
        this.searchName = name;
    }

    /**
     * Creates a new instance of UserBeans
     */
    public UserBeans() {
    }

    //method to list customers
    public List<Group2users> getUsersType() {

        Query query = em.createNamedQuery("Group2users.findByUsertype")
                .setParameter("usertype", "customer");
        return query.getResultList();
    }

    //method to search searchnames
    public List<Group2users> getUsersName() {

        Query query = em.createNamedQuery("Group2users.findByUsername")
                .setParameter("username", searchName);
        return query.getResultList();
    }
    
    //method to return getUsername list
    public Group2users getByName() {
        return getUsersName().get(0);
    }

    //method to update 
    public void update( ) {
        System.out.println("=======" + loginName);
        group2user = startBean.getByName(loginName);
        if (group2user != null) {
            System.out.println("group2user is not null");
            group2user.setUsername(username);
            group2user.setProfile(profile);
            group2productsFacade.edit(group2user);
        }
    }

    public void lookUpAction() {
        System.out.println("\\\\\\\\\\\\\\\\\\");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String name = request.getParameter("username");
        loginName = name;
        System.out.println("action==========" + loginName);
    }
}
