package controller;

import model.Message;
import model.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "SendMessage", urlPatterns = {"/sendmessage"},
        initParams = {@WebInitParam(name = "MEMBER_VIEW", value = "member.jsp")})
public class SendMessage extends HttpServlet {
    private String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        Message message = new Message();
        message.setUsername(username);

        String blabla = request.getParameter("blabla");

        if (blabla != null && blabla.length() != 0){
            if (blabla.length()<140){
                message.setTxt(blabla);
                message.setDate(new Date());
                userService.addMessage(message);

            }else {
                request.setAttribute("blabla", blabla);
            }
        }

        List<Message> messages = userService.getMessage(message);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher(MEMBER_VIEW).forward(request,response);


    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
