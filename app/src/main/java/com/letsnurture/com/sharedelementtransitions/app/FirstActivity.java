package com.letsnurture.com.sharedelementtransitions.app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ln-69 on 5/1/16.
 */
public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);

    }

    public void onClick(View view) {
        View imageView = findViewById(R.id.itemImageView);
        View textView = findViewById(R.id.itemTextView);
        View button = findViewById(R.id.btnSubmit);

        Intent intent = new Intent(this, SecondActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setTransitionName(getString(R.string.activity_text_trans));
            button.setTransitionName(getString(R.string.activity_mixed_trans));

            Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
            Pair<View, String> pair2 = Pair.create(textView, textView.getTransitionName());
            Pair<View, String> pair3 = Pair.create(button, button.getTransitionName());
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, pair1, pair2, pair3);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
