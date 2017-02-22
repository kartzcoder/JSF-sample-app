/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "GROUP2USERS", catalog = "", schema = "APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group2users.findAll", query = "SELECT g FROM Group2users g"),
    @NamedQuery(name = "Group2users.findById", query = "SELECT g FROM Group2users g WHERE g.userID = :userID"),
    @NamedQuery(name = "Group2users.findByError", query = "SELECT g FROM Group2users g WHERE g.error = :error"),
    @NamedQuery(name = "Group2users.findByErrorInt", query = "SELECT g FROM Group2users g WHERE g.errorInt = :errorInt"),
    @NamedQuery(name = "Group2users.findByPassword", query = "SELECT g FROM Group2users g WHERE g.password = :password"),
    @NamedQuery(name = "Group2users.findByProfile", query = "SELECT g FROM Group2users g WHERE g.profile = :profile"),
    @NamedQuery(name = "Group2users.findByUsername", query = "SELECT g FROM Group2users g WHERE g.username = :username"),
    @NamedQuery(name = "Group2users.findByUsertype", query = "SELECT g FROM Group2users g WHERE g.usertype = :usertype")})
public class Group2users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    @JoinColumn(name = "GROUP2PO", referencedColumnName = "USERID")
//    @ManyToOne(optional = false)
    private Long userID;
    private String error;
    private Integer errorInt;
    private String password;
    @Size(max = 10000)
    @Column(name = "PROFILE", length = 10000)
    private String profile;
    
    @Size(max = 255)
    @Column(name = "USERTYPE", length = 255)
    private String usertype;

    public Group2users() {
    }

    public Group2users(Long id) {
        this.userID = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getErrorInt() {
        return errorInt;
    }

    public void setErrorInt(Integer errorInt) {
        this.errorInt = errorInt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userID fields are not set
        if (!(object instanceof Group2users)) {
            return false;
        }
        Group2users other = (Group2users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Group2users[ id=" + userID + " ]";
    }
    
}
