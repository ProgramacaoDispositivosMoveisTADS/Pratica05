package br.edu.ifpe.tads.pdm.pratica05.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static br.edu.ifpe.tads.pdm.pratica05.db.SchoolContract.*;

/**
 * Created by Richardson on 24/04/2015.
 */
public class SchoolDbHelper extends SQLiteOpenHelper {


    public SchoolDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STUDENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STUDENT);
        this.onCreate(db);
    }
}
