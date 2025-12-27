package com.example.sql_masterclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ShopDB.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create CUSTOMERS Table
        String createTable = "CREATE TABLE Customers (" +
                "CustomerID INTEGER PRIMARY KEY, " +
                "CustomerName TEXT, " +
                "ContactName TEXT, " +
                "City TEXT, " +
                "Country TEXT)";
        db.execSQL(createTable);

        // Insert Mock Data (Professional Data)
        db.execSQL("INSERT INTO Customers VALUES (1, 'Alfreds Futterkiste', 'Maria Anders', 'Berlin', 'Germany')");
        db.execSQL("INSERT INTO Customers VALUES (2, 'Ana Trujillo Emparedados', 'Ana Trujillo', 'México D.F.', 'Mexico')");
        db.execSQL("INSERT INTO Customers VALUES (3, 'Antonio Moreno Taquería', 'Antonio Moreno', 'México D.F.', 'Mexico')");
        db.execSQL("INSERT INTO Customers VALUES (4, 'Around the Horn', 'Thomas Hardy', 'London', 'UK')");
        db.execSQL("INSERT INTO Customers VALUES (5, 'Berglunds snabbköp', 'Christina Berglund', 'Luleå', 'Sweden')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Customers");
        onCreate(db);
    }
}