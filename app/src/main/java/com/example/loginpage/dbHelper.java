package com.example.loginpage;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="LoginDB";
    public static final int version=1;
    public static final String TABLE_NAME="LoginTable";
    public static final String USERID="Username";//
    public static final String PASSWORD="Password";
    public static final String SNO="Sno";

    public dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + SNO + " INTEGER , "+  USERID + " text PRIMARY KEY, " + PASSWORD + " text " + " ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertDetails(int sno,String username, String pwd, String cpwd){
//        sno;
        SQLiteDatabase db=this.getWritableDatabase();
        if (pwd.equals(cpwd)){
            ContentValues values=new ContentValues();
            values.put(SNO,sno++);
            values.put(USERID,username);
            values.put(PASSWORD,pwd);
            db.insert(TABLE_NAME,null,values);
            return true;
        }
        else{
            return false;
        }
    }
     public Boolean checkUsername(String userid){
         SQLiteDatabase db=this.getReadableDatabase();
         Cursor cursor=db.rawQuery("Select * from "+ TABLE_NAME +" where "+ USERID +" =? ", new String[] {userid});
         if(cursor.getCount()>0)
             return true;
         else
             return false;
    }
     public int fetchContact(String userid,String pwd){

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("Select * from "+ TABLE_NAME, null); //cursor points to the first row of the table
        //or below code can also be used to check if the username entered by user exists or not
//        Cursor cursor=db.rawQuery("Select * from "+ TABLE_NAME + " where " + USERID + " =? and " + PASSWORD + " =? ", new String[] {userid,pwd});
        //selectionArgs is used to pass condition to fetch data.

        //using while loop to iterate table and fetch data through cursor.
        ArrayList<UserDetailsModel> arrContacts =new ArrayList<>();
        int f=0;
        while(cursor.moveToNext()) {                                          //moveToNext returns boolean value.

            UserDetailsModel model = new UserDetailsModel();
                                                                            //0 is the index of First column in the table
            model.username=cursor.getString(1);
            model.password=cursor.getString(2);
            if ((model.username.equals(userid)) && (model.password.equals(pwd))) {
                f = 1;
            }
        }
        return f;
    }
}
