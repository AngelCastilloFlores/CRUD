package com.example.android.menudietas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.menudietas.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angelcastillo on 2/5/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;

    private static final String PRIMARY_KEY = " PRIMARY KEY ";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";
    private static final String COMMA_SEP = ",";

    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USER = "usuario";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private SQLiteDatabase db;


    //CREAR TABLA EN BD CON DIFERENTES CAMPOS
    public static final String TABLE_CREATE =
            "create table " + TABLE_NAME
                    + " (" +
                    COLUMN_ID + INTEGER_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
                    COLUMN_USER + TEXT_TYPE + COMMA_SEP +
                    COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_PASSWORD + TEXT_TYPE
                    + " )";

    //DB VERSION
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    /***********************************************************************************************
     * CRUD METHODS
     */

    /**
     * CREATE: Crea un nuevo usuario
     *
     * @param c
     */
    public void createContact(Contact c) {
        db = this.getReadableDatabase();

        ContentValues values = getContactValues(c);
        db.insert(TABLE_NAME, null, values);

        db.close();
    }



    /**
     * READ: Devuelve un contacto que cumple las condiciones que se pasan como parámetros
     * @param selection
     * @param selectionArgs
     * @return
     */
    public List<Contact> readRows(String selection, String[] selectionArgs) {

        List<Contact> result = new ArrayList<>();

        db = this.getReadableDatabase();

        //CAMPOS DEL REGISTRO CONTACTO
        String[] projection = {
                COLUMN_ID,
                COLUMN_USER,
                COLUMN_EMAIL
        };

        //DEVUELVE EL CONTACTO DE FORMA ASCENDENTE O DESCENTE
        String sortOrder =
                COLUMN_ID + " ASC";


        //DIFERENTES PARAMETROS PARA DEOLVER EL CONTACTO
        Cursor c = db.query(
                TABLE_NAME,                  // The table to query
                projection,                                // The columns to return
                selection,                                 // The columns for the WHERE clause
                selectionArgs,                             // The values for the WHERE clause
                null,                                      // don't group the rows
                null,                                      // don't filter by row groups
                sortOrder                                  // The sort order
        );

        //CURSOR
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                Contact contact = cursorToContact(c);
                result.add(contact);
                c.moveToNext();
            }
        }

        db.close();
        return result;
    }

    /**
     * READ: devuelve la contraseña de un usuario el cual se pasa su EMAIL por parámetro
     *
     * @param email
     * @return
     */
    public String readUserPasswordByEmail(String email) {
        db = this.getReadableDatabase();

        //SI NO ENCUENTRA = "NOT FOUND"
        String passWord = "not found";

        String[] projection = {
                COLUMN_PASSWORD
        };

        String selection = COLUMN_EMAIL + " = ?" ;

        String[] selectionArgs = {
                email
        };

        Cursor c = db.query(
                TABLE_NAME,                  // The table to query
                projection,                                // The columns to return
                selection,                                 // The columns for the WHERE clause
                selectionArgs,                             // The values for the WHERE clause
                null,                                      // don't group the rows
                null,                                      // don't filter by row groups
                null                                  // The sort order
        );

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                passWord = c.getString(0);
                c.moveToNext();
            }
        }

        db.close();

        return passWord;
    }


    /**
     *
     * UPDATE (ACTUALIZACIÓN DE LA CONTRASEÑA DEL USUARIO MEDIANTE EL EMAIL DEL MISMO)
     * @param newPassword
     * @param email
     */
    public void updateUserPassword(String newPassword, String email){

        db = this.getReadableDatabase();

        String query = "UPDATE " + TABLE_NAME +
                " SET " + COLUMN_PASSWORD +  " = " + newPassword +
                " WHERE " + COLUMN_EMAIL + " = '" + email + "'";


        db.execSQL(query);

        db.close();
    }

    /**
     * UPDATE
     * @param newPassword
     * @param email
     */
    void updateUserPasswordSQLiteSyntax(String newPassword, String email){

        db = this.getReadableDatabase();

        //nuevos valores
        ContentValues newValues = new ContentValues();
        newValues.put(COLUMN_PASSWORD, newPassword);

        //condición WHERE
        String selection = COLUMN_EMAIL + " = ?" ;
        String[] selectionArgs = {
                email
        };

        db.update(TABLE_NAME, newValues, selection, selectionArgs);
        db.close();
    }



    /**
     * DELETE: Borra un USUARIO que cumple las condiciones que se pasan como parámetros
     * @param tableName
     */
    void deleteFromTable(String tableName, String whereClause) {

        db = this.getReadableDatabase();

        if (!db.isDbLockedByCurrentThread()) {

            String query = "DELETE FROM " + tableName;
            if (whereClause != null && whereClause.length() > 0) {
                query = query + " WHERE " + whereClause;
            }

            db.execSQL(query);
        }

        db = this.getReadableDatabase();
    }

    /**
     * DELETE: Borra un contacto que cumple las condiciones que se pasan como parámetros
     * @param tableName
     * @param whereClause
     * @param whereParams
     */
    void deleteFromTableSQLiteSyntax(String tableName, String whereClause, String[] whereParams) {

        db = this.getReadableDatabase();

        if (!db.isDbLockedByCurrentThread()) {
            db.delete(tableName, whereClause, whereParams);
        }

        db.close();
    }

    /**
     * DELETE: Borra un contacto a partir de su email que se recibe como parámetro
     * @param email
     */
    public void deleteContactByEmail(String email){

        String selection = COLUMN_EMAIL + " = ?" ;

        String[] selectionArgs = {
                email
        };

        deleteFromTableSQLiteSyntax(TABLE_NAME, selection, selectionArgs);
    }

    /**
     * DELETE: Borra un contacto a partir de su id que se recibe como parámetro
     * @param id
     */
    void deleteContactById(String id){

        String selection = COLUMN_ID + " = ?" ;

        String[] selectionArgs = {
                id
        };

        deleteFromTableSQLiteSyntax(TABLE_NAME, selection, selectionArgs);
    }


    /**
     * DELETE: Borra un contacto a partir de su id que se recibe como parámetro
     * @param id
     */
    public void removeContactForId(String id) {
        deleteFromTable(TABLE_NAME, COLUMN_ID + "=" + id);
    }


    /**
     * COMMON
     */
    /**
     * Crear un object Contac a partir de un cursor
     *
     * @param cursor
     * @return
     */
    Contact cursorToContact(Cursor cursor) {

        Contact contact = new Contact();

        contact.setId(cursor.getInt(0));
        contact.setUsuario(cursor.getString(1));
        contact.setEmail(cursor.getString(2));
        contact.setPassword(cursor.getString(3));

        return contact;
    }


    /**
     * @return
     */
    ContentValues getContactValues(Contact contact) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, contact.getUsuario());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PASSWORD, contact.getPassword());

        return values;
    }

}
