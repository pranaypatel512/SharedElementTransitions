package com.letsnurture.com.sharedelementtransitions.app;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ln-69 on 5/1/16.
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_target_activity);

        View smallImageView = findViewById(R.id.itemTextView);
        View editText = findViewById(R.id.editText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            smallImageView.setTransitionName(getString(R.string.activity_image_trans));
            editText.setTransitionName(getString(R.string.activity_text_trans));
        }
    }
}
