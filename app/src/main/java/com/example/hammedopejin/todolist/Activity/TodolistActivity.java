package com.example.hammedopejin.todolist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hammedopejin.todolist.Adapter.CustomUserAdapter;
import com.example.hammedopejin.todolist.Fragment.MyDeleteDialogFragment;
import com.example.hammedopejin.todolist.Helper.TodoItemsDbHelper;
import com.example.hammedopejin.todolist.Items;
import com.example.hammedopejin.todolist.R;

import java.util.ArrayList;

public class TodolistActivity extends AppCompatActivity implements MyDeleteDialogFragment.OnCompleteListener{

        // REQUEST_CODE can be any value we like, used to determine the result type later
        private final int REQUEST_CODE = 20;

        CustomUserAdapter cAdapter;
        ListView lvItems;
        String iValue;
        TodoItemsDbHelper td;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_todolist);




            td = new TodoItemsDbHelper(this);
            ArrayList<String> baseData = td.getItem();
            String todo[] = new String[baseData.size()];
            String pri[] = new String[baseData.size()];
            Items[] data = new Items[baseData.size()];


            for (int i = 0, j = 0; j < baseData.size(); i++, j=j+2) {
                todo[i] = baseData.get(j);

            }
            for (int i = 1, j = 0; i < baseData.size();  j++, i=i+2) {
                pri[j] = baseData.get(i);
            }
            for (int i = 0; i < todo.length; i++) {
                data[i] = new Items(todo[i], pri[i]);
            }
            // Create the adapter to convert the array to views
            cAdapter = new CustomUserAdapter(this, R.layout.item_user, data);
            // Attach the adapter to a ListView
            lvItems = (ListView) findViewById(R.id.lvItems);
            lvItems.setAdapter(cAdapter);

            //setupListViewListener();
            setupListViewCListener();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


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
        if (id == R.id.action_add) {
            onAddItem(this.getCurrentFocus());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        // Switch to new cursor and update contents of ListView
        //todoAdapter.changeCursor(newCursor);

        public void onComplete(String task){
            cAdapter.notifyDataSetChanged();
        }


        public void onAddItem(View v) {
            Intent i = new Intent(TodolistActivity.this, NewAddActivity.class);
            startActivity(i);

        }

        private void setupListViewCListener(){
            lvItems.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapter, View view, int pos,
                                                long id) {
                            Object it = adapter.getItemAtPosition(pos);
                            Items we = (Items)it;
                            iValue = we.todo;
                            onClick(view);

                            return;
                        }
                    });
        }

        // launching an activity for a result
        public void onClick(View view) {
            Intent i = new Intent(TodolistActivity.this, EditDialogActivity.class);
            i.putExtra("it", iValue);
            startActivityForResult(i, REQUEST_CODE);

        }
    }
