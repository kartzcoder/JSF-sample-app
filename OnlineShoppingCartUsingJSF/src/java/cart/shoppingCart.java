/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import boundary.Group2poFacade;
import boundary.Group2productsFacade;
import entities.Group2po;
import entities.Group2products;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author karthik
 */
@Stateless
public class shoppingCart implements shoppingCartLocal {
    private Map<String, Integer> items = new HashMap<>();
    private int poNumber = 0;
    private int userName;
    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;
    
    @EJB
    private Group2productsFacade group2productsFacade;
    
    @EJB
    private Group2poFacade group2poFacade;
    
    @Override
    public void addItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity += quantity;
        items.put(id, orderQuantity);
    }

    @Override
    public void removeItem(String id, int quantity) {
        // obtain current number of items in cart
        Integer orderQuantity = items.get(id);
        if (orderQuantity == null) {
            orderQuantity = 0;
        }
        // adjust quantity and put back to cart
        orderQuantity -= quantity;
        if (orderQuantity <= 0) {
            // final quantity less equal 0 - remove from cart
            items.remove(id);
        } else {
            // final quantity > 0 - adjust quantity
            items.put(id, orderQuantity);
        }
        
    }

    @Override
    @Remove
    public String checkout() {
        // dummy checkout method that just returns message for successful 
        // checkout
        String message = "";
        String checkedOut = "You checked out the following items:\n";
        for(Map.Entry<String, Integer> entry: items.entrySet()){
            String productName = entry.getKey();
            Integer orderQuantity = entry.getValue();
            
            Query query = em.createNamedQuery("Group2products.findByProductname")
                .setParameter("productname", productName);
            Group2products product = (Group2products) query.getResultList().get(0);
            System.out.println("product quantity "+product.getQuantity());
            int tempNumber = product.getQuantity()-orderQuantity;
            if(tempNumber > -1){
                
                product.setQuantity(tempNumber);
                this.group2productsFacade.edit(product);
                checkedOut += productName + " Quantity: " + orderQuantity+ "\n";
                System.out.println("message 1  "+ message);
            }
            else{
                message = message + "Not enough stock for product " + product.getProductname();
                System.out.println("message 2  "+ message);
                
            }
            
            
        }
               
        message = checkedOut + "\n" + message;
        // empty storage
        
        
        items.clear();
        return message;
    }

    @Override
    @Remove
    public void cancel() {
        // no action required - annotation @Remove indicates
        // that calling this method should remove the EJB which will
        // automatically destroy instance variables
        // empty storage
        items.clear();
    }

    @Override
    public String getItemList() {
        String message = "";
        Set<String> keys = items.keySet();
        Iterator<String> it = keys.iterator();
        String k;
        while (it.hasNext()) {
            k = it.next();
            message += k + ", quantity: " + items.get(k) + "; \n<br>";
        }
        return message;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public void writeToPO(String username) {
        Map<String, Integer> writeItems = items;
        for(Map.Entry<String, Integer> entry: writeItems.entrySet()){
            System.out.println("toPO " + entry.getKey());
            String productName = entry.getKey();
            Integer orderQuantity = entry.getValue();
            for (int i = 1;i<= orderQuantity;i++){
                poNumber++;
                System.out.println("PO number " + poNumber + " product name " + 
                        productName + " by "+username);
                Group2po po = new Group2po();
                po.setItems(productName);
                po.setUsername(username);
                
                this .group2poFacade.create(po);
            }
            
        }


    }

    
    
}
