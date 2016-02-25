package com.example.jack.aspectjtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.jack.aspectjlibrary.annotation.CustomTrace;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button mBtn = (Button)findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");

            }
        });

        Button mBtnAspectCustom = (Button)findViewById(R.id.btncustom);
        mBtnAspectCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCustomAspect();
            }
        });
    }

    @CustomTrace(eventId = "one", eventName = "buttonClick")
    private void testCustomAspect(){

        Log.e(TAG, "testCustomAspect: ");
    }
}
