package sorm.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装jdbc查询常用的操作
 */
public class JDBCUtils {
    public static void handleParams(PreparedStatement preparedStatement,Object[] params){
        if (params!=null){
            for (int i=0;i<params.length;i++){
                try {
                    preparedStatement.setObject(i+1,params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
