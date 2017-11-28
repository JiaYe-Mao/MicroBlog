package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static consts.PageConst.*;

/**
 * Created by GaryMao on 11/27/2017.
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (checkUser(username, password)){
            request.getSession().setAttribute(LOGIN, username);
            request.getRequestDispatcher(MEMBER_VIEW).forward(request, response);

        } else {
            response.sendRedirect(BACK_TO_INDEX_VIEW);
        }
    }

    private boolean checkUser (String name, String password) throws IOException {
        for (String file : new File(USERS).list()){
            if(file.equals(name)){
                File profile = new File(USERS + "/" + name + "/profile");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(profile));
                    String password2 = null;
                    if ((password2=reader.readLine())!= null){
                        password2 = password2.split("\t")[1];
                        reader.close();
                        if (password2.equals(password)){
                            return true;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                return false;
            }
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
