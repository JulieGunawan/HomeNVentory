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
import models.Item;
import models.Role;
import models.User;
import services.AccountService;
import services.InventoryService;

/**
 *
 * @author 751682
 */
public class AccountServlet extends HttpServlet {


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
        HttpSession session = request.getSession(); 
        //String action = (String) session.getAttribute("action");
        String action = request.getParameter("action");
        
        if (action!= null){ 
            if (action.equals("update")) {
                try{    
                    String email = (String) session.getAttribute("email");
                    User user = as.get(email);
                    request.setAttribute("user", user);
                   // session = request.getSession();
                }
                catch (Exception e){
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
                }
        //String action = request.getParameter("action");      
            }    
        }
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").
                forward(request, response);
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
        
        String newEmail = request.getParameter("email"); //original email before updated
        String userEmail = request.getParameter("originalemail"); //get the new email, if it's updated  
        String firstName = request.getParameter("firstName"); //get first name of user, if it's updated
        String lastName = request.getParameter("lastName"); //get last name of user if it's updated
        String password = request.getParameter("password"); //get the password if it's changed
        
        AccountService as = new AccountService();
        
        String action = request.getParameter("action");       
        
        //If cancel button is pressed, go back to inventory page
        if(action !=null && action.equalsIgnoreCase("cancel")){
            response.sendRedirect("inventory");
            return;
        }        
               
        try{
            if (action!= null && action.equals("deactivate")){   
                User deactUser = as.get(userEmail);
                String first = deactUser.getFirstName();
                String last = deactUser.getLastName();
                String pass = deactUser.getPassword();
                as.update(userEmail, false, first, last, pass);
                response.sendRedirect("login");
                return;
            }  
        } catch (Exception e){
             Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        User newUser=null; //store original information of user
        String message="";          
        
        if (newEmail == null){
            message ="Please enter your email";
            session.setAttribute("message", message);
            doGet(request, response);  
            return;
        }
           
        try {
            User originalUser = as.get(userEmail);
            newUser = as.get(newEmail);
            
            if(newUser==null){
                message ="Please fill all the form";
                session.setAttribute("message", message);
                doGet(request, response);
                return;
            }          
            
            if (action !=null && action.equals("save")){
                if(newUser.equals(originalUser)){                       
                      as.update(newEmail, true, firstName, lastName, password);
                    } else {
                        List<Item> itemList = originalUser.getItemList();
                        as.delete(originalUser.getEmail());
                        as.insert(newEmail, 2, firstName, lastName, password);
                        newUser = as.get(newEmail);
                        newUser.setItemList(itemList);
                    }
                    response.sendRedirect("inventory");
                    return;
            }
                       
        } catch (Exception e){
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
         
        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").
                forward(request, response);
    }

}
