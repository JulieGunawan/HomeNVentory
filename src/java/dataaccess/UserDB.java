/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;
import models.Role;
import models.User;

/**
 * UserDB is used for system admin to be able to manage users
 * It has ability to view all accounts, create a new account, delete an account, and edit an account
 * @author 751682
 */
public class UserDB {
    /**
     * method to view all users in the database
     * @return list of users in database
     * @throws Exception 
     */
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }
    
    /**
     * to get a user based on email address searched
     * @param email address of the user to be found
     * @return user found that has the email address
     * @throws Exception 
     */
    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        } finally{
            em.close();
        }
    }
    
    /**
     * to insert/add a user into database
     * @param user to be added
     * @throws Exception 
     */
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans=em.getTransaction();
        
        try {
          trans.begin();
          em.persist(user);
          em.merge(user);
          trans.commit();          
          
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    /**
     * to edit a user from database
     * @param user information after the update
     * @throws Exception 
     */
    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans=em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
            
        } catch (Exception e) {
            trans.rollback();
        } finally{
            em.close();
        }
    }
    
    /**
     * to delete an account/user
     * @param email of user to be deleted from database
     * @throws Exception 
     */
    public void delete(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans=em.getTransaction();
        
        try{
            User user = em.find(User.class, email);
            Role role = user.getRole();
            List<Item> itemList = user.getItemList();
            Item item;
            Category category ;
            role.getUserList().remove(user); //remove user from role's userList
            
            trans.begin();
              em.remove(em.merge(user));     
              em.merge(role);
              
              for (int i=0; i<itemList.size(); i++){
                  item = itemList.get(i);
                  category = item.getCategory(); //get category from the item then remove the item from itemList
                  category.getItemList().remove(item);
                  em.merge(category);
                  itemList.remove(i); //take the item off the itemList
                  em.remove(em.merge(item));
              }
             
              trans.commit();
        } catch (Exception e) {
          trans.rollback();
        }finally {
            em.close();
        }
    }
}
