package com.example.pk.game15;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = findViewById(R.id.play);
        Button rules = findViewById(R.id.rules);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            width = metrics.widthPixels / 3;
            height = metrics.widthPixels / 5;
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            width = metrics.heightPixels / 3;
            height = metrics.heightPixels / 5;
        }
        ViewGroup.LayoutParams params = play.getLayoutParams();
        params.width = width;
        params.height = height;
        play.setLayoutParams(params);
        rules.setLayoutParams(params);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseLevel.class);
                startActivity(intent);
            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rules.class);
                startActivity(intent);
            }
        });
    }

}
