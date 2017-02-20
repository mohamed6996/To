package com.example.to;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.to.data.Contract;
import com.example.to.data.Helper;

public class MainActivity extends AppCompatActivity {

    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        helper = new Helper(this);
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                Contract.ToDoEntry._ID,
                Contract.ToDoEntry.COLUMN_TITLE,};


        Cursor cursor = db.query(
                Contract.ToDoEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.textView);

        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(Contract.ToDoEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(Contract.ToDoEntry.COLUMN_TITLE);


            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentName
                      ));
            }
        } finally {
            cursor.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.show:
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}