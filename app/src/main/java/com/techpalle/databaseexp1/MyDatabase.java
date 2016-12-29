package com.techpalle.databaseexp1;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by manasranjan on 12/28/2016.
 *///2=create data base java file separatlly which act as controller
    public class MyDatabase {
    //5step:declare required variable
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase;//for doing dml operations
    //step 6  create object for myhelper variable by taking an constructor
    public  MyDatabase(Context c)
    {
        myHelper=new MyHelper(c,"techpalle.db",null,1);
    }
    //step7 create sqlite object using open method
    public  void open(){
        sqLiteDatabase=myHelper.getWritableDatabase();
    }
    //step8 perform DML operation
    public void insertStudent(String name,String course){
        ContentValues cv=new ContentValues();
        cv.put("sname",name);
        cv.put("scourse",course);
        sqLiteDatabase.insert("student",null,cv);
    }
    //keep update and delete()on hold
    public Cursor queryStudent(){
        Cursor c=null;
        //q1-read all student details
        c=sqLiteDatabase.query("student",null,null,null,null,null,null,null);

        return c;
    }
    //step9=close database
    public void close(){
        sqLiteDatabase.close();
    }
    //3-create inner helper class for DDL operations
    private class MyHelper extends SQLiteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //step4:create all required tables in this method
            //during creating sql command your have to also give semicolon inside double code
            db.execSQL("create table student(_id integer primary key,sname text,scourse text);");

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

