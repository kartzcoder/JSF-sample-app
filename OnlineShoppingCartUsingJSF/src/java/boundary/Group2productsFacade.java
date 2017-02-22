/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Group2products;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author karthik
 */
@Stateless
public class Group2productsFacade extends AbstractFacade<Group2products> {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Group2productsFacade() {
        super(Group2products.class);
    }

    public List<Group2products> getProductbyName(String name) {

        // create named query and set parameter
        Query query = em.createNamedQuery("Group2products.findByProductname")
                .setParameter("productname", name);
        // return query result
        return query.getResultList();
    }

     public List<Group2products> getAllProduct() {

        // create named query and set parameter
        Query query = em.createNamedQuery("Group2products.findAll");
        // return query result
        return query.getResultList();
    }
    
}
