package krobertsoncsc335.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
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
