package br.edu.ifpe.tads.pdm.pratica05;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifpe.tads.pdm.pratica05.db.SchoolDbHelper;
import static br.edu.ifpe.tads.pdm.pratica05.db.SchoolContract.Student.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void buttonInsertClick(View view) {
        String name =
                ((EditText)findViewById(R.id.edit_name)).getText().toString();
        int grade = Integer.parseInt(
                ((EditText) findViewById(R.id.edit_grade)).getText().toString());
        SchoolDbHelper dbHelper = new SchoolDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STUDENT_NAME, name);
        values.put(COLUMN_NAME_STUDENT_GRADE, grade);
        long newId = db.insert(TABLE_NAME, null, values);
        Toast toast = Toast.makeText(this,
                "Registro adicionado. ID = " + newId, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void buttonUpdateClick(View view) {
        String name =
                ((EditText)findViewById(R.id.edit_name)).getText().toString();
        int grade = Integer.parseInt(
                ((EditText) findViewById(R.id.edit_grade)).getText().toString());
        SchoolDbHelper dbHelper = new SchoolDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_STUDENT_GRADE, grade);
        String selection = COLUMN_NAME_STUDENT_NAME + " LIKE ?";
        String[] selectionArgs = { name + "" };
        int count = db.update(TABLE_NAME, values, selection, selectionArgs);
        Toast toast = Toast.makeText(this,
                "Registros atualizados: " + count, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void buttonDeleteClick(View view) {
        String name =
                ((EditText)findViewById(R.id.edit_name)).getText().toString();
        SchoolDbHelper dbHelper = new SchoolDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_NAME_STUDENT_NAME + " LIKE ?";
        String[] selectionArgs = { name + "" };
        int count = db.delete(TABLE_NAME, selection, selectionArgs);
        Toast toast = Toast.makeText(this,
                "Registros deletados: " + count, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void buttonQueryClick(View view) {
        String name =
                ((EditText) findViewById(R.id.edit_name)).getText().toString();
        SchoolDbHelper dbHelper = new SchoolDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {_ID,
                COLUMN_NAME_STUDENT_NAME,
                COLUMN_NAME_STUDENT_GRADE};
        String selection = COLUMN_NAME_STUDENT_NAME + " LIKE ?";
        String[] selectionArgs = {name + "%"};
        String sortOrder = COLUMN_NAME_STUDENT_GRADE + " DESC";
        Cursor c = db.query(TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null, null,
                sortOrder);
        ArrayList<CharSequence> data = new ArrayList<CharSequence>();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String entry = c.getInt(c.getColumnIndex(_ID)) + ": ";
            entry += c.getString(
                    c.getColumnIndex(COLUMN_NAME_STUDENT_NAME)) + " = ";
            entry += c.getInt(
                    c.getColumnIndex(COLUMN_NAME_STUDENT_GRADE));
            data.add(entry);
            c.moveToNext();
        }
        Intent intent = new Intent(this, QueryResultActivity.class);
        intent.putCharSequenceArrayListExtra("data", data);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
