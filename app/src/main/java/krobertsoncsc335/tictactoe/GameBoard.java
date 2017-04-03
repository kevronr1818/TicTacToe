package krobertsoncsc335.tictactoe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Kevron on 3/31/2017.
 */

public class GameBoard {
    Paint paint = new Paint();

    private int displayWidth;
    private int displayHeight;

    private int numHorizontalLines;
    private int numVerticalLines;
    private int lineSpacingVertical;
    private int lineSpacingHorizontal;


    public void handleTouch(Point p){

    }

    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);

        for(int i=0;i<(numHorizontalLines+1);i++){
            int lineheight = (i+1)*lineSpacingHorizontal;
            canvas.drawLine(0,lineheight,displayWidth,lineheight, paint);
        }

        for(int i=0;i<numVerticalLines;i++){
            int linewidth = (i+1)*displayWidth/(numVerticalLines+1);
            canvas.drawLine(linewidth, 0, linewidth, displayHeight, paint);
        }
    }
}
