/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Group2po;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author karthik
 */
@Stateless
public class Group2poFacade extends AbstractFacade<Group2po> {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Group2poFacade() {
        super(Group2po.class);
    }
    
}
