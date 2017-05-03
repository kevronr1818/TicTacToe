package krobertsoncsc335.tictactoe;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    GameSurfaceView surfaceView;
    Point touched = new Point();
    boolean wasTouched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //touched = new Point();
        wasTouched = false;

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        surfaceView = new GameSurfaceView(this);

        // Set the view content to our surface view, not R.layout.activity_game
        setContentView(surfaceView);

    }

    public void showHumanWon(){
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("result", getResources().getString(R.string.human_won));
        startActivity(intent);
        finish();
    }

    public void showComputerWon(){
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("result", getResources().getString(R.string.computer_won));
        startActivity(intent);
        finish();
    }

    public void showTie(){
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("result", getResources().getString(R.string.tie));
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                wasTouched = true;
                touched.x = (int) event.getX();
                touched.y = (int) event.getY();
                break;
        }

        return true;
    }

    public Point getTouch() {
        wasTouched = false;
        return touched;
    }

    public void onResume() {
        super.onResume();
        surfaceView.onResume();

    }

    public void onPause() {
        super.onPause();
        surfaceView.onPause();
    }


}
