package krobertsoncsc335.tictactoe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;


import java.util.Random;


/**
 * Created by Kevron on 3/31/2017.
 */

public class GameBoard {
    private int[][] board = new int[3][3];
    Paint paint = new Paint();
    private Bitmap letterOBitmap;
    private Bitmap letterXBitmap;
    private GameActivity gameActivity;
    private MediaPlayer spotTakenSound;

    public GameBoard() {

//Initiate the game board with blanks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    //Handles the human move
    public void handleMove(Point p) {

        getColumnWidth();
        getRowHeight();

        int col = p.x/getColumnWidth();
        int row = p.y/getRowHeight();

        // Check that row and col are in bounds
        if(board[row][col]!=0){
            spotTakenSound = MediaPlayer.create(gameActivity, R.raw.takenspotsound);
            spotTakenSound.start();
        }
        else{
            board[row][col] = 1;
        }
    }
    //draw either an O or X to the screen
    public void drawMarkers(Canvas canvas) {

        letterOBitmap = DisplayAdvisor.loadBitmap(gameActivity.getResources(), R.drawable.letter_o);
        letterOBitmap = DisplayAdvisor.loadScaledToIdeal(gameActivity.getResources(), (int)(300* DisplayAdvisor.scaleX), (int)(300 * DisplayAdvisor.scaleY), R.drawable.letter_o);

        letterXBitmap = DisplayAdvisor.loadBitmap(gameActivity.getResources(), R.drawable.letter_x);
        letterXBitmap = DisplayAdvisor.loadScaledToIdeal(gameActivity.getResources(), (int)(300* DisplayAdvisor.scaleX), (int)(300 * DisplayAdvisor.scaleY), R.drawable.letter_x);

        for (int r = 0; r < 3; r++){
            for(int c =0; c < 3; c++){
                if(board[r][c]==1)
                canvas.drawBitmap(letterOBitmap, c*getColumnWidth() + 50, r*getRowHeight() + 50, paint);
            }
        }

        for (int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(board[r][c]==2)
                    canvas.drawBitmap(letterXBitmap, c * getColumnWidth() + 50, r * getRowHeight() + 50, paint);
            }
        }

   }

    //Draws the game board
    public void drawBoard(Canvas canvas) {
        canvas.drawColor(Color.BLUE);

        //draws lines going left to right
        canvas.drawLine(0, getRowHeight(), DisplayAdvisor.maxX, getRowHeight(), paint);
        canvas.drawLine(0, getRowHeight() * 2, DisplayAdvisor.maxX, getRowHeight() * 2, paint);//getrowHeight x 2 same for column
        canvas.drawLine(0, getRowHeight() * 3, DisplayAdvisor.maxX, getRowHeight() * 3, paint); //getrowHeight x3 same for column

        //draws lines going up and down
        canvas.drawLine(getColumnWidth(), 0, getColumnWidth(), DisplayAdvisor.maxY, paint);
        canvas.drawLine(getColumnWidth() * 2, 0, getColumnWidth() * 2, DisplayAdvisor.maxY, paint);
    }

    private int getColumnWidth(){

        int columnWidth = DisplayAdvisor.maxX/3;
        return columnWidth;
    }

    private int getRowHeight(){

        int rowHeight = DisplayAdvisor.maxY/3;
        return rowHeight;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }


    public void handleComputerMove() {

       if(!makeComputerWin()){
           blockHumanWin();
       }
       else if (!blockHumanWin()){
           makeComputerWin();
       }

    }

    public void makeRandomComputerMove() {
        Random random = new Random();
        while(true){

            int col = random.nextInt(3);
            int row = random.nextInt(3);

            if(board[row][col]==0) {
                board[row][col] = 2;
                return;
            }
        }
    }

