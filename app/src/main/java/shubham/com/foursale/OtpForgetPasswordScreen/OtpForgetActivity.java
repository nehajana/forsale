package shubham.com.foursale.OtpForgetPasswordScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
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
import shubham.com.foursale.OtpForgetPasswordScreen.ApiModel.OtpForgetModel;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.ApiModel.LoginModel;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class OtpForgetActivity extends AppCompatActivity implements IApiResponse {

    RelativeLayout rll_arrow;
    RelativeLayout rll_next;
    String mobile;
    ProgressBar progressBar;
    EditText edt_mobilenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_forget);

      //  mobile= Preference.get(this,Preference.KEY_USER_Mobile);



        findview();
        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rll_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                mobile= edt_mobilenumber.getText().toString();
                System.out.println("mobile :"+mobile);

                if(mobile.equalsIgnoreCase(""))
                {
                    Toast.makeText(OtpForgetActivity.this, "Please enter mobile no.", Toast.LENGTH_SHORT).show();
                }else
                {
                    Intent myIntent = new Intent(OtpForgetActivity.this, ForgotActivity.class);
                    startActivity(myIntent);
                }
               // otpforgetdata(mobile);
            }
        });
    }

    private void findview()
    {
        rll_arrow = (RelativeLayout) findViewById(R.id.rll_arrow);
        rll_next = (RelativeLayout) findViewById(R.id.rll_next);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        edt_mobilenumber=(EditText) findViewById(R.id.edt_mobilenumber);
    }

    private void otpforgetdata(String mobile)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",mobile);

        ApiRequest apiRequest = new ApiRequest(this,this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_FORGETPASSWORD, Constants.USER_FORGETPASSWORD,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj)
    {
        if (Constants.USER_FORGETPASSWORD.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                OtpForgetModel finalArray = new Gson().fromJson(response, new TypeToken<OtpForgetModel>(){}.getType());
                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("Success")) {

                    progressBar.setVisibility(View.GONE);

                   // String userid=finalArray.getUserId().toString();

                    Intent myIntent = new Intent(OtpForgetActivity.this, ForgotActivity.class);
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
