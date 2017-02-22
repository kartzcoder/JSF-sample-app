/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Group2users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author karthik
 */
@Stateless
public class Group2usersFacade extends AbstractFacade<Group2users> {

    @PersistenceContext(unitName = "Group2Ver0.3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Group2usersFacade() {
        super(Group2users.class);
    }
    
}
