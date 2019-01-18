package com.example.pk.game15;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;

public class Game extends AppCompatActivity {
    private Chronometer chronometer;
    private boolean running = false;
    private long milliseconds;
    private Logic logic;
    private Graphic graphic;
    private int width;
    private int height;
    private Resources res;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        res = getResources();

        Intent intent = getIntent();
        int side = intent.getIntExtra("side", res.getInteger(R.integer.sideFour));
        Display display = getWindowManager().getDefaultDisplay();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            width = display.getWidth() - res.getInteger(R.integer.playGridIdent);
            height = display.getWidth() - res.getInteger(R.integer.playGridIdent);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            width = display.getHeight() - res.getInteger(R.integer.playGridIdent);
            height = display.getHeight() - res.getInteger(R.integer.playGridIdent);
        }
        GridLayout field = findViewById(R.id.field);
        ViewGroup.LayoutParams params = field.getLayoutParams();
        params.width = width;
        params.height = height;
        field.setLayoutParams(params);
        field.setColumnCount(side);
        field.setRowCount(side);

        chronometer = findViewById(R.id.chronometer);
        String chr = res.getText(R.string.chronometr).toString();
        chronometer.setFormat(chr + " %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        milliseconds = 0;
        startCh();

        logic = new Logic(side);

        if (savedInstanceState != null) {
            logic = savedInstanceState.getParcelable("logic");
            chronometer.setBase(savedInstanceState.getLong("milliseconds"));
        }

        graphic = new Graphic(this, this, field, logic, side);

        Button pause = findViewById(R.id.pause_button);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseCh();
                AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
                builder.setMessage(res.getText(R.string.paused))
                        .setCancelable(false)
                        .setNegativeButton(res.getText(R.string.restart), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logic.newGame();
                                graphic.update(logic);
                                resetCh();
                                startCh();
                            }
                        })
                        .setPositiveButton(res.getText(R.string.contin), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                startCh();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button menu = findViewById(R.id.menu_button);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startCh() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - milliseconds);
            chronometer.start();
            running = true;
        }
    }

    private void pauseCh() {
        if (running) {
            chronometer.stop();
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    private void resetCh() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        milliseconds = 0;
    }

    //диалоговое окно приокончании игры
    public void endGame() {
        pauseCh();
        String time = res.getText(R.string.time).toString();
        String min = res.getText(R.string.min).toString();
        String sec = res.getText(R.string.sec).toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
        builder.setTitle(res.getText(R.string.win))
                .setMessage(time + " " + milliseconds / res.getInteger(R.integer.convertToMin) + " " + min + " " + (milliseconds / res.getInteger(R.integer.convertToSec) - (milliseconds / res.getInteger(R.integer.convertToMin)) * res.getInteger(R.integer.sixty)) + " " + sec)
                .setCancelable(false)
                .setPositiveButton(res.getText(R.string.restart), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logic.newGame();
                        graphic.update(logic);
                        resetCh();
                        startCh();
                    }
                })
                .setNegativeButton(res.getText(R.string.toMenu), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Game.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        resetCh();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("logic", logic);
        outState.putLong("milliseconds", chronometer.getBase());
    }
}
