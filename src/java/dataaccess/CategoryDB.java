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
import models.User;

/**
 * CategoryDB is used to manage Categories, done by system admin
 * It has the ability to view the list of categories, add categories, edit a category name
 * There is no functionality to delete a category
 * @author 751682
 */
public class CategoryDB {
    
    /**
     * to get a category based on category id
     * @param id: category id to be found
     * @return Category that is found from the id
     * @throws Exception 
     */
    public Category get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Category category=em.find(Category.class, id);
            return category;
        }finally{
            em.close();
        }
    }
    
    /**
     * to get all categories, to view all the list
     * @return list of categories in the database
     * @throws Exception 
     */
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        }finally{
            em.close();
        }
    }
    
    /**
     * to add categories
     * @param category to be added
     * @throws Exception 
     */
    public void insert(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans= em.getTransaction();
        
        try{
            trans.begin();
            em.persist(category);
            em.merge(category);
            trans.commit();
        }catch (Exception e){
            trans.rollback();
        }finally {
            em.close();
        }
    }
    
    /**
     * to update or edit a category name
     * @param category to be edited
     * @throws Exception 
     */
    public void update(Category category) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans= em.getTransaction();
        
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
}
