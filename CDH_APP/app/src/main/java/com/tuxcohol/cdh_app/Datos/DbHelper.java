package com.tuxcohol.cdh_app.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "TuxCoholDB";
    private static String TABLE_NAME = "Favoritos";
    public static String KEY_ID_USUARIO = "idUsuario";
    public static String KEY_ID_LOCAL = "idLocal";
    public static String FAVORITE_STATUS = "estado";
    // dont forget write this spaces
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID_USUARIO + " TEXT," + KEY_ID_LOCAL+ " int,"
            + FAVORITE_STATUS+" TEXT)";


    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // create empty table
    public void insertEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // enter your value
        for (int x = 1; x < 11; x++) {
            cv.put(KEY_ID_LOCAL, x);
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME,null, cv);
        }
    }

    // insert data into database
    public void insertIntoTheDatabase(String idUsuario, int idLocal, String status) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID_USUARIO, idUsuario);
        cv.put(KEY_ID_LOCAL, idLocal);
        cv.put(FAVORITE_STATUS, status);
        db.insert(TABLE_NAME,null, cv);
        Log.d("FavDB Status", idLocal + ", favstatus - "+status+" - . " + cv);
    }

    // read all data
    public Cursor read_all_data(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID_LOCAL+"='"+id+"'";
        return db.rawQuery(sql,null,null);
    }

    // remove line from database
    public void remove_fav(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='false' WHERE "+KEY_ID_LOCAL+"='"+id+"'";
        db.execSQL(sql);
        Log.d("remove", String.valueOf(id));

    }

    //es favorito?
    public boolean is_fav(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from  " + TABLE_NAME + " where " + KEY_ID_LOCAL+"="+id+" and "+FAVORITE_STATUS+"= 'true'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='true'";
        return db.rawQuery(sql,null,null);
    }



}
