package com.example.pk.game15;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

class Cell extends android.support.v7.widget.AppCompatButton {

    private String num;
    //описание кнопки/клетки
    Cell (Activity activity, final Logic logic, final Graphic graphic){
        super(activity, null);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = 0;
        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setGravity(Gravity.FILL);
        setLayoutParams(params);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!logic.isGameOver()){
                    logic.move(graphic.findPosition(Cell.this));
                    graphic.update(logic);
                }
            }
        });
    }
    //задание стиля не пустой кнопки
    public void normalStyle(){
        setBackgroundResource(R.drawable.scaleshape);
        setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
