package shubham.com.foursale.SigninScreen;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.foursale.ForgotScreen.ForgotActivity;
import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.LoginActivity.LoginActivity;
import shubham.com.foursale.OtpForgetPasswordScreen.OtpForgetActivity;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.ApiModel.LoginModel;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class SignInActivity extends AppCompatActivity implements IApiResponse {
    RelativeLayout rll_forgot_pswrd;
    RelativeLayout rll_arrow;
    String password;
    TextInputEditText txt_password;
    RelativeLayout btn_sign;
    String phoneno="";

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        findview();

        Intent intent = getIntent();

        if(intent !=null) {

            phoneno = intent.getStringExtra("phonenumber");
            System.out.println("phoneno :"+phoneno);
        }


       btn_sign.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {

                password = txt_password.getText().toString();

                if(password.equalsIgnoreCase(""))
                {
                    Toast.makeText(SignInActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(password.length()<3)
                {
                    Toast.makeText(SignInActivity.this, "please enter  valid password", Toast.LENGTH_SHORT).show();
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);

                    LoginMethod(phoneno,password);
                }
            }
        });
        rll_forgot_pswrd.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(SignInActivity.this,
                        OtpForgetActivity.class);
                startActivity(myIntent);
            }
        });
        rll_arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onBackPressed();
            }
        });
       /* btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SignInActivity.this,
                        HomeActivity.class);
                startActivity(myIntent);
            }
        });*/
    }

    private void findview()
    {

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        rll_forgot_pswrd= (RelativeLayout) findViewById( R.id.rll_forgot_pswrd );
        txt_password= (TextInputEditText) findViewById( R.id.txt_password );
        btn_sign= (RelativeLayout) findViewById( R.id.rll_signinbtn);
        rll_arrow= (RelativeLayout) findViewById( R.id.rll_arrow );
    }
    private void LoginMethod(String phoneno, String password)
    {
        HashMap<String, String> map = new HashMap<>();

        map.put("phone",phoneno);

        map.put("password",password);

        ApiRequest apiRequest = new ApiRequest(SignInActivity.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_LOGIN, Constants.USER_LOGIN,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj)
    {
        if (Constants.USER_LOGIN.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                LoginModel finalArray = new Gson().fromJson(response, new TypeToken<LoginModel>(){}.getType());

               Toast.makeText(this, finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                //  System.out.println("message : --  "+finalArray.getMessage());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("Success")) {

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(this, "" + finalArray.getMessage(), Toast.LENGTH_SHORT).show();

                    String userid=finalArray.getUserId().toString();

                    Preference.save(SignInActivity.this,Preference.KEY_USER_ID,userid);

                    Intent myIntent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(myIntent);

                } else {

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(this, "" + finalArray.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("null response");

        progressBar.setVisibility(View.GONE);

        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
