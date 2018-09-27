package sorm.bean;

/**
 * 封装java属性和get、set方法的源代码
 */
public class JavaFieldGetSet {
    /**
     * 属性的源码信息：private int userId;
     */
    private String fieldInfo;
    /**
     * get方法的源码信息:public int getUserId(){return UserId;}
     */
    private String getInfo;
    /**
     * set方法的源码信息:public void setUserId(int id){this.userId=id}
     */
    private String setInfo;

    public String getFieldInfo() {
        return fieldInfo;
    }
    public String getGetInfo() {
        return getInfo;
    }
    public String getSetInfo() {
        return setInfo;
    }
    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }
    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }
    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }

    public JavaFieldGetSet(){}

    public JavaFieldGetSet(String fieldInfo,String getInfo,String setInfo){
        super();
        this.fieldInfo=fieldInfo;
        this.setInfo=setInfo;
        this.getInfo=getInfo;
    }

    @Override
    public String toString() {
        System.out.println(fieldInfo);
        System.out.println(getInfo);
        System.out.println(setInfo);
        return super.toString();
    }
}
