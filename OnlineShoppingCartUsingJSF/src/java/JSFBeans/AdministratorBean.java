/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2productsFacade;
import entities.Group2products;
import entities.UserOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
@Named(value = "administratorBean")
@RequestScoped
public class AdministratorBean {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @EJB
    private Group2productsFacade group2productsFacade;
    private Group2products group2products;
    
    private List<UserOrder> userOrders;
    
    //default constructor
    public Group2products getGroup2products() {
        return group2products;
    }

    //setter for products
    public void setGroup2products(Group2products group2products) {
        this.group2products = group2products;
    }
    
    

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public AdministratorBean() {
        this.group2products = new Group2products();
    }

    //method to create products
    public void createProduct() {
        this.group2productsFacade.create(group2products);
    }

    //method to display list of all products
    public List<Group2products> getAllProduct() {

        // create named query and set parameter
        Query query = em.createNamedQuery("Group2products.findAll");
        // return query result
        return query.getResultList();
    }
    
    //method to display list of orders
    public List<UserOrder> getUserOrders() {
        if(null == userOrders){
            List<Group2products> products = this.getAllProduct();
            userOrders = new ArrayList<>();
            for(Group2products product : products) {
                userOrders.add(new UserOrder(product));
            }
        }
        return userOrders;
    }
    
    //method to delete products
    public void deleteProduct(Group2products group2products) {
        if (group2products != null) {
            this.group2productsFacade.remove(group2products);
            Logger.getLogger("product removed by admin: " + group2products.toString() );
        }
    }

    //method to find products by ID
    public Group2products getById(int id) {

        // create named query and set parameter
        Query query = em.createNamedQuery("Group2products.findById")
                .setParameter("productID", id);
        // return query result
        return (Group2products) query.getResultList().get(0);
    }

    //method to update products
    public void updateProduct(int id) {
        if (id != 0) {
            group2products = getById(id);
        }

    }

    //method to update products
    public void update() {
        this.group2productsFacade.edit(group2products);
    }

    
     public String lookUpAction() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
  
        String id = request.getParameter("productID");
        if(id!=null){
            return id;
        }
        return "0";
    }
}
