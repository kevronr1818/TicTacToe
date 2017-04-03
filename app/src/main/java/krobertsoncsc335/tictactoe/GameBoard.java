package krobertsoncsc335.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Kevron on 3/31/2017.
 */

public class GameBoard {
    Paint paint = new Paint();

    public void handleTouch(Point p){

    }


    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        //draws lines going left to right
            canvas.drawLine(0, 175, 800, 175, paint);
            canvas.drawLine(0, 375, 800, 375, paint);
            canvas.drawLine(0, 575, 800, 575, paint);

        //draws lines going up and down
            canvas.drawLine(175, 0, 175, 600, paint);
            canvas.drawLine(375, 0, 375, 600, paint);


    }
}
