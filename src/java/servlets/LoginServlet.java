/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author 751682
 */
public class LoginServlet extends HttpServlet {


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
      
        HttpSession session = request.getSession();
         
       if (request.getParameter("logout")!=null){
            session.invalidate();
            String message="logout";
            request.setAttribute("message", message); 
            session = request.getSession();           
       }       
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
            forward(request,response);
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
      
        String action = request.getParameter("action");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        HttpSession session = request.getSession();
        
        AccountService as = new AccountService();
        User user = as.login(email, password);
        String message="";
        if (email.isEmpty() || email==null || password.isEmpty() || password == null){
            message="empty";
            session.setAttribute("message",message);
            response.sendRedirect("login");
            return;
        }
              
        if (action!= null){
            session.setAttribute("email", email);
            switch(action)
            {
                case "signin":
                    if (user==null){
                        message = "notfound";
                        session.setAttribute("message", message);
                        response.sendRedirect("login");
                       return;
                    }

                    else if (user.getActive()){
                        session.setAttribute("user",user);
                        if (user.getRole().getRoleId()==1){                               
                            response.sendRedirect("admin");
                            return;
                        }
                        else {
                            response.sendRedirect("inventory");                               
                            return;
                        }
                    }
                    else{
                        message="inactive";
                        session.setAttribute("message", message);
                        response.sendRedirect("login");
                        return;
                    }
                        
                case "register":
                    response.sendRedirect("registration");
                    return;
            }
        }
            
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);   
    }
}
