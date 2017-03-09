package com.example.hammedopejin.todolist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hammedopejin.todolist.Fragment.MyDeleteDialogFragment;
import com.example.hammedopejin.todolist.Helper.TodoItemsDbHelper;
import com.example.hammedopejin.todolist.R;

import java.util.ArrayList;

/**
 * Created by hammedopejin on 3/1/17.
 */

public class EditDialogActivity extends AppCompatActivity {
    // REQUEST_CODE can be any value we like, used to determine the result type later
    private final int REQUEST_CODE = 20;


    ArrayList<String> items;
    private ListView lvItems;
    public String task;
    public String date;
    public String[] data = new String[5];
    public String note;
    public String priority;
    public String status;
    ArrayAdapter<String> itemsAdapter;
    TodoItemsDbHelper td;
    ArrayList<String> dataFromDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dialog);

        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items = new ArrayList<>();
        td = new TodoItemsDbHelper(this);
        dataFromDB = td.getAll(getIntent().getStringExtra("it"));
        task = dataFromDB.get(0);
        date = (dataFromDB.get(2) + ", " + dataFromDB.get(1)) + ", " + dataFromDB.get(3);
        note = dataFromDB.get(4);
        priority = dataFromDB.get(5);
        status = dataFromDB.get(6);
        data[0] = ("Task Name :           " + task).toString();
        data[1] = ("Due Date :               "+ date).toString();
        data[2] = ("Notes :                    " + note).toString();
        data[3] = ("Priority Level :        " + priority).toString();
        data[4] = ("Status :                   " + status).toString();
        // Create the adapter to convert the array to views
        // cAdapter = new CustomUserAdapter(this, items, 2);
        // Attach the adapter to a ListView
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems = (ListView)findViewById(R.id.listPreview);
        lvItems.setAdapter(itemsAdapter);
        items.add(data[0]);
        items.add(data[1]);
        items.add(data[2]);
        items.add(data[3]);
        items.add(data[4]);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_dialog, menu);
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

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, TodolistActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.action_edit:
                onClickEdit(getCurrentFocus());
                break;
            case R.id.action_delete:
                onClickDelete(getCurrentFocus());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    void setTd(TodoItemsDbHelper td){
        this.td = td;
    }
    TodoItemsDbHelper getTd(){
        return td;
    }

    // launching an activity for a result
    public void onClickEdit(View view) {
        Intent i = new Intent(EditDialogActivity.this, EditItemActivity.class);
        i.putExtra("task", task);
        startActivityForResult(i, REQUEST_CODE);
    }

    public void onClickDelete(View view) {
        FragmentManager fm = getSupportFragmentManager();
        setTd(td);
        MyDeleteDialogFragment deleteDialog = MyDeleteDialogFragment.newInstance("ATTENTION !!!", task);
        //Bundle args = new Bundle();
        //args.putString("task", task);
        //deleteDialog.setArguments(args);
        deleteDialog.show(fm, "fragment_alert");
    }

}
