package com.crud.demo.cruds;

import com.crud.demo.Dao.UserDao;
import com.crud.demo.model.User;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/create")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private UserDao userDao;

    public HelloServlet(){
        this.userDao=new UserDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();

        switch(action){
            case "/new":
                showNewForm(request,response);
                break;
            case "/create":
                try {
                    createUser(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/select":
                try {
                    listUser(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateUser(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/delete":
                try {
                    deleteUser(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/selectbyid":
                userById(request, response);
                break;



        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }

    private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request,response);
    }
    private void userById(HttpServletRequest request,HttpServletResponse response){
        int id= Integer.parseInt(request.getParameter("id"));
        List<User> userById = (List<User>) userDao.selectUserById(id);
        request.setAttribute("userById", userById);
    }
    private void listUser (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDao.selectAllUsers();
        request.setAttribute("listUser", listUser);
//        Need to create Page
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward (request, response);
    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
        String name = request.getParameter("name");
        String sex = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String position = request.getParameter("position");
        String address = request.getParameter("address");
        int salary = Integer.parseInt(request.getParameter("salary"));
        User newUser = new User(name,sex,age,position,address,salary);
        response.sendRedirect("list");

    }
    private void updateUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String position = request.getParameter("position");
        int salary = Integer.parseInt(request.getParameter("sex"));
        User user = new User (id,age,address,position,salary);
        userDao.updateUser(user);
        response.sendRedirect("list");

    }

    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(id);
        response.sendRedirect("list");
    }
}