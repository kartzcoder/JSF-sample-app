/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karthik
 */
@Entity
@Table(name = "GROUP2PO", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group2po.findAll", query = "SELECT g FROM Group2po g"),
    @NamedQuery(name = "Group2po.findById", query = "SELECT g FROM Group2po g WHERE g.poID = :poID"),
    @NamedQuery(name = "Group2po.findByItems", query = "SELECT g FROM Group2po g WHERE g.items = :items"),
    @NamedQuery(name = "Group2po.findByUserid", query = "SELECT g FROM Group2po g WHERE g.userid = :userid"),
    @NamedQuery(name = "Group2po.findByUsername", query = "SELECT g FROM Group2po g WHERE g.username = :username")})
public class Group2po implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long poID;
    @Size(max = 255)
    @Column(name = "ITEMS", length = 255)
    private String items;
    @Column(name = "USERID")
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Group2po")
    private Long userid;
    @Size(max = 255)
    @Column(name = "USERNAME", length = 255)
    private String username;

    public Group2po() {
    }

    public Group2po(Long id) {
        this.poID = id;
    }

    public Long getPoID() {
        return poID;
    }

    public void setPoID(Long poID) {
        this.poID = poID;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poID != null ? poID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the poID fields are not set
        if (!(object instanceof Group2po)) {
            return false;
        }
        Group2po other = (Group2po) object;
        if ((this.poID == null && other.poID != null) || (this.poID != null && !this.poID.equals(other.poID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Group2po[ id=" + poID + " ]";
    }
    
}
