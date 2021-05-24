package com.revature.project1.dbentry;

import com.revature.project1.annotations.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SqlCrud {
    protected Object obj;
    protected Entity entity;
    protected String tableName;
    protected String condition;
    protected String statement;
    protected String action;
    protected HashMap<String, Object> data;
    protected Class<?> clazz;
    protected ArrayList<String> columnNames = new ArrayList<>();
    protected ArrayList<String> columnValues = new ArrayList<>();

    public void setStatement(Object obj) throws IllegalAccessException {
        this.obj = obj;
        setClazz();
        setTableName();
        setColumnNamesAndValues();

        // prepares the corresponding SQL statements
        switch (action) {
            case "insert into":
                String s1 = ""; // "column1, column2, column3, ..."
                String s2 = ""; // "value1, value2, value3, ..."
                for (int i = 0; i < columnNames.size()-1; i++) {
                    s1 += columnNames.get(i);
                    s2 += columnValues.get(i);
                    if (i < columnNames.size()-2) {
                        s1 += ", ";
                        s2 += ", ";
                    }
                }
                //statement = "{action} {tableName} ({columnNames}) values ({columnValues})"; // insert format
                statement = String.format("%s %s (%s) values (%s)", action, tableName, s1, s2);
                break;
            case "select":
                //statement = "{action} {items} from {tableName} where ({condition})"; // select format
                statement = String.format("%s * from %s where (%s)", action, tableName, condition);
                break;
            case "update":
                String s = ""; // "column1=value1, column2=value2, ..."
                for (int i = 0; i < columnNames.size()-1; i++) {
                    s += columnNames.get(i) + "=" + columnValues.get(i);
                    if (i < columnNames.size()-2) {
                        s += ", ";
                    }
                }
                //statement = "{action} {tableName} set {columnName=columnValues} where ({condition})"; // update format
                statement = String.format("%s %s set %s where (%s)", action, tableName, s, condition);
                break;
            case "delete from":
                //statement = "{action} {tableName} where ({condition})"; // delete format
                statement = String.format("%s %s where (%s)", action, tableName, condition);
                break;
            default:
                System.out.println("Invalid action!");
        }
    }

    public void setStatement(Object obj, String condition) throws IllegalAccessException {
        this.condition = condition;
        setStatement(obj);
    }

    public boolean setTableName() {
        //Gets the class of the obj and uses the annotation to get the table name
        Class<?> clazz = obj.getClass();
        for (Annotation annotation : clazz.getAnnotations()) {
            if (!annotation.annotationType().getSimpleName().equals("Entity")) {
                return false;
            } else {
                entity = (Entity) annotation;
                tableName = entity.name();
            }
        }
        return true;
    }

    public void setClazz() {
        clazz = obj.getClass();
    }

    public void setColumnNamesAndValues() throws IllegalAccessException {
        //Get all the fields in the pojo
        for (Field fields : clazz.getDeclaredFields()) {

            for (Annotation anno: fields.getAnnotations()){
                if(anno.annotationType().getSimpleName().equals("Column")){
                    columnNames.add(fields.getName());
                    fields.setAccessible(true);
                    columnValues.add(fields.get(obj).toString());
                    fields.setAccessible(false);
                }
            }
        }
    }

    public String getStatement() {
        System.out.println("SQL -- " + action.toUpperCase() + ": " + statement);
        return statement;
    }

    public String getTableName() {
        return tableName;
    }

    public Class<?> getClazz() {
        return clazz;
    }


}
