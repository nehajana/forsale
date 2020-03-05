package shubham.com.foursale.AgentsScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import shubham.com.foursale.AgentsScreen.Apimodel.AgentsData_model;
import shubham.com.foursale.AgentsScreen.Apimodel.Agents_model;
import shubham.com.foursale.HomeFragment.ApiModel.AdvertisementDataModel;
import shubham.com.foursale.HomeFragment.ApiModel.AdvertisementModel;
import shubham.com.foursale.HomeFragment.ApiModel.Featured;
import shubham.com.foursale.HomeFragment.ApiModel.HomeMainModel;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class AgentsActivity extends AppCompatActivity implements IApiResponse {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;


    private AgentsRecyclerViewAdapter mAdapter;

    private ArrayList<AgentsData_model> modelList = new ArrayList<>();
    RelativeLayout rll_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);

        // ButterKnife.bind(this);
        findViews();

        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getAgents();

    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rll_arrow = (RelativeLayout) findViewById(R.id.rll_arrow);
    }


    private void setAdapter(ArrayList<AgentsData_model> modelList) {

        mAdapter = new AgentsRecyclerViewAdapter(AgentsActivity.this, this.modelList);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new AgentsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, AgentsData_model model) {

                //handle item click events here
               // Toast.makeText(AgentsActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getAgents()
    {

        HashMap<String, String> map = new HashMap<>();

        ApiRequest apiRequest = new ApiRequest(AgentsActivity.this,this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_getagent, Constants.USER_getagent,map, Request.Method.GET);
    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_getagent.equalsIgnoreCase(tag_json_obj)) {

            if (response != null) {

                Agents_model finalArray = new Gson().fromJson(response, new TypeToken<Agents_model>() {
                }.getType());

                Boolean status=finalArray.getStatus();

                if(status)
                {
                    modelList= (ArrayList<AgentsData_model>) finalArray.getResult();

                    setAdapter(modelList);

                }

            }
        }

    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(AgentsActivity.this, "Please check network", Toast.LENGTH_SHORT).show();
    }

}
