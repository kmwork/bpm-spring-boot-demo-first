package ru.lanit.kostya.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class KostyaInputDataServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("text/html");
//        //return writer
//        PrintWriter out = response.getWriter();
//
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        out.close();
//
//        try {
//            login(username, password);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        response.sendRedirect("https://yandex.ru/");

//        String name = request.getParameter("name");
//        String address = request.getParameter("address");
//        String age = request.getParameter("age");
//
//        out.println("<HTML><HEAD><TITLE>Personnel Details</TITLE></HEAD><BODY>");
//        out.println(name + address + age);
//        out.println("</BODY></HTML>");
//        System.out.println("Finished Processing");
    }
}