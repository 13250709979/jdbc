package sorm.util;

import sorm.bean.ColumnInfo;
import sorm.bean.JavaFieldGetSet;
import sorm.bean.TableInfo;
import sorm.core.DBManager;
import sorm.core.MySqlTypeConvertor;
import sorm.core.TableContext;
import sorm.core.TypeConvertor;

import javax.lang.model.element.NestingKind;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装了生成java文件（源代码）常用的操作
 */
public class JavaFileUtils {

    /**
     * 根据字段信息生成Java属性信息：var username --> private String username 以及相应的set和get方法
     * @param columnInfo：字段信息
     * @param typeConvertor: 类型转换器
     * @return
     */
    public static JavaFieldGetSet
        createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor typeConvertor){

        JavaFieldGetSet javaFieldGetSet=new JavaFieldGetSet();

        /**
         * 属性的源码信息：private int userId;
         */
        String javaFieldType=typeConvertor.databaseType2JavaType(columnInfo.getDataType());
        javaFieldGetSet.setFieldInfo("\tprivate "+javaFieldType+" "+columnInfo.getName()+";\n");

        /**
         * get方法的源码信息:public int getUserId(){return UserId;}
         */
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append
                ("\tpublic "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"(){\n");
        stringBuilder.append("\t\treturn "+columnInfo.getName()+";\n");
        stringBuilder.append("\t}\n");
        javaFieldGetSet.setGetInfo(stringBuilder.toString());

        /**
         * set方法的源码信息:public void setUserId(int id){this.userId=id;}
         */
        StringBuilder builder=new StringBuilder();
        builder.append
                ("\tpublic void set"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"("+javaFieldType+" "+columnInfo.getName()+"){\n");
        builder.append("\t\tthis."+columnInfo.getName()+"="+columnInfo.getName()+";\n");
        builder.append("\t}\n");
        javaFieldGetSet.setSetInfo(builder.toString());
        return javaFieldGetSet;
    }

    /**
     *
     * @param tableInfo
     * @param typeConvertor
     * @return
     */
    public static String createJavaSrc(TableInfo tableInfo,TypeConvertor typeConvertor){

        /**
         * java对象所有的字段以及set、get方法
         */
        Map<String,ColumnInfo> map=tableInfo.getColumns();
        List<JavaFieldGetSet> javaFieldGetSetList=new ArrayList<JavaFieldGetSet>();
        for (ColumnInfo columnInfo:map.values()){
            javaFieldGetSetList.add(createFieldGetSetSRC(columnInfo,typeConvertor));
        }

        StringBuilder stringBuilder=new StringBuilder();
        /**
         * java对象package：package sorm.util;
         */
        stringBuilder.append("package "+ DBManager.getConfiguration().getPoPackage()+";\n\n");

        /**
         * java对象import：import java.util.ArrayList;
         */
        stringBuilder.append("import java.util.*;\n");
        stringBuilder.append("import java.sql.*;\n\n");

        /**
         * java对象class:public class JavaFileUtils
         */
        stringBuilder.append("public class "+StringUtils.firstChar2UpperCase(tableInfo.getTname())+"{\n\n");

        /**
         * java对象属性列表
         */
        for (JavaFieldGetSet javaFieldGetSet:javaFieldGetSetList){
            stringBuilder.append(javaFieldGetSet.getFieldInfo());
        }
        stringBuilder.append("\n");

        /**
         * java对象属性set方法
         */
        for (JavaFieldGetSet javaFieldGetSet:javaFieldGetSetList){
            stringBuilder.append(javaFieldGetSet.getSetInfo());
        }

        /**
         * java对象属性get方法
         */
        for (JavaFieldGetSet javaFieldGetSet:javaFieldGetSetList){
            stringBuilder.append(javaFieldGetSet.getGetInfo());
        }

        stringBuilder.append("\n}");
        return stringBuilder.toString();

    }

    public static void createJavaPOFile(TableInfo tableInfo,TypeConvertor typeConvertor){

        String string=createJavaSrc(tableInfo,typeConvertor);
        BufferedWriter bufferedWriter=null;
        String srcPath=DBManager.getConfiguration().getSrcPath()+"\\";
        String packagePath=DBManager.getConfiguration().getPoPackage().replace(".","/");
        File file=new File(srcPath+packagePath);
        System.out.println(file.getAbsoluteFile());

        if (!file.exists()){
            file.mkdir();
        }
        try {
            bufferedWriter=new BufferedWriter
                    (new FileWriter(file.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
            int length=string.length();
            bufferedWriter.write(string,0,length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (bufferedWriter!=null)
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Map<String,TableInfo> map= TableContext.tableMap;

        for (TableInfo tableInfo:map.values()){
            createJavaPOFile(tableInfo,new MySqlTypeConvertor());
        }
    }
}
