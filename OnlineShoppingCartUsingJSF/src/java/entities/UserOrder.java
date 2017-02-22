/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import cart.shoppingCart;
import cart.shoppingCartLocal;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author karthik
 */

public class UserOrder implements Serializable {

    private Group2products product;
    private String userOrder = "0";

    public Group2products getProduct() {
        return product;
    }

    public void setProduct(Group2products product) {
        this.product = product;
    }

    public String getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(String userOrder) {
        System.out.println("set order " + userOrder + " for product " + this.product.getProductname());
        this.userOrder = userOrder;
    }
   public void addToBasket(shoppingCartLocal cart){
       cart.addItem(this.getProduct().getProductname(), Integer.parseInt(userOrder));
   }
   
    public UserOrder(Group2products product) {
        this.product = product;
        
    }
}
