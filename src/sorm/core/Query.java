package sorm.core;

import java.util.List;

/**
 * Query接口，负责查询（对外提供服务的核心类）
 */
@SuppressWarnings("")
public interface Query {

    /**
     * 直接执行一个DML语句
     * @param sql
     * @param params
     * @return
     */
    public int executeDML(String sql,Object[] params);

    /**
     * 将一个对象存储到数据库中
     * @param obj
     */
    public void insert(Object obj);

    /**
     * 将一个对象直接删除
     * @param clazz
     * @param id
     * @return
     */
    public int delete(Class clazz,Object id);


    /**
     * 将一个对象直接删除
     * @param obj
     */
    public void delete(Object obj);

    /**
     * 更新对象对应的记录，并且只能更新指定的字段的值
     * @param obj
     * @param dieldNames
     * @return
     */
    public int update(Object obj,String[] dieldNames);

    /**
     * 查询返回多行记录
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public List queryRows(String sql,Class clazz,Object[] params);

    /**
     * 查询返回一行记录
     * @param sql
     * @param params
     * @return
     */
    public Object queryUniqueRow(String sql,Object[] params);

    /**
     * 查询返回一个值
     * @param sql
     * @param params
     * @return
     */
    public Object queryValue(String sql,Object[] params);

    /**
     * 查询返回一个值
     * @param sql
     * @param params
     * @return
     */
    public Number queryNumber(String sql,Object[] params);
}
