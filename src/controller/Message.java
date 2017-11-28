package controller;

import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Message", urlPatterns = {"/sendmessage"},
        initParams = {@WebInitParam(name = "SUCCESS_VIEW", value = "member"),
                      @WebInitParam(name = "ERROR_VIEW", value = "member")})
public class Message extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String blabla = request.getParameter("blabla");
        if (blabla != null && blabla.length() != 0){
            if (blabla.length()<140){
                UserService userService = (UserService) getServletContext().getAttribute("userService");
                String username = (String)request.getSession().getAttribute("username");
                userService.addMessage(username, blabla);
                response.sendRedirect(SUCCESS_VIEW);
            }else {
                request.getRequestDispatcher(ERROR_VIEW).forward(request,response);
            }
        }else {
            response.sendRedirect(ERROR_VIEW);
        }




    }




//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
