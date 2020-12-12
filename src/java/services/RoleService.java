/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author 751682
 */
public class RoleService {
    
     /**
     * get all the users in the database that can be accessed only by system admin account
     * @return List of all users in database
     * @throws Exception 
     */
    public List<Role> getAll() throws Exception{
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    /**
     * get the user from their email
     * @param email to get the user
     * @return user with the email entered
     * @throws Exception 
     */
    public Role get(int roleId) throws Exception {
        RoleDB roleDB = new RoleDB();
        
        try {
            Role role = roleDB.get(roleId);
            return role;
        } catch (Exception e){
            
        }
        return null;
    }
}
