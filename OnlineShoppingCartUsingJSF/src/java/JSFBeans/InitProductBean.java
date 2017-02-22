/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import boundary.Group2productsFacade;
import entities.Group2products;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author karthik
 */
@ManagedBean (eager=true)
//@Named(value = "initProductBean")
@ApplicationScoped
public class InitProductBean {

    /**
     * Creates a new instance of InitProductBean
     */
    @EJB
    private Group2productsFacade group2ProductFacade;
    
    @PostConstruct
    public void onStartup(){
        System.out.println("Server started, ready to creat products");
       this.creatDefaultUsers();
    }
    
    @PreDestroy
    public void onShutdown(){
        System.out.println("Server is shutting down");
    }
    
    //method to create default products in the database
    public void creatDefaultUsers(){
        System.out.println("Creating default products");

        this.createProduct("Apple", 100);
        this.createProduct("Orange", 100);
        this.createProduct("Banana", 100);
        this.createProduct("Peach", 100);
        this.createProduct("Pear", 100);
        System.out.println("Products created");
    }
    
    //method to craete new products in the database
    private void createProduct(String productName, int quantity){
        Group2products newProduct = new Group2products();
        newProduct.setProductname(productName);
        newProduct.setQuantity(quantity);
        this.group2ProductFacade.create(newProduct);
        Logger.getLogger("Product Name" + productName + ": " + quantity);
    }
    
    //default constructor
    public InitProductBean() {
    }
    
}
