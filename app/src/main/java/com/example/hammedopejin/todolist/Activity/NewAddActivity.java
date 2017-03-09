package com.example.hammedopejin.todolist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hammedopejin.todolist.Helper.TodoItemsDbHelper;
import com.example.hammedopejin.todolist.R;

/**
 * Created by hammedopejin on 3/1/17.
 */

public class NewAddActivity extends AppCompatActivity {
    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;





    TodoItemsDbHelper td;

    private String task;
    private String day;
    private String month;
    private String year;
    private String note;
    private String iPriority;
    private String iStatus;
    DatePicker eDate;

    private Spinner priority;
    private Spinner status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        eDate = (DatePicker) findViewById(R.id.dueDate);
        eDate.setMinDate(System.currentTimeMillis() - 1000);
        td = new TodoItemsDbHelper(this);
        addListenerOnPriorityItemSelection();
        addListenerOnStatusItemSelection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_action_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            onAddItem(getCurrentFocus());
            return true;
        }
        if (id == R.id.action_cancel) {
            onCancel(getCurrentFocus());
            return true;
        }
        /*switch (item.getItemId()) {
            case R.id.menu_load:
                menuItem = item;
                menuItem.setActionView(R.layout.progressbar);
                menuItem.expandActionView();
                TestTask task = new TestTask();
                task.execute("test");
                break;
            default:
                break;
        }
        return true; */

        return super.onOptionsItemSelected(item);
    }


    public void onAddItem(View v) {
        EditText eTask = (EditText)findViewById(R.id.task);
        task = eTask.getText().toString();
        day = String.valueOf(eDate.getDayOfMonth());
        month = String.valueOf(eDate.getMonth()+ 1);
        year = String.valueOf(eDate.getYear());

        EditText eNote = (EditText)findViewById(R.id.note);
        note = eNote.getText().toString();









        td.addItem(task, day, month, year, note, iPriority, iStatus);//writeItems();


        Intent i = new Intent(NewAddActivity.this, TodolistActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void onCancel(View view){
        Intent i = new Intent(NewAddActivity.this, TodolistActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void addListenerOnPriorityItemSelection(){
        priority = (Spinner) findViewById(R.id.priority);
        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iPriority   = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addListenerOnStatusItemSelection(){
        status = (Spinner) findViewById(R.id.status);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iStatus   = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
