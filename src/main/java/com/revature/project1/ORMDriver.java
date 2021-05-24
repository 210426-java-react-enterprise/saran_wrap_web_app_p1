package com.revature.project1;

import com.revature.project1.dbentry.*;
import com.revature.project1.models.AppUser;

public class ORMDriver {
    public static void main(String[] args) throws IllegalAccessException {

        Object userObj = new AppUser("gtomasel", "Passw0rd", "Email@mail.com", "Giancarlo", "Lastname", 23);
        SqlInsert insertTest = new SqlInsert();

        insertTest.insertNewObject(userObj);

        //INSERT
        insertTest.setStatement(userObj);
        insertTest.getStatement();

        //SELECT
        SqlSelect selectTest = new SqlSelect();
        selectTest.setStatement(userObj, "username=gtomasel, password=Passw0rd");
        selectTest.getStatement();

        //UPDATE
        SqlUpdate updateTest = new SqlUpdate();
        updateTest.setStatement(userObj, "username=gtomasel");
        updateTest.getStatement();

        //DELETE
        SqlDelete deleteTest = new SqlDelete();
        deleteTest.setStatement(userObj, "username=gtomasel");
        deleteTest.getStatement();

    }
}
