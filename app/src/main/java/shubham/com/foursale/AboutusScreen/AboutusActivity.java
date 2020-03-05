package shubham.com.foursale.AboutusScreen;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import shubham.com.foursale.AboutusScreen.ApiModel.AboutusDataModel;
import shubham.com.foursale.AboutusScreen.ApiModel.AboutusModel;
import shubham.com.foursale.MoreFragment.ApiModel.ProfileDataModel;
import shubham.com.foursale.MoreFragment.ApiModel.ProfileModel;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class AboutusActivity extends AppCompatActivity implements IApiResponse {

    TextView txt_view;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        findview();

        aboutusdata();
    }
    private void findview()
    {
        txt_view = findViewById(R.id.txt_view);
    }

    private void aboutusdata()
    {
        ApiRequest apiRequest = new ApiRequest(this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_ABOUTUS, Constants.USER_ABOUTUS, Request.Method.GET);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj)
    {
        if (Constants.USER_ABOUTUS.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                AboutusModel finalArray = new Gson().fromJson(response, new TypeToken<AboutusModel>(){}.getType());

                Boolean status = finalArray.getStatus();

                if(status) {
                    Toast.makeText(this, "status"+status, Toast.LENGTH_SHORT).show();
                    AboutusDataModel aboutusDataModel = finalArray.getResult();

                    message = aboutusDataModel.getMessage();
                    //txt_view.setText(message);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txt_view.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        txt_view.setText(Html.fromHtml(message));
                    }

                } else {
                    Toast.makeText(this, "" + finalArray.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
