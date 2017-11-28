package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static consts.PageConst.*;
/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(LOGIN) != null){
            request.getSession().invalidate();
        }
        response.sendRedirect(BACK_TO_INDEX_VIEW);
    }
}
