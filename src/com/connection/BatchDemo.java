package com.connection;

import java.sql.*;

public class BatchDemo {
    public static void main(String[] args){
        delete();
        //batchPrepareStatment();
        //batchStatment();
    }

    public static void batchPrepareStatment(){
        Connection connection=null;
        PreparedStatement statement=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");
            connection.setAutoCommit(false);

            long start=System.currentTimeMillis();
            String sql="insert into book values(?,?,?,?,?)";
            statement=connection.prepareStatement(sql);
            for (int i=10;i<200000;i++){
                statement.setInt(1,i);
                statement.setString(2,"ddn");
                statement.setString(3,"nnv");
                statement.setInt(4,20);
                statement.setInt(5,14);
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            long end=System.currentTimeMillis();
            System.out.println(end-start);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement!=null) {
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

    public static void batchStatment(){
        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");
            connection.setAutoCommit(false);
            statement=connection.createStatement();
            long start=System.currentTimeMillis();
            for (int i=100;i<200000;i++){
                String sql="insert into book(id,bookname,isbn,price,stock) values("+i+",'vvnnv','vvvv',78,85)";
                statement.addBatch(sql);
            }
            statement.executeBatch();
            connection.commit();
            long end=System.currentTimeMillis();
            System.out.println(end-start);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement!=null) {
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

    public static void delete(){

        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");

            statement=connection.createStatement();
            String id="9 OR TRUE";
            String sql="DELETE from book WHERE id="+id;
            statement.execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement!=null) {
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
}
