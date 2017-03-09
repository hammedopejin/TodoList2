package com.example.hammedopejin.todolist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hammedopejin on 3/6/17.
 */

public class SplashActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Intent intent = new Intent(this, TodolistActivity.class);
            startActivity(intent);
            finish();
        }
}
