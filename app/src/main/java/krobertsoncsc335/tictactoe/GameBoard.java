package krobertsoncsc335.tictactoe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Kevron on 3/31/2017.
 */

public class GameBoard {
    private int[][] board = new int[3][3];
    Paint paint = new Paint();
    BoardPosition boardPosition = BoardPosition.EMPTY;

    public GameBoard() {

//Initiate the game board with blanks
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }

        }

    }


    public void handleMove(Point p) {
        //getting X or O to appear on screen
        getColumnWidth();
        getRowHeight();
        //int columnWidth = 100;
        //int rowHeight = 100;
        int col = p.x/getColumnWidth();
        int row = p.y/getRowHeight();

        // Check that row and col are in bounds
        board[row][col] = 1;



    }

    public void drawMarkers(Canvas canvas) {
        //paint.setARGB(255, 255, 0, 0);
        //nested for loop to take row and col into x's y's
        /*int x1 = col * columnWidth;
        int y1 = row * rowHeight;
        int x2 = x1+10;
        int y2 = y1+10;
        */


        for (int r = 0; r < 3; r++){
            for(int c =0; c < 3; c++){
                if(board[r][c]==1)
                canvas.drawCircle(c*getColumnWidth() + 20 ,r*getRowHeight() + 20, 20, paint);
            }
        }

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

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                board[i][j] = 0;

            }

        }

    }

    private int getColumnWidth(){

        int columnWidth = DisplayAdvisor.maxX/3;
        return columnWidth;
    }

    private int getRowHeight(){

        int rowHeight = DisplayAdvisor.maxY/3;
        return rowHeight;
    }


/*
    public void placeMove(int x, int y, String moveMade) {
        if (board[x][y] == "") {
            board[x][y] = moveMade;
        }

    }

    */


}
