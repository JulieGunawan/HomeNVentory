/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Role;


/**
 *
 * @author 751682
 */
public class RoleDB {
    /**
     * to get a role from role ID
     * @param id of the role to be searched
     * @return role specified by ID
     * @throws Exception 
     */
    public Role get(int id) throws Exception  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Role role = em.find(Role.class, id);
            return role;
        }finally{
            em.close();
        }
    }
    
    /**
     * get all the role listed in database
     * @return the list of all roles available
     * @throws Exception 
     */
    public List<Role> getAll() throws Exception  {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }       
    }
}
