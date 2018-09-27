package sorm.bean;

/**
 * 封装表中一个字段的信息
 */
public class ColumnInfo {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段的数据类型
     */
    private String dataType;

    /**
     * 字段的键类型（普通键：0,主键：1,外键:2）
     */
    private int keyType;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getKeyType() {
        return keyType;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public ColumnInfo(){
    }
    public ColumnInfo(String name,String dataType,int keyType){
        super();
        this.name=name;
        this.dataType=dataType;
        this.keyType=keyType;
    }

}
