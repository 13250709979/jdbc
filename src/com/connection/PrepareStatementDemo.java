package com.connection;

import java.sql.*;

public class PrepareStatementDemo {

    public static void main(String[] args) {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","18151815");
            String sql="insert into book values(?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,94);
            preparedStatement.setString(2,"ddn");
            preparedStatement.setString(3,"nnv");
            preparedStatement.setInt(4,20);
            preparedStatement.setInt(5,14);

            //结果集有数据返回：true，否则为false
            //System.out.println(preparedStatement.execute());

            //主要用于insert、update、delete操作，返回更新的行数
            System.out.println(preparedStatement.executeUpdate());

            //用于select操作，返回结果集ResultSet
            String selectSql="select * from book where id>?";
            preparedStatement=connection.prepareStatement(selectSql);
            preparedStatement.setInt(1,28);

            set=preparedStatement.executeQuery();
            while (set.next()){
                System.out.println(set.getRow());
                System.out.println(set.getInt(1)+":"+set.getString(2)+":"+set.getString(3));
            }
            int index=set.getRow();
            System.out.println(index);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
