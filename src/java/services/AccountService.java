/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 * UserService is used to manage user account
 * this class has ability to edit user's information once user successfully log in
 * @author 751682
 */
public class AccountService {
    
    /**
     * User login, takes in email and password of an account, check if the account exists in database, based on the email/password pair.
     * if it is, return the user, otherwise, notify the user that account doesn't exist, user is to register
     * @param email of the user to login into the system
     * @param password of the user to login into the system
     * @return 
     */
    public User login (String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
     
        } catch (Exception e) {
            
        }
            return null;
    }
    
    /**
     * get all the users in the database that can be accessed only by system admin account
     * @return List of all users in database
     * @throws Exception 
     */
    public List<User> getAll() throws Exception{
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    /**
     * get the user from their email
     * @param email to get the user
     * @return user with the email entered
     * @throws Exception 
     */
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            return user;
        } catch (Exception e){
            
        }
        return null;
    }
    
      /**
     * insert new user from registration into database
     * @param User the new user information
     * @param Role of new user, assigned to regular user, roleId=2, when new user is created through registration
     * @throws Exception 
     */
    public void insert (String email, String firstName, String lastName, String password) throws Exception {
        UserDB userDB = new UserDB();
        boolean active = true;
        User user = new User(email, active, firstName, lastName, password);
        Role role = new Role(2);
        
        user.setRole(role);
        userDB.insert(user);
    }
}
