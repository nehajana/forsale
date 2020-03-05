package shubham.com.foursale.ForgotScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.OtpForgetPasswordScreen.OtpForgetActivity;
import shubham.com.foursale.R;

public class ForgotActivity extends AppCompatActivity {
    ImageView imgbkarr;
    RelativeLayout rll_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        findiew();

        imgbkarr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rll_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(ForgotActivity.this, HomeActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void findiew()
    {
        imgbkarr = (ImageView) findViewById(R.id.imgbkarr);
        rll_next = (RelativeLayout) findViewById(R.id.rll_next);
    }
}
