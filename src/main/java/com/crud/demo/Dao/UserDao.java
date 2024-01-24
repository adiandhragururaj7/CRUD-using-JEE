package com.crud.demo.Dao;

import com.crud.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private String url ="jdbc:mysql://localhost:3306/root?useSSL=false";
    private String jdbcUsername = "root";
    private String password = "123456";

    private static final String INSERT_USERS_SOL = "INSERT INTO users" + "(name, sex, age, position, address, salary) VALUES"
+" (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set address= ?,position= ?, salary =?, age =? where id = ?;";

//    id, name, sex, age, address, position, salary
    protected Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,jdbcUsername,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }

//    create user
    public void createUser(User user) throws SQLException {
        try(Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SOL)){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSex());
            preparedStatement.setInt(3,user.getAge());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setString(5,user.getPosition());
            preparedStatement.setInt(6,user.getSalary());
            preparedStatement.executeUpdate();
            System.out.println("Created the user");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    Update User
    public boolean updateUser(User user) throws SQLException{
        boolean rowUpdated;
        try(Connection con = getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(UPDATE_USERS_SQL);){
            preparedStatement.setInt(4,user.getAge());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getPosition());
            preparedStatement.setInt(7,user.getSalary());
            rowUpdated =preparedStatement.executeUpdate()>0;
            System.out.println("updated the user");

        }
        return rowUpdated;
    }

//    Read users
    public User selectUserById(int id){
        User user = null;
//        establishing a connection
        try(Connection con = getConnection();
//            create a connection statement
        PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID);){
        preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");
                user = new User(id,name,sex,age,address,position,salary);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
// Select all users
    public List<User> selectAllUsers(){
        List<User> users = new ArrayList<>();
//        establishing a connection
        try(Connection con = getConnection();
//            create a connection statement
            PreparedStatement preparedStatement = con.prepareStatement("SELECT_ALL_USERS");){


            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String namaiva = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");
                users.add(new User(id,namaiva,sex,age,address,position,salary));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

//    Delete User
    public boolean deleteUser(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection con = getConnection();
        PreparedStatement preparedStatement=con.prepareStatement("DELETE_USERS_SQL");){
        preparedStatement.setInt(1,id);
        rowDeleted = preparedStatement.executeUpdate()>0;
            System.out.println("Deleted the user");

        }
        return rowDeleted;
    }

}
