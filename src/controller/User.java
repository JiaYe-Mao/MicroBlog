package controller;

import model.Message;
import model.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "User", urlPatterns = {"/user/*"},
            initParams = {@WebInitParam(name="USER_VIEW", value = "../user.jsp"),
                          @WebInitParam(name = "MEMBER_VIEW", value = "../member.jsp")})
public class User extends HttpServlet {
    private String USER_VIEW;
    private String MEMBER_VIEW;

    @Override
    public void init() throws ServletException {
        USER_VIEW = getServletConfig().getInitParameter("USER_VIEW");
        MEMBER_VIEW = getServletConfig().getInitParameter("MEMBER_VIEW");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentUsername = (String) request.getSession().getAttribute("username");
        String requestUsername = request.getPathInfo().substring(1);

        request.setAttribute("username", requestUsername);



        UserService userService = (UserService) getServletContext().getAttribute("userService");
        if (userService.isUsernameExisted(requestUsername)){
            Message username = new Message();
            username.setUsername(requestUsername);
            List<Message> messages = userService.getMessage(username);
            request.setAttribute("messages", messages);
            if (requestUsername.equals(currentUsername)){
                request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);
            }
        }

        request.getRequestDispatcher(USER_VIEW).forward(request,response);
    }
}
