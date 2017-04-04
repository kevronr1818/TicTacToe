package krobertsoncsc335.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by Kevron on 3/31/2017.
 */

public class GameBoard {
    final float STROKE_WIDTH = 7;
    final int PADDING = 25;

    private int displayWidth;
    private int displayHeight;

    private int numHorizontalLines;
    private int numVerticalLines;
    private int lineSpacingVertical;
    private int lineSpacingHorizontal;


    Paint paint = new Paint();
    //DrawThread boardThread;
    //BoardThread boardThread;
    SurfaceHolder boardHolder;
    Context contxt;

    public void handleTouch(Point p){

    }

    /**Determines pixels**/
    public void setDisplayConstants(Context context){
        numHorizontalLines = 2;
        numVerticalLines = 2;
        displayWidth = context.getResources().getDisplayMetrics().widthPixels;
        displayHeight=displayWidth;
        lineSpacingVertical = displayWidth/(numVerticalLines+1);
        lineSpacingHorizontal = displayHeight/(numHorizontalLines+1);
        //displayHeight=displayMetrics.heightPixels - SYSTEM_BAR_OFFSET;
    }


    public void drawBoard(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        //draws lines going left to right
            canvas.drawLine(0, 175, 800, 175, paint);
            canvas.drawLine(0, 375, 800, 375, paint);
            canvas.drawLine(0, 575, 800, 575, paint);

        //draws lines going up and down
            canvas.drawLine(175, 0, 175, 575, paint);
            canvas.drawLine(375, 0, 375, 575, paint);


    }

    public void drawX(Canvas canvas, int row, int column){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH*2);
        paint.setColor(Color.BLACK);
        canvas.drawLine(column*lineSpacingVertical+PADDING, row*lineSpacingHorizontal+PADDING, (column+1)*lineSpacingVertical-PADDING, (row+1)*lineSpacingHorizontal-PADDING, paint);
        canvas.drawLine(column*lineSpacingVertical+PADDING, (row+1)*lineSpacingHorizontal-PADDING, (column+1)*lineSpacingVertical-PADDING, row*lineSpacingHorizontal+PADDING, paint);
    }

    private void drawO(Canvas canvas, int row, int column){
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH*2);
        paint.setColor(Color.RED);
        RectF oval = new RectF(column*lineSpacingVertical+PADDING, row*lineSpacingHorizontal+PADDING, (column+1)*lineSpacingVertical-PADDING, (row+1)*lineSpacingHorizontal-PADDING);
        canvas.drawOval(oval, paint);
        //canvas.drawCircle(row*lineSpacingVertical+lineSpacingVertical/2, column*lineSpacingHorizontal+lineSpacingHorizontal/2, 4*lineSpacingVertical/10, paint);
    }

    private void drawMoves(Canvas canvas){

        for(int i=0; i<2;i++){
            for(int j=0; j<2;j++){
                char ch = BoardPosition.PLAYER_ONE;
                if(ch=='X'){
                    drawX(canvas, i, j);
                } else if(ch=='O'){
                    drawO(canvas, i, j);
                }
            }
        }
    }

    public char getMove (int row, int column){
        return gameBoard[row][column];
    }

}
