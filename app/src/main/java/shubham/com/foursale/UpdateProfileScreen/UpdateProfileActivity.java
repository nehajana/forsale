package shubham.com.foursale.UpdateProfileScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.ApiModel.LoginModel;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.UpdateProfileScreen.ApiModel.UpdateProfileModel;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class UpdateProfileActivity extends AppCompatActivity implements IApiResponse {

    ImageView img_profile;
    EditText  edt_name;
    EditText edt_email;
    EditText edt_phone;
    EditText edt_address;
    EditText edt_city;
    EditText edt_country;
    EditText edt_zipcode;
    ProgressBar progressBar;
    Button btn_signup;
     String user_id;
     String firstname;
     String email;
     String phone;
     String address;
     String city;
     String country;
     String zipcode;
     RelativeLayout rll_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        findview();

        user_id= Preference.get(this,Preference.KEY_USER_ID);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // progressBar.setVisibility(View.VISIBLE);
                firstname = edt_name.getText().toString();
                email = edt_email.getText().toString();
                phone = edt_phone.getText().toString();
                address = edt_address.getText().toString();
                city = edt_city.getText().toString();
                country = edt_country.getText().toString();
                zipcode = edt_zipcode.getText().toString();

                updateprofiledata(user_id,firstname,email,phone,address,city,country,zipcode);
            }
        });
        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void findview()
    {
        edt_name= (EditText) findViewById(R.id.edt_name);
        edt_email= (EditText) findViewById(R.id.edt_email);
        edt_phone= (EditText) findViewById(R.id.edt_phone);
        edt_address= (EditText) findViewById(R.id.edt_address);
        edt_city= (EditText) findViewById(R.id.edt_city);
        edt_country= (EditText) findViewById(R.id.edt_country);
        edt_zipcode= (EditText) findViewById(R.id.edt_zipcode);
        img_profile= (ImageView) findViewById(R.id.img_profile);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        btn_signup= (Button) findViewById(R.id.btn_signup);
        rll_arrow= (RelativeLayout) findViewById(R.id.rll_arrow);
    }

    private void updateprofiledata(String user_id, String firstname, String email, String phone, String address, String city,
                                   String country, String zipcode)
    {
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);
        map.put("firstname",firstname);
        map.put("email",email);
        map.put("phone",phone);
        map.put("shipping_address",address);
        map.put("city",city);
        map.put("country",country);
        map.put("zipcode",zipcode);

        ApiRequest apiRequest = new ApiRequest(UpdateProfileActivity.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_UPDATEPROFILE, Constants.USER_UPDATEPROFILE,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj)
    {
        if (Constants.USER_UPDATEPROFILE.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                UpdateProfileModel finalArray = new Gson().fromJson(response, new TypeToken<UpdateProfileModel>(){}.getType());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("Success")) {

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(this, "" + finalArray.getMessage(), Toast.LENGTH_SHORT).show();

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
