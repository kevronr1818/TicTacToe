package krobertsoncsc335.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kevron on 3/31/2017.
 */

public class GameSurfaceView extends SurfaceView implements Runnable {
    private final GameActivity gameActivity;
    boolean isRunning = false;     // true when activity is active and running
    private Thread thread = null;  // the thread that's doing the drawing.
    private GameBoard gameBoard = new GameBoard();
    private final MediaPlayer touchSound;
    private Bitmap letterOBitmap;
    private Bitmap letterXBitmap;
    private int positionX, positionY;

    public GameSurfaceView(Context context) {
        super(context);
        gameActivity = (GameActivity)context;
        positionX = (int)(50 * DisplayAdvisor.scaleX);
        positionY = (int)(50 * DisplayAdvisor.scaleY);
        letterOBitmap = DisplayAdvisor.loadScaledToIdeal(gameActivity.getResources(), (int)(100* DisplayAdvisor.scaleX), (int)(100 * DisplayAdvisor.scaleY), R.drawable.letter_o);
        letterXBitmap = DisplayAdvisor.loadScaledToIdeal(gameActivity.getResources(), (int)(100* DisplayAdvisor.scaleX), (int)(100 * DisplayAdvisor.scaleY), R.drawable.letter_x);
        touchSound = MediaPlayer.create(gameActivity, R.raw.gametouchsound);
        gameBoard.setGameActivity(gameActivity);


    }

    public void run() {
        while (isRunning) {
            // Get the canvas
            SurfaceHolder surfaceHolder = getHolder();
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surfaceHolder.lockCanvas();

            if(gameActivity.wasTouched){
                Point p = gameActivity.getTouch();
                gameBoard.handleMove(p);

                touchSound.start();

                if(gameBoard.didHumanWin()) {
                    //start new activity that says who won
                    gameActivity.showHumanWon();

                }
                else {
                    gameBoard.handleComputerMove();

                    if(gameBoard.didComputerWin()){
                        gameActivity.showComputerWon();
                    }
                }

                if(gameBoard.didGameTie()){
                    gameActivity.showTie();
                }
            }

            drawEverything(canvas);

            // Display our canvas
            surfaceHolder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(175);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawEverything(Canvas canvas) {
        canvas.drawARGB(255, 255, 0, 0);
        gameBoard.drawBoard(canvas);
        gameBoard.drawMarkers(canvas);

    }


    public void onResume() {
        // We've become active.  Create a thread
        // and start drawing.
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPause() {
        // We're no longer active.
        isRunning = false;  // This will stop the loop in run and thread will end.
        while (thread != null) {
            try {
                thread.join();  // Wait for thread to end.
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}