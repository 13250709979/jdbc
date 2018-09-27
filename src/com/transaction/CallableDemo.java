package com.transaction;

import java.sql.*;

public class CallableDemo {
    public static void main(String[] args){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");

            connection.setAutoCommit(false);

            String sql="insert into book values(?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            preparedStatement.setString(2,"ddn");
            preparedStatement.setString(3,"nnv");
            preparedStatement.setInt(4,20);
            preparedStatement.setInt(5,14);
            preparedStatement.execute();

            System.out.println("第一个");

            sql="insert into book values(?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"ddn");
            preparedStatement.setString(3,"nnv");
            preparedStatement.setInt(4,20);
            preparedStatement.setInt(5,14);
            System.out.println("xx");
            preparedStatement.execute();
            System.out.println("第二个");
            connection.commit();
            System.out.println("第三个");

        } catch (ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if (set!=null){
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
