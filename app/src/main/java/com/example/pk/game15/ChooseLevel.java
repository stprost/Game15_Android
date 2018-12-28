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
public class ChooseLevel extends AppCompatActivity {

    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        Button normal = findViewById(R.id.normal);
        Button easy = findViewById(R.id.easy);
        Button hard = findViewById(R.id.hard);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            width = metrics.widthPixels / 3;
            height = metrics.widthPixels / 7;
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            width = metrics.heightPixels / 3;
            height = metrics.heightPixels / 7;
        }
        ViewGroup.LayoutParams params = easy.getLayoutParams();
        params.width = width;
        params.height = height;
        easy.setLayoutParams(params);
        normal.setLayoutParams(params);
        hard.setLayoutParams(params);


        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side", 4);
                startActivity(intent);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side", 5);
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side", 6);
                startActivity(intent);
            }
        });
    }
}
