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
    private int numHorizontalLines = 2;
    private int numVerticalLines = 2;



    public void handleTouch(Point p){

    }


    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        //draws lines going left to right
        for(int i=0;i<(numHorizontalLines+1);i++){
            canvas.drawRect(0, 250, 800, 250, paint);
            canvas.drawLine(0, 450, 800, 450, paint);

        }
        //draws lines going up and down
        for(int i=0;i<numVerticalLines;i++){
            canvas.drawLine(175, 0, 175, 800, paint);
            canvas.drawLine(375, 0, 375, 800, paint);
        }
    }
}
