package com.example.hammedopejin.todolist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hammedopejin.todolist.Helper.TodoItemsDbHelper;
import com.example.hammedopejin.todolist.R;

import java.util.ArrayList;

/**
 * Created by hammedopejin on 3/1/17.
 */

public class EditItemActivity extends AppCompatActivity {



    ArrayList<String> items;
    public String date;
    int flag;
    public String priority;
    public String status;
    private String task;
    private String day;
    private String month;
    private String year;
    private String note;
    TodoItemsDbHelper td;
    ArrayList<String> dataFromDB;
    EditText eTask;
    EditText eNote;
    Spinner ePriority;
    Spinner eStatus;
    DatePicker eDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        eTask = (EditText)findViewById(R.id.eTask);
        eNote = (EditText)findViewById(R.id.eNote);
        ePriority = (Spinner)findViewById(R.id.ePriority);
        eStatus = (Spinner)findViewById(R.id.eStatus);
        eDueDate = (DatePicker)findViewById(R.id.eDueDate);

        items = new ArrayList<>();
        td = new TodoItemsDbHelper(this);
        dataFromDB = td.getAll(getIntent().getStringExtra("task"));
        task = dataFromDB.get(0);
        flag = td.getID(task);
        date = (dataFromDB.get(2) + ", " + dataFromDB.get(1)) + ", " + dataFromDB.get(3);
        note = dataFromDB.get(4);
        priority = dataFromDB.get(5);
        status = dataFromDB.get(6);


        eTask.setText(task.toString());
        eTask.setSelection(eTask.getText().length());

        eNote.setText(note.toString());
        eNote.setSelection(eNote.getText().length());

        ePriority.setSelection(priSelect(priority.toString()));

        eStatus.setSelection(statSelect(status.toString()));

        eDueDate.setMinDate(System.currentTimeMillis() - 1000);
        eDueDate.updateDate(Integer.parseInt((dataFromDB.get(3)).toString()),(Integer.parseInt((dataFromDB.get(2)))-1), Integer.parseInt(dataFromDB.get(1)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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
            onSave(getCurrentFocus());
            return true;
        }
        if (id == R.id.action_cancel) {
            onCancel(getCurrentFocus());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int priSelect(String arg){
        if (arg.compareTo("HIGH") == 0)
            return 0;
        if (arg.compareTo("MEDIUM") == 0)
            return 1;
        if (arg.compareTo("LOW") == 0)
            return 2;
        return 0;
    }
    int statSelect(String arg){
        if (arg.compareTo("TO DO") == 0)
            return 0;
        if (arg.compareTo("DONE") == 0)
            return 1;
        return 0;
    }

    public void onCancel(View view){
        Intent i = new Intent(EditItemActivity.this, EditDialogActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("it", task);
        startActivity(i);
    }

    public void onSave(View v) {
        priority = ePriority.getSelectedItem().toString();
        status = eStatus.getSelectedItem().toString();
        day = String.valueOf(eDueDate.getDayOfMonth());
        month = String.valueOf(eDueDate.getMonth()+ 1);
        year = String.valueOf(eDueDate.getYear());
        task = eTask.getText().toString();
        note = eNote.getText().toString();


        td.updateItem(task, day, month, year, note, priority, status, flag);//writeItems();

        Intent i = new Intent(EditItemActivity.this, EditDialogActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("it", task);
        startActivity(i);
    }


}
