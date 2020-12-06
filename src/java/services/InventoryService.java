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
    
    public void insert(String itemname, double price, int categoryId, User owner) throws Exception {
        ItemDB itemDB = new ItemDB();
        
        Item item = new Item(0, itemname, price);
        Category category = new Category(categoryId);
        item.setCategory(category);
        item.setOwner(owner);
        
        itemDB.insert(item);
    }
    
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
}
