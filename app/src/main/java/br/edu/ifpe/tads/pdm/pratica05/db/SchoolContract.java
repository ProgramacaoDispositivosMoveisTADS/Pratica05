package br.edu.ifpe.tads.pdm.pratica05.db;

import android.provider.BaseColumns;

/**
 * Created by Richardson on 24/04/2015.
 */
public final class SchoolContract {


    public static final String DATABASE_NAME = "School.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE_STUDENT =
            "CREATE TABLE " + Student.TABLE_NAME + " (" +
                    Student._ID + " INTEGER PRIMARY KEY, " +
                    Student.COLUMN_NAME_STUDENT_NAME + " TEXT, " +
                    Student.COLUMN_NAME_STUDENT_GRADE + " INTEGER " + ")";
    public static final String SQL_DELETE_STUDENT = "" +
            "DROP TABLE IF EXISTS " + Student.TABLE_NAME;
    public static abstract class Student implements BaseColumns {
        public static final String TABLE_NAME = "Student";
        public static final String COLUMN_NAME_STUDENT_NAME = "Name";
        public static final String COLUMN_NAME_STUDENT_GRADE = "Grade";
    }
    private SchoolContract() {}
}