    public boolean blockHumanWin() {
        ///test 1 (across in top row)
        if(board[0][0]==1 &&  board[0][0]==(board[0][1]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }
        else if(board[0][0]==1 &&  board[0][0]==(board[0][2]) &&
                board[0][1]==0)
        {
            board[0][1]= 2;
            return true;
        }
        else if(board[0][2]==1 &&  board[0][2]==(board[0][1]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test2 (across in middle row)
        if(board[1][0]==1&&  board[1][0]==(board[1][1]) &&
                board[1][2]==0)
        {
            board[1][2]= 2;
            return true;
        }
        else if(board[1][0]==1&&  board[1][0]==(board[1][2]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[1][2]==1&&  board[1][2]==(board[1][1]) &&
                board[1][0]==0)
        {
            board[1][0]= 2;
            return true;
        }

        ///test3 (across in bottom row)
        if(board[2][0]==1 &&  board[2][0]==(board[2][1]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[2][0]==1 &&  board[2][0]==(board[2][2]) &&
                board[2][1]==0)
        {
            board[2][1]= 2;
            return true;
        }
        else if(board[2][2]==1 &&  board[2][2]==(board[2][1]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }

        ///test4 (down in first column)
        if(board[0][0]==1 &&  board[0][0]==(board[1][0]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }
        else if(board[0][0]==1 &&  board[0][0]==(board[2][0]) &&
                board[1][0]==0)
        {
            board[1][0]= 2;
            return true;
        }
        else if(board[2][0]==1 &&  board[2][0]==(board[1][0]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test5 (down in second column)
        if(board[0][1]==1 &&  board[0][1]==(board[1][1]) &&
                board[2][1]==0)
        {
            board[2][1]= 2;
            return true;
        }
        else if(board[0][1]==1 &&  board[0][1]==(board[2][1]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][1]==1 &&  board[2][1]==(board[1][1]) &&
                board[0][1]==0)
        {
            board[0][1]= 2;
            return true;
        }

        ///test6 (down in third column)
        if(board[0][2]==1 &&  board[0][2]==(board[1][2]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[0][2]==1 &&  board[0][2]==(board[2][2]) &&
                board[1][2]==0)
        {
            board[1][2]= 2;
            return true;
        }
        else if(board[2][2]==1 &&  board[2][2]==(board[1][2]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }

        ///test7 (diagonal from left to right)
        if(board[0][0]==1 &&  board[0][0]==(board[1][1]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[0][0]==1 &&  board[0][0]==(board[2][2]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][2]==1 &&  board[2][2]==(board[1][1]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test8 (diagonal from right to left)
        if(board[0][2]==1 &&  board[0][2]==(board[1][1]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }
        else if(board[0][2]==1 &&  board[0][2]==(board[2][0]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][0]==1 &&  board[2][0]==(board[1][1]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }

        else {
            makeRandomComputerMove();
        }

        return false;

    }



    public boolean makeComputerWin(){
        ///test 1 (across in top row)
        if(board[0][0]==2 &&  board[0][0]==(board[0][1]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }
        else if(board[0][0]==2 &&  board[0][0]==(board[0][2]) &&
                board[0][1]==0)
        {
            board[0][1]= 2;
            return true;
        }
        else if(board[0][2]==2 &&  board[0][2]==(board[0][1]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test2 (across in middle row)
        if(board[1][0]==2 &&  board[1][0]==(board[1][1]) &&
                board[1][2]==0)
        {
            board[1][2]= 2;
            return true;
        }
        else if(board[1][0]==2 &&  board[1][0]==(board[1][2]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[1][2]==2 &&  board[1][2]==(board[1][1]) &&
                board[1][0]==0)
        {
            board[1][0]= 2;
            return true;
        }

        ///test3 (across in bottom row)
        if(board[2][0]==2 &&  board[2][0]==(board[2][1]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[2][0]==2 &&  board[2][0]==(board[2][2]) &&
                board[2][1]==0)
        {
            board[2][1]= 2;
            return true;
        }
        else if(board[2][2]==2 &&  board[2][2]==(board[2][1]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }

        ///test4 (down in first column)
        if(board[0][0]==2 &&  board[0][0]==(board[1][0]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }
        else if(board[0][0]==2 &&  board[0][0]==(board[2][0]) &&
                board[1][0]==0)
        {
            board[1][0]= 2;
            return true;
        }
        else if(board[2][0]==2 &&  board[2][0]==(board[1][0]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test5 (down in second column)
        if(board[0][1]==2 &&  board[0][1]==(board[1][1]) &&
                board[2][1]==0)
        {
            board[2][1]= 2;
            return true;
        }
        else if(board[0][1]==2 &&  board[0][1]==(board[2][1]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][1]==2 &&  board[2][1]==(board[1][1]) &&
                board[0][1]==0)
        {
            board[0][1]= 2;
            return true;
        }

        ///test6 (down in third column)
        if(board[0][2]==2 &&  board[0][2]==(board[1][2]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[0][2]==2 &&  board[0][2]==(board[2][2]) &&
                board[1][2]==0)
        {
            board[1][2]= 2;
            return true;
        }
        else if(board[2][2]==2 &&  board[2][2]==(board[1][2]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }

        ///test7 (diagonal from left to right)
        if(board[0][0]==2 &&  board[0][0]==(board[1][1]) &&
                board[2][2]==0)
        {
            board[2][2]= 2;
            return true;
        }
        else if(board[0][0]==2 &&  board[0][0]==(board[2][2]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][2]==2 &&  board[2][2]==(board[1][1]) &&
                board[0][0]==0)
        {
            board[0][0]= 2;
            return true;
        }

        ///test8 (diagonal from right to left)
        if(board[0][2]==2 &&  board[0][2]==(board[1][1]) &&
                board[2][0]==0)
        {
            board[2][0]= 2;
            return true;
        }
        else if(board[0][2]==2 &&  board[0][2]==(board[2][0]) &&
                board[1][1]==0)
        {
            board[1][1]= 2;
            return true;
        }
        else if(board[2][0]==2 &&  board[2][0]==(board[1][1]) &&
                board[0][2]==0)
        {
            board[0][2]= 2;
            return true;
        }
        return false;

    }

    public boolean didHumanWin() {
        ///test 1 (across in top row)
        if(board[0][0]==1 &&  board[0][0]==(board[0][1]) &&
                board[0][1]== (board[0][2]))
        {
            return true;
        }
        ///test2 (across in middle row)
        if(board[1][0]==1&&  board[1][0]==(board[1][1]) &&
                board[1][1]==(board[1][2]))
        {
            return true;
        }
        ///test3 (across in bottom row)
        if(board[2][0]==1 &&  board[2][0]==(board[2][1]) &&
                board[2][1]==(board[2][2]))
        {
            return true;
        }
        ///test4 (down in first column)
        if(board[0][0]==1 &&  board[0][0]==(board[1][0]) &&
                board[1][0]==(board[2][0]))
        {
            return true;
        }

        ///test5 (down in second column)
        if(board[0][1]==1 &&  board[0][1]==(board[1][1]) &&
                board[1][1]==(board[2][1]))
        {
            return true;
        }
        ///test6 (down in third column)
        if(board[0][2]==1 &&  board[0][2]==(board[1][2]) &&
                board[1][2]==(board[2][2]))
        {
            return true;
        }
        ///test7 (diagonal from left to right)
        if(board[0][0]==1 &&  board[0][0]==(board[1][1]) &&
                board[1][1]==(board[2][2]))
        {
            return true;
        }
        ///test8 (diagonal from right to left)
        if(board[0][2]==1&&  board[0][2]==(board[1][1]) &&
                board[1][1]==(board[2][0]))
        {
            return true;
        }

        return false;
    }

    public boolean didComputerWin() {
        ///test 1 (across in top row)
        if(board[0][0]==2 &&  board[0][0]==(board[0][1]) &&
                board[0][1]== (board[0][2]))
        {
            return true;
        }
        ///test2 (across in middle row)
        if(board[1][0]==2&&  board[1][0]==(board[1][1]) &&
                board[1][1]==(board[1][2]))
        {
            return true;
        }
        ///test3 (across in bottom row)
        if(board[2][0]==2 &&  board[2][0]==(board[2][1]) &&
                board[2][1]==(board[2][2]))
        {
            return true;
        }
        ///test4 (down in first column)
        if(board[0][0]==2 &&  board[0][0]==(board[1][0]) &&
                board[1][0]==(board[2][0]))
        {
            return true;
        }

        ///test5 (down in second column)
        if(board[0][1]==2 &&  board[0][1]==(board[1][1]) &&
                board[1][1]==(board[2][1]))
        {
            return true;
        }
        ///test6 (down in third column)
        if(board[0][2]==2 &&  board[0][2]==(board[1][2]) &&
                board[1][2]==(board[2][2]))
        {
            return true;
        }
        ///test7 (diagonal from left to right)
        if(board[0][0]==2 &&  board[0][0]==(board[1][1]) &&
                board[1][1]==(board[2][2]))
        {
            return true;
        }
        ///test8 (diagonal from right to left)
        if(board[0][2]==2&&  board[0][2]==(board[1][1]) &&
                board[1][1]==(board[2][0]))
        {
            return true;
        }

        return false;
    }

    public boolean didGameTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if(board[r][c] == 0){
                    return false;
                }

            }
        }

        return true;
    }
}


