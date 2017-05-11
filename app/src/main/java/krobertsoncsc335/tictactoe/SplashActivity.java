package krobertsoncsc335.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class SplashActivity extends AppCompatActivity {
    private MediaPlayer introGameSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        introGameSound = MediaPlayer.create(this, R.raw.introgamesound);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        DisplayAdvisor.setScreenDimmensions(metrics);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    introGameSound.start();
                } catch (InterruptedException e) {
                } finally {
                    Intent openMainActivity = new Intent("krobertsoncsc335.GAMEACTIVITY");
                    startActivity(openMainActivity);
                }
            }
        };

        timer.start();
    }


}
