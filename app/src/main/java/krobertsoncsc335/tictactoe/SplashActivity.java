package krobertsoncsc335.tictactoe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private MediaPlayer mySound; //move to onCreate in menu options (if you end up creating that class)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mySound = MediaPlayer.create(this, R.raw.introgamesound);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    mySound.start();
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
