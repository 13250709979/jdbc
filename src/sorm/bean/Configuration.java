package sorm.bean;

/**
 * 管理配置信息
 */
public class Configuration {
    /**
     * 驱动类
     */
    private String driver;
    /**
     * 数据库Url
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String user;
    /**
     * 数据库密码
     */
    private String pwd;
    /**
     * 正在使用哪个数据库
     */
    private String usingDB;
    /**
     * 项目的源码路径
     */
    private String srcPath;
    /**
     * 扫描生成java类的包（po：Persistence Object持久化对象）
     */
    private String poPackage;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getDriver() {
        return driver;
    }
    public String getPoPackage() {
        return poPackage;
    }
    public String getPwd() {
        return pwd;
    }
    public String getSrcPath() {
        return srcPath;
    }
    public String getUsingDB() {
        return usingDB;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public void setPoPackage(String poPackage) {
        this.poPackage = poPackage;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUsingDB(String usingDB) {
        this.usingDB = usingDB;
    }
    public String getUrl() {
        return url;
    }


}
