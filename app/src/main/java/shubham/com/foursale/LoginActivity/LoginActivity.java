package shubham.com.foursale.LoginActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.foursale.HomeFragment.HomeFragment;
import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.LoginActivity.ApiModel.LoginDataModel;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class LoginActivity extends AppCompatActivity{

    RelativeLayout RR_next;
    EditText edmobile;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RR_next=(RelativeLayout) findViewById(R.id.RR_next);
        edmobile=(EditText) findViewById(R.id.ed_mobile);

        RR_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               validation();
            }
        });

    }

    private void validation(){

        mobile=edmobile.getText().toString();


        if(mobile.equalsIgnoreCase("")){
            Toast.makeText(LoginActivity.this, "Please enter your mobile.", Toast.LENGTH_SHORT).show();
        }
        else {
            Preference.save(this,Preference.KEY_USER_Mobile,mobile);

            Intent myIntent = new Intent( LoginActivity.this, SignInActivity.class );
            myIntent.putExtra("phonenumber",mobile);

            startActivity( myIntent );
        }
    }


}
