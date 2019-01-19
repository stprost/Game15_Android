package com.example.pk.game15;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.GridLayout;

class Graphic {
    private final Cell[] cells;
    private final int side;
    private final Game game;
    private int[] id = new int[] {R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8, R.id.c9, R.id.c10, R.id.c11, R.id.c12, R.id.c13, R.id.c14, R.id.c15, R.id.c16, R.id.c17, R.id.c18, R.id.c19, R.id.c20, R.id.c21, R.id.c22, R.id.c23, R.id.c24, R.id.c25, R.id.c26, R.id.c27, R.id.c28, R.id.c29, R.id.c30, R.id.c31, R.id.c32, R.id.c33, R.id.c34, R.id.c35};

    Graphic(Activity activity, Game game, GridLayout field, Logic logic, int side) {
        this.side = side;
        this.game = game;
        cells = new Cell[side * side];
        //заполнение поля кнопками/клетками
        for (int i = 0; i < side * side; i++) {
            cells[i] = new Cell(activity, logic, this);
            cells[i].setId(id[i]);
            field.addView(cells[i]);
        }
        update(logic);
    }

    //обновление игрового поля
    @SuppressLint("SetTextI18n")
    public void update(Logic logic) {
        for (int i = 0; i < side * side; i++) {
            if (i != logic.getBlankPos()) {
                cells[i].setText(Integer.toString(logic.getTiles()[i]));
                cells[i].setNum(Integer.toString(logic.getTiles()[i]));
                cells[i].normalStyle();
            } else {
                cells[i].setBackgroundResource(R.color.white);
                cells[i].setText(" ");
                cells[i].setNum(" ");
            }
        }
        if(logic.isGameOver()) game.endGame();
    }

    //нахождение индекса клетки
    public int findPosition(Cell cell) {
        for (int i = 0; i < side * side; i++){
            if(cells[i].getText().equals(cell.getText()) ) return i;
        }
        return 0;
    }

    public Cell[] getCells() {
        return cells;
    }
}
