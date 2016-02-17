package com.letsnurture.com.sharedelementtransitions.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ln-69 on 5/1/16.
 */
public class StartingActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvActvityDemo, tvFragmentDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        tvActvityDemo = (TextView) findViewById(R.id.tvActvityDemo);
        tvFragmentDemo = (TextView) findViewById(R.id.tvFragmentDemo);
        tvFragmentDemo.setOnClickListener(this);
        tvActvityDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvActvityDemo:
                Intent intentAct = new Intent(StartingActivity.this, FirstActivity.class);
                startActivity(intentAct);
                break;
            case R.id.tvFragmentDemo:
                Intent intent = new Intent(StartingActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }
    }
}
