package umbandung.com.digitalhomecare;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen  extends AppCompatActivity {

    private static int LOADING_TIME = 1500;
    AlertDialog bilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bilder = new AlertDialog.Builder(this).create();

        new Handler().postDelayed(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SplashScreen.this, Login.class); // Arahkan
                // class
                // Tujuan,
                // (Ganti
                // Main.class
                // dengan
                // class
                // tujuan)
                SplashScreen.this.startActivity(intent);
                SplashScreen.this.finish();

            }
        }, LOADING_TIME);
    }

}