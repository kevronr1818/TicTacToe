package krobertsoncsc335.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtResult = (TextView)findViewById(R.id.txtResult);

        Intent intent = getIntent();

        String result = intent.getStringExtra("result");
        txtResult.setText(result);
    }

    public void playAgainClicked (View view){
        Intent i = getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(getApplicationContext().getPackageName() );

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(i);

    }
}
