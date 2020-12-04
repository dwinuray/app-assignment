package com.dwinuray.app_assignment.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dwinuray.app_assignment.models.Assignments;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME  = "db_assignment";
    private static final String TABLE   = "assignment";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATED_TABLE = "CREATE TABLE assignment (" +
                "id INTEGER PRIMARY KEY, type varchar(25), name varchar(150), description TEXT, date DATE, status varchar(10) )";

        db.execSQL(CREATED_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


    // insert
    public void onInsertAssignment(Assignments mdAssignment){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("id", mdAssignment.getId());
        value.put("type", mdAssignment.getType());
        value.put("name", mdAssignment.getName());
        value.put("description", mdAssignment.getDescription());
        value.put("date", mdAssignment.getDate());
        value.put("status", mdAssignment.getStatus());

        // do insert
        db.insert( TABLE, null, value );
    }

    // get data assignment
    public List<Assignments> getAllAssignment(){

        List<Assignments> dataModelList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if ( cursor.moveToFirst() ) {

            do {
                Assignments mdAssignment = new Assignments(null, null, null, null, null, null);
                mdAssignment.setId(cursor.getString(0));
                mdAssignment.setType(cursor.getString(1));
                mdAssignment.setName(cursor.getString(2));
                mdAssignment.setDescription(cursor.getString(3));
                mdAssignment.setDate(cursor.getString(4));
                mdAssignment.setStatus(cursor.getString(5));

                dataModelList.add(mdAssignment);
            } while ( cursor.moveToNext() );
        }

        db.close();
        return dataModelList;
    }
}
