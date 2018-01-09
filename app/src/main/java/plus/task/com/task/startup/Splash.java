package plus.task.com.task.startup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import plus.task.com.task.R;
import plus.task.com.task.activity.First;
import plus.task.com.task.utility.Constant;
import plus.task.com.task.utility.GlobalActivity;

public class Splash extends GlobalActivity {
    SharedPreferences preferences;
    private ProgressBar mProgressBar;
    String user_id;
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences = getSharedPreferences(Constant.PreferenceManager.PREF_NAME, MODE_PRIVATE);
        user_id = preferences.getString(Constant.PreferenceManager.USER_STATUS, "empty");
        initView();

    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setTypeface(Regular);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isNetAvailable) {
                    if (user_id.equals("empty")) {
                        startActivity(new Intent(getApplicationContext(), First.class));
                        finish();
                    } else if (user_id.equals("1")) {
                        startActivity(new Intent(getApplicationContext(), First.class));
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Interet Connection Not Available", Toast.LENGTH_SHORT).show();
                }

            }
        }, Constant.PreferenceManager.SPLASH_DISPLAY_LENGTH);
    }
}
