/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.CategoryService;
import services.InventoryService;

/**
 *
 * @author 751682
 */
public class InventoryServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        AccountService as = new AccountService();
        CategoryService cs = new CategoryService();
        InventoryService is = new InventoryService();
        
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("email");
        if (email==null){
            response.sendRedirect("login");
            return;
        }
               
        try{
            User user = as.get(email);
            List<Item> items= user.getItemList();
            List<Category> categories=cs.getAll();
            request.setAttribute("user", user);
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            session=request.getSession();
        }
        catch (Exception e){
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        CategoryService cs = new CategoryService();       
        
        //get all values entered during the session
        String action = request.getParameter("action");
        String itemID = request.getParameter("itemID");   //item ID
        String itemName= request.getParameter("itemname"); //item name
        String email= request.getParameter("useremail"); //user's email
        String price = request.getParameter("price"); //item's price
        String catID = request.getParameter("category"); //item's category      
        int itemId; //parsing itemID to int
        
        if (action !=null){
            try {             
                if (itemID==null || itemID.isEmpty()){ //if current session doesn't have item ID, then get the value from previous session
                    itemID = (String) session.getAttribute("itemID");   
                }
                
                itemId = Integer.parseInt(itemID);   
                Item item = is.get(itemId);
                
                if (action.equals("edit")){    //to edit the item from list              
                    List<Category> categories=cs.getAll();
                    request.setAttribute("categories", categories);
                    session.setAttribute("editItem", item);
                    response.sendRedirect("inventory");
                    return;
                }
                else if (action.equals("delete")) { //to delete item from list
                    is.delete(itemId);
                    response.sendRedirect("inventory");
                    return;
                }
                                            
                double itemPrice = Double.parseDouble(price);
                
                if (catID==null || catID.isEmpty()){
                    catID="1";
                }
                int categoryID = Integer.parseInt(catID);  
                Category category = cs.get(categoryID);
                User user= as.get(email); //get user from email 
                            
                if (action.equals("add")){ //adding a new item to the list               
                    is.insert(itemName, itemPrice, category, user);
                    List<Item> items=user.getItemList();
                    List<Category> categories=cs.getAll();
                    request.setAttribute("categories", categories);
                    request.setAttribute("items", items);
                }
                else if (action.equals("save")){ //updating the item info from list and save it back
                    is.update(itemId, itemName, itemPrice, categoryID);
                    List<Item> items=user.getItemList();
                    List<Category> categories=cs.getAll();
                    request.setAttribute("categories", categories);
                    request.setAttribute("items", items);
                }
                else if (action.equals("update")){ //to update user's account information
                    session.setAttribute("useremail", email);
                    response.sendRedirect("account");
                    return;
                }        
            }
            catch (Exception e){
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, e);
            }         
        }       
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp")
            .forward(request, response);         
    }
}
