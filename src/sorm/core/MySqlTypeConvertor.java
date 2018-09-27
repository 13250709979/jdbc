package sorm.core;

/**
 * mysql数据类型和java数据类型的转换
 */
public class MySqlTypeConvertor implements TypeConvertor{
    /**
     * 将数据库类型转换为java的数据类型
     * @param columnType
     * @return
     */
    @Override
    public String databaseType2JavaType(String columnType) {
        if ("varchar".equalsIgnoreCase(columnType)
                ||"char".equalsIgnoreCase(columnType)
                ||"text".equalsIgnoreCase(columnType)){
            return "String";
        }else if("int".equalsIgnoreCase(columnType)
                ||"smallint".equalsIgnoreCase(columnType)
                ||"integer".equalsIgnoreCase(columnType)){
            return "Integer";
        }else if("bigint".equalsIgnoreCase(columnType)){
            return "Long";
        }else if("double".equalsIgnoreCase(columnType)
                ||"float".equalsIgnoreCase(columnType)){
            return "Double";
        }else if("clob".equalsIgnoreCase(columnType)){
            return "java.sql.CLob";
        }else if("blob".equalsIgnoreCase(columnType)){
            return "java.sql.BLob";
        }else if("date".equalsIgnoreCase(columnType)
                ||"datetime".equalsIgnoreCase(columnType)){
            return "java.sql.Date";
        }else if("time".equalsIgnoreCase(columnType)){
            return "java.sql.Time";
        }else if("timestamp".equalsIgnoreCase(columnType)){
            return "java.sql.Timestamp";
        }else if ("DECIMAL".equalsIgnoreCase(columnType)){
            return "java.math.BigDecimal";
        }else if ("TINYINT(1)".equalsIgnoreCase(columnType)){
            return "Byte";
        }

        return "Byte";
    }

    /**
     * 将java的数据类型转换为数据库类型
     * @param columnType
     * @return
     */
    @Override
    public String JavaType2databaseType(String columnType) {
        return null;
    }
}
