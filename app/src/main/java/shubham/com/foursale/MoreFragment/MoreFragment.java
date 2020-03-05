package shubham.com.foursale.MoreFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.foursale.AboutusScreen.AboutusActivity;
import shubham.com.foursale.AgentsScreen.AgentsActivity;
import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.MoreFragment.ApiModel.ProfileDataModel;
import shubham.com.foursale.MoreFragment.ApiModel.ProfileModel;
import shubham.com.foursale.Notification.NotificationActivity;
import shubham.com.foursale.Offerfragment.ApiModel.OfferDataModel;
import shubham.com.foursale.PaymentHistotry.PaymentHistory;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.ApiModel.LoginModel;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.TermsCondition.TermsCondtion;
import shubham.com.foursale.UpdateProfile.UpdatedProfileActivity;
import shubham.com.foursale.UpdateProfileScreen.UpdateProfileActivity;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;


public class MoreFragment extends Fragment implements IApiResponse {

    private View rootView;

    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    ImageView img_bell;
    RelativeLayout RR_agent;
    RelativeLayout RR_aboutus;
    RelativeLayout RR_terms_conditon;
    RelativeLayout rll_updateprofile;
    LinearLayout payment_history;
    ImageView img_profile;
    EditText edt_username;
    EditText edt_email;
    String user_id;
    String name;
    String email;
    TextView txt_terms_condition;
    ArrayList<ProfileDataModel> profilelist = new  ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_morefragment, container, false);

        //setAdapter_Schdule();

        findview();

        RR_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), AgentsActivity.class);
                startActivity(intent);
            }
        });

        payment_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),PaymentHistory.class);
                startActivity(intent);
            }
        });

        RR_terms_conditon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),TermsCondtion.class);
                startActivity(intent);
            }
        });

        img_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NotificationActivity.class);
                startActivity(intent);
            }
        });
        RR_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AboutusActivity.class);
                startActivity(intent);
            }
        });
        rll_updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        edt_username.setEnabled(false);
        edt_email.setEnabled(false);

        user_id= Preference.get(getActivity(),Preference.KEY_USER_ID);

        profiledata(user_id);

        return  rootView;
    }

    private void findview()
    {
        payment_history=(LinearLayout) rootView.findViewById(R.id.payment_history);
        RR_agent=(RelativeLayout) rootView.findViewById(R.id.RR_agent);
        rll_updateprofile=(RelativeLayout) rootView.findViewById(R.id.rll_updateprofile);
        RR_aboutus=(RelativeLayout) rootView.findViewById(R.id.RR_aboutus);
        RR_terms_conditon=(RelativeLayout) rootView.findViewById(R.id.RR_terms_conditon);
        img_bell=(ImageView) rootView.findViewById(R.id.img_bell);
        img_profile=(ImageView) rootView.findViewById(R.id.img_profile);
        edt_username=(EditText) rootView.findViewById(R.id.edt_username);
        edt_email=(EditText) rootView.findViewById(R.id.edt_email);
        txt_terms_condition=(TextView) rootView.findViewById(R.id.txt_terms_condition);
    }

    private void profiledata(String user_id)
    {
        HashMap<String, String> map = new HashMap<>();

        map.put("user_id",user_id);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_GETPROFILE, Constants.USER_GETPROFILE,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj)
    {
        if (Constants.USER_GETPROFILE.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                ProfileModel finalArray = new Gson().fromJson(response, new TypeToken<ProfileModel>(){}.getType());

                Boolean status = finalArray.getStatus();

                if(status) {

                    ProfileDataModel profileDataModel = finalArray.getResult();
                    name= profileDataModel.getFirstname().toString();
                    email= profileDataModel.getEmail().toString();
                    String imageurl= profileDataModel.getUserprofile().toString();

                  Picasso.with(getActivity()).load(Constants.IMAGE_URL+imageurl)
                            .placeholder(R.drawable.ad1)
                            .into(img_profile);

                    edt_username.setText(name);
                    edt_email.setText(email);


                } else {
                    Toast.makeText(getActivity(), "" + finalArray.getStatus(), Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }
}
