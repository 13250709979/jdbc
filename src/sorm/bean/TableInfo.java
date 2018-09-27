package sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * 存储表结构信息
 */
public class TableInfo {
    /**
     * 表名字
     */
    private String tname;

    /**
     * 所有字段信息
     */
    private Map<String,ColumnInfo> columns;

    /**
     * 唯一主键
     */
    private ColumnInfo onlyPrikey;

    /**
     * 联合主键
     */
    private List<ColumnInfo> priKeys;

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }
    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }
    public ColumnInfo getOnlyPrikey() {
        return onlyPrikey;
    }
    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }
    public String getTname() {
        return tname;
    }
    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }
    public void setOnlyPrikey(ColumnInfo onlyPrikey) {
        this.onlyPrikey = onlyPrikey;
    }
    public void setTname(String tname) {
        this.tname = tname;
    }

    public TableInfo(String tname,Map map,ColumnInfo columnInfo){
        super();
        this.tname=tname;
        this.columns=map;
        this.onlyPrikey=columnInfo;
    }
    public TableInfo(String tname,Map map,List<ColumnInfo> priKeys){
        super();
        this.tname=tname;
        this.columns=map;
        this.priKeys=priKeys;
    }
    public TableInfo(){
    }
}
