package plus.task.com.task.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;

import plus.task.com.task.R;

public class GlobalActivity extends AppCompatActivity {

    public Animation animation_slide_in_right;
    public Typeface Light, Regular;
    public ConnectionDetector connectionDetector;
    public boolean isInternetAvailable, isNetAvailable;
    public ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectionDetector = new ConnectionDetector(getApplicationContext());
        animation_slide_in_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Light = Typeface.createFromAsset(getAssets(),"Ubuntu-Light.ttf");
        Regular = Typeface.createFromAsset(getAssets(),"Ubuntu-Regular.ttf");
        isInternetAvailable = connectionDetector.isConnectingToInternet();
        isNetAvailable = connectionDetector.isNetworkAvailable();
    }

    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void hideKeyBoard(Context context){
        if (getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null){
                InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        }
    }
    public void showProgressDialog(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressDialog(){
        if (progressDialog!=null){
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
    public ConnectionDetector getInternetConnection(){
        return  connectionDetector;
    }

}