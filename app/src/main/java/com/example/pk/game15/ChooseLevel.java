package com.example.pk.game15;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class ChooseLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        Button normal = findViewById(R.id.normal);
        Button easy = findViewById(R.id.easy);
        Button hard = findViewById(R.id.hard);

        final Resources res = getResources();
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side",res.getInteger(R.integer.sideFour));
                startActivity(intent);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side", res.getInteger(R.integer.sideFive));
                startActivity(intent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevel.this, Game.class);
                intent.putExtra("side", res.getInteger(R.integer.sideSix));
                startActivity(intent);
            }
        });
    }
}
