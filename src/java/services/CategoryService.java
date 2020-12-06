/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author 751682
 */
public class CategoryService {
    
    /**
     * get all category from database
     * @return List of category stored in database
     * @throws Exception 
     */
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categories = categoryDB.getAll();
        return categories;
    }
    
    /**
     * get category based on category id
     * @param id: category ID that is searched
     * @return category from category id
     * @throws Exception 
     */
    public Category get(int id) throws Exception{
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(id);
        return category;
    } 
    
    /**
     * to add a new category, can only be done by admin
     * @param categoryName to be added into the database
     * @throws Exception 
     */
    public void insert(String categoryName) throws Exception{
        CategoryDB categoryDB = new CategoryDB();
        Category category = new Category(0, categoryName);
        categoryDB.insert(category); 
    }
    
    /**
     * to edit category, can only be done by admin 
     * @param categoryId to be updated
     * @param categoryName to be updated/editted
     * @throws Exception 
     */
    public void update(int categoryId, String categoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryId);
        category.setCategoryName(categoryName);
        
        categoryDB.update(category);
        
    }
}
