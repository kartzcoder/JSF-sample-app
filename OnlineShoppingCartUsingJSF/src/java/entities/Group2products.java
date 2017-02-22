/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import JSFBeans.AdministratorBean;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karthik
 */
@Entity
@Table(name = "GROUP2PRODUCTS", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group2products.findAll", query = "SELECT g FROM Group2products g"),
    @NamedQuery(name = "Group2products.findById", query = "SELECT g FROM Group2products g WHERE g.productID = :productID"),
    @NamedQuery(name = "Group2products.findByProductname", query = "SELECT g FROM Group2products g WHERE g.productname = :productname"),
    @NamedQuery(name = "Group2products.findByQuantity", query = "SELECT g FROM Group2products g WHERE g.quantity = :quantity")})
public class Group2products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRODUCTID")
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long productID;
    @Size(max = 255)
    @Column(name = "PRODUCTNAME", length = 255)
    private String productname;
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Group2products() {
    }

    public Group2products(Long id) {
        this.productID = id;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the productID fields are not set
        if (!(object instanceof Group2products)) {
            return false;
        }
        Group2products other = (Group2products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Group2products[ id=" + productID + " ]";
    }
}
