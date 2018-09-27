package sorm.core;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import sorm.bean.ColumnInfo;
import sorm.bean.TableInfo;
import sorm.util.JavaFileUtils;
import sorm.util.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成表结构
 */
public class TableContext {
    /**
     * 表名为Key，表信息对象为value
     */
    public static Map<String,TableInfo> tableMap=new HashMap<String,TableInfo>();

    /**
     * 将po的Class对象和表信息对象关联起来，便于重用
     */
    public static Map<Class,TableInfo> poClassTableMap =new HashMap<Class, TableInfo>();

    private TableContext(){
    }

    static {
        try {
            Connection connection=DBManager.getConnection();
            DatabaseMetaData databaseMetaData=connection.getMetaData();
            ResultSet tableRet=databaseMetaData.getTables(null,"%","%",new String[]{"Table"});
            /**
             * 查询表
             */
            while (tableRet.next()){
                String tableName= (String) tableRet.getObject("TABLE_NAME");
                TableInfo tableInfo=new TableInfo(tableName, new HashMap<String,ColumnInfo>(), new ArrayList<ColumnInfo>());
                tableMap.put(tableName,tableInfo);

                /**
                 * 查询表字段
                 */
                ResultSet set=databaseMetaData.getColumns(null,"%",tableName,"%");
                while (set.next()){
                    ColumnInfo columnInfo=new ColumnInfo(set.getString("COLUMN_NAME"),set.getString("TYPE_NAME"),0);
                    tableInfo.getColumns().put(set.getString("COLUMN_NAME"),columnInfo);
                }
                /**
                 * 查询主键
                 */
                ResultSet resultSet=databaseMetaData.getPrimaryKeys(null,"%",tableName);
                while(resultSet.next()){
                    ColumnInfo columnInfo2=tableInfo.getColumns().get(resultSet.getObject("COLUMN_NAME"));
                    columnInfo2.setKeyType(1);
                    tableInfo.getPriKeys().add(columnInfo2);
                }

                if (tableInfo.getPriKeys().size()>0){
                    tableInfo.setOnlyPrikey(tableInfo.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        updateJavaPOFile();
        loadPOTables();
    }

    public static void updateJavaPOFile(){
        Map<String,TableInfo> map= TableContext.tableMap;
        for (TableInfo tableInfo:map.values()){
            JavaFileUtils.createJavaPOFile(tableInfo,new MySqlTypeConvertor());
        }
    }

    public static void loadPOTables(){
        for (TableInfo tableInfo:tableMap.values()){
            try {
                Class clazz= Class.forName(DBManager.getConfiguration().getPoPackage()+"."+ StringUtils.firstChar2UpperCase(tableInfo.getTname()));
                poClassTableMap.put(clazz,tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Map<String,TableInfo> tableInfoMap=TableContext.tableMap;
        System.out.println(tableInfoMap);
    }
}
