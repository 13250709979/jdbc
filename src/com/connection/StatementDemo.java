package com.connection;

import java.sql.*;

public class StatementDemo {
    public static void main(String[] args){
        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");

            statement=connection.createStatement();
            String id="8 OR TRUE";
            String sql="DELETE from book WHERE id="+id;
            statement.execute(sql);
            for (int i=23;i<28;i++){
                 String insertSql = "insert into book (id,bookname,isbn,price,stock) values("+i+",'boo','bb',45,25)";
                 statement.executeUpdate(insertSql);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } if (statement!=null) {
            try {
                statement.close();
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
