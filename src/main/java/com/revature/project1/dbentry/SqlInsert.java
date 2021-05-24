package com.revature.project1.dbentry;

import com.revature.project1.annotations.Column;
import com.revature.project1.annotations.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlInsert extends SqlCrud{
    public SqlInsert() {
        action = "insert into";
    }

    public void insertNewObject(Object obj) throws IllegalAccessException {

        //Gets the class of the obj and uses the annotation to get the table name
        Class<?> clazz = obj.getClass();
        Annotation[] classAnnotation = clazz.getAnnotations();
        String tableName = "";
        for(Annotation annotation : classAnnotation){
            if (annotation.annotationType().getSimpleName().equals("Entity")) {
                Entity test = (Entity) annotation;
                tableName = test.name();
                System.out.println("Table name " + tableName);
            }

        }

        //Get all the fields in the pojo
        Field[] declaredClassFields = clazz.getDeclaredFields();
        System.out.println("Field 1 name " + declaredClassFields[1].getName());

        //Use the field annotations and get all the column names and values and put them in their own ArrayLists
        ArrayList<String> columnNames = new ArrayList<>();
        ArrayList<String> columnValues = new ArrayList<>();
        for(Field fields: declaredClassFields){
            Annotation[] fieldAnnotations = fields.getAnnotations();

            for (Annotation anno: fieldAnnotations){
                String annoName = anno.annotationType().getSimpleName();
                if(annoName.equals("Column")){
                    columnNames.add(fields.getName());
                    fields.setAccessible(true);
                    columnValues.add( fields.get(obj).toString());
                    fields.setAccessible(false);
                }
            }
        }

        //Create the start of sql string and adding table name
        String sqlInsert = "Insert into ";
        sqlInsert += tableName + " (";

        //Adds the column names and column values to the sql string.
        sqlInsert += String.join(",", columnNames) + ")";
        sqlInsert += " values (" + String.join(",", columnValues) + ")";


        System.out.println("Sql String: " + sqlInsert);

        //Create key pairs of annotation and fields x
        //Use a stream to filter for table name
        //Use a stream to filter for all column names
    }

    //Wezley's method to print all members
    private void printMembers(Object[] members, String memberType) {
        if (members.length != 0) {
            System.out.println("\t" + memberType + " on class: ");
            for (Object o : members) {
                System.out.println("\t\t- " + o);
            }
        }
    }
}
