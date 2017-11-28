package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;

import static consts.PageConst.*;
/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Delete", urlPatterns = {"/delete"})
public class Delete extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(LOGIN) == null){
            response.sendRedirect(BACK_TO_INDEX_VIEW);
            return;
        }
        String username = (String)request.getSession().getAttribute(LOGIN);
        String date = request.getParameter(MESSAGE);
        File file = new File(USERS+"/"+username+"/"+date+".txt");
        if (file.exists()){
            file.delete();
        }
        response.sendRedirect(MEMBER_VIEW);
    }
}
