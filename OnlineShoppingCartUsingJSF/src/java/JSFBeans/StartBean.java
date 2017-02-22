/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2usersFacade;
import cart.shoppingCartLocal;
import entities.Group2users;
import entities.UserOrder;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author karthik
 */
@Named(value = "startBean")
@SessionScoped
public class StartBean implements Serializable {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @EJB
    private shoppingCartLocal shoppingCart;

    @EJB
    private Group2usersFacade group2productsFacade;
    private Group2users group2user;

    private String username, password,checkoutMessage,usernameUpdate,profileUpdate;

    public String getUsernameUpdate() {
        return usernameUpdate;
    }

    public void setUsernameUpdate(String usernameUpdate) {
        this.usernameUpdate = usernameUpdate;
    }

    public String getProfileUpdate() {
        return profileUpdate;
    }

    public void setProfileUpdate(String profileUpdate) {
        this.profileUpdate = profileUpdate;
    }

    private String order = "";

    //default constructor
    public StartBean() {
    }
    
    //getter for username
    public String getUsername() {
        return username;
    }

    //setter for username
    public void setUsername(String username) {
        this.username = username;
        this.usernameUpdate = username;
    }

    //getter for password
    public String getPassword() {
        return password;
    }

    //setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // mathod to find users by username
    public Group2users getByName(String name) {
        // create named query and set parameter
        Query query = em.createNamedQuery("Group2users.findByUsername")
                .setParameter("username", username);
        // return query result
        return (Group2users) query.getResultList().get(0);
    }

    //metod to evaluate user login and direct to appropriate page
    public String evaluate() {
        Group2users user = getByName(username);
        if (user != null && password.equals(user.getPassword())){
            if(user.getUsertype().equals("admin")){
                HttpSession hs = Util.getSession();
                hs.setAttribute("username", username); 
            return "admin?faces-redirect=true";
            }else{
                HttpSession hs = Util.getSession();
                hs.setAttribute("username", username); 
                profileUpdate = user.getProfile();
                return "page2?faces-redirect=true";
            }
        }
        
        else
        {
            return "error?faces-redirect=true";
        }
    }
    
    public String logOut() {
        HttpSession hs = Util.getSession();
        hs.invalidate();
        return "index";
    }
    
    public void addToBasket(UserOrder order) {
        System.out.println(order.getProduct().getProductname());
        System.out.println(order.getUserOrder());
        shoppingCart.addItem(order.getProduct().getProductname(), Integer.parseInt(order.getUserOrder()));
    }
    public shoppingCartLocal getCart(){
        return shoppingCart;
    }

    /**
     * Checkout shopping cart - only stores checked out items in instance
     * variable and removes releases SFSB cart
     *
     * @return Value "checkout" for auto navigation
     */
    public String checkout() {
        shoppingCart.writeToPO(username);
        checkoutMessage = shoppingCart.checkout();
        return "checkout";
    }

    /**
     * Cancels the order. Only releases SFSB cart
     *
     * @return Value "cancel" for auto navigation
     */
    public String cancel() {
        shoppingCart.cancel();
        return "cancel";
    }

    /**
     * Returns a list of items and their quantities that are currently in
     * shopping cart
     *
     * @return Items/quantities in shopping cart
     */
    public String getItemList() {
        String content = shoppingCart.getItemList();
        return content.replace("<br>", "");
    }

    /**
     * Destroys current session
     *
     * @return value "index"
     */
    public String index() {
        // invalidate session to remove reference 
        // to shopping cart - you want a new one next time to make
        // sure to receive a new SFSB
        FacesContext.getCurrentInstance().getExternalContext().
                invalidateSession();
        return "index";
    }
    public String getCheckoutMessage() {
        return checkoutMessage;
    }

    public void setCheckoutMessage(String checkoutMessage) {
        this.checkoutMessage = checkoutMessage;
    }
    
    /**
     * Getter for order
     *
     * @return order
     */
    public String getOrder() {
        return order;
    }
    /**
     * Update the user detail in the data base
     *
     * 
     */
    public void updateUser() {
        group2user = getByName(getUsername());
        System.out.println("getUsername" + group2user.getUsername());
        if (group2user != null) {
            group2user.setUsername(usernameUpdate);
            group2user.setProfile(profileUpdate);
            group2productsFacade.edit(group2user);
        }
    }

}
