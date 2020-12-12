/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author 751682
 */
public class InventoryService {
    
    /**
     * to get item based on item ID
     * @param itemId
     * @return Item with the item ID
     * @throws Exception 
     */
    public Item get(int itemId) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemId);
        return item;
    }
    
    /**
     * to get all items stored in database
     * @return List of all items
     * @throws Exception 
     */
    public List<Item> getAll() throws Exception{
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll();
        
        return items;
    }
    
    /**
     * to insert a new item to user's list
     * @param itemname name of new item going to be added
     * @param price of new item going to be added 
     * @param categoryId of new item going to be added 
     * @param owner of the item
     * @throws Exception 
     */
    public void insert(String itemname, double price, Category category, User owner) throws Exception {
        ItemDB itemDB = new ItemDB();
        
        Item item = new Item(0, itemname, price);
        
        item.setCategory(category);
        item.setOwner(owner);
        category.getItemList().add(item);
        owner.getItemList().add(item);
        itemDB.insert(item);
    }
    
    /**
     * to edit the item from user's list
     * @param itemId item Id that is selected to be edited
     * @param itemname new name of edited item 
     * @param price new price of edited item
     * @param categoryId category ID of edited item
     * @throws Exception 
     */
    public void update(int itemId, String itemname, double price, int categoryId) throws Exception {
        ItemDB itemDB = new ItemDB();
        
        Item item = itemDB.get(itemId);
        item.setItemName(itemname);
        item.setPrice(price);
        Category category = item.getCategory();
        category.setCategoryId(categoryId);
        item.setCategory(category);
        
        itemDB.update(item);
              
    }
    
    /**
     * to delete selected item from user's list
     * @param itemId item ID to be deleted
     * @throws Exception 
     */
    public void delete(int itemId) throws Exception{
        ItemDB itemDB = new ItemDB();  
        Item item = itemDB.get(itemId);
        itemDB.delete(item);
    }
}
