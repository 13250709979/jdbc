package sorm.core;

import sorm.bean.ColumnInfo;
import sorm.bean.TableInfo;
import sorm.pojo.Book;
import sorm.util.JDBCUtils;
import sorm.util.ReflectUtils;

import java.sql.*;
import java.util.List;

/**
 * 负责针对MySql的
 */
public class MySqlQuery implements Query {
    /**
     * 直接执行一个DML语句
     *
     * @param sql
     * @param params
     * @return
     */
    @Override
    public int executeDML(String sql, Object[] params) {
        Connection connection=DBManager.getConnection();
        int count=0;
        PreparedStatement preparedStatement=null;
        try{
            preparedStatement=connection.prepareStatement(sql);
            /**
             * Sql设置参数
             */
            JDBCUtils.handleParams(preparedStatement,params);
            count=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 将一个对象存储到数据库中
     *
     * @param obj
     */
    @Override
    public void insert(Object obj) {

    }

    /**
     * 将一个对象直接删除
     *
     * @param clazz
     * @param id
     * @return
     */
    @Override
    public int delete(Class clazz, Object id) {
        /**
         * Sql语句:delete from mmall_cart where id=?
         */
        TableInfo tableInfo=TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo=tableInfo.getOnlyPrikey();
        String sql="delete from "+tableInfo.getTname()+" where "+columnInfo.getName()+"=?";

        return executeDML(sql,new Object[]{id});

}

    /**
     * 将一个对象直接删除
     *
     * @param obj
     */
    @Override
    public void delete(Object obj) {
        Class clazz=obj.getClass();
        TableInfo tableInfo=TableContext.poClassTableMap.get(clazz);
        ColumnInfo columnInfo=tableInfo.getOnlyPrikey();

        /**
         * 通过反射机制，调用属性对应的get方法，得到主键
         */
        Object priKeyValue=ReflectUtils.invokeGet(columnInfo.getName(),obj);
        delete(clazz,priKeyValue);
    }

    /**
     * 更新对象对应的记录，并且只能更新指定的字段的值
     *
     * @param obj
     * @param dieldNames
     * @return
     */
    @Override
    public int update(Object obj, String[] dieldNames) {
        return 0;
    }

    /**
     * 查询返回多行记录
     *
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        return null;
    }

    /**
     * 查询返回一行记录
     *
     * @param sql
     * @param params
     * @return
     */
    @Override
    public Object queryUniqueRow(String sql, Object[] params) {
        return null;
    }

    /**
     * 查询返回一个值
     *
     * @param sql
     * @param params
     * @return
     */
    @Override
    public Object queryValue(String sql, Object[] params) {
        return null;
    }

    /**
     * 查询返回一个值
     *
     * @param sql
     * @param params
     * @return
     */
    @Override
    public Number queryNumber(String sql, Object[] params) {
        return null;
    }

    public static void main(String[] args){
        Book book=new Book();
        book.setId(2);
        new MySqlQuery().delete(book);
    }
}
