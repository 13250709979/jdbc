package sorm.core;

/**
 * 负责java数据类型和数据库类型的互相转换
 */
public interface TypeConvertor {

    /**
     * 将数据库类型转换为java的数据类型
     * @param columnType
     * @return
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java的数据类型转换为数据库类型
     * @param columnType
     * @return
     */
    public String JavaType2databaseType(String columnType);
}
