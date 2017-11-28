package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

import static consts.PageConst.*;

/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Message", urlPatterns = {"/sendmessage"})
public class Message extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(LOGIN) == null){
            response.sendRedirect(BACK_TO_INDEX_VIEW);
            return;
        }

        String blabla = request.getParameter(WORDS_BEYOND_LIMIT);
        if (blabla != null && blabla.length() != 0){
            if (blabla.length()<140){
                String username = (String)request.getSession().getAttribute(LOGIN);
                addMessage(username, blabla);
                response.sendRedirect(MEMBER_VIEW);
            }else {
                request.getRequestDispatcher(MEMBER_VIEW).forward(request,response);
            }
        }else {
            response.sendRedirect(MEMBER_VIEW);
        }




    }

    private void addMessage (String username, String blabla) throws IOException {
        String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));   //utf-8
        writer.write(blabla);
        writer.close();
    }


//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
