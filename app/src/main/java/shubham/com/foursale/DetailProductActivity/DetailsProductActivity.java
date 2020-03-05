package shubham.com.foursale.DetailProductActivity;

import android.content.Intent;
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

import shubham.com.foursale.DetailProductActivity.ApiModel.DetailsModel;
import shubham.com.foursale.DetailProductActivity.ApiModel.Details_DataModel;
import shubham.com.foursale.HomeScreen.HomeActivity;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.ApiModel.LoginModel;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.SubProductDetails.SubProductActivityDetails;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class DetailsProductActivity extends AppCompatActivity implements IApiResponse {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<Details_DataModel> modelList = new ArrayList<>();
    String category_id;
    ImageView img_back;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);

        findViews();

        Intent intent=getIntent();
        if(intent !=null)
        {
            category_id=intent.getStringExtra("category_id");
            System.out.println("category_id :- "+category_id);
        }
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        progressbar.setVisibility(View.VISIBLE);
        detailsdata(category_id);
    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        img_back=(ImageView) findViewById(R.id.img_back);
        progressbar=(ProgressBar) findViewById(R.id.progressbar);
    }

    private void setAdapter(ArrayList<Details_DataModel> modelList) {

        mAdapter = new RecyclerViewAdapter(DetailsProductActivity.this, this.modelList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Details_DataModel model) {

                Intent intent=new Intent(DetailsProductActivity.this,SubProductActivityDetails.class);
                intent.putExtra("sub_category_id",model.getSubId().toString());
                startActivity(intent);

            }
        });
    }


    private void detailsdata(String category_id)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("category_id",category_id);
        map.put("language","english");

        ApiRequest apiRequest = new ApiRequest(DetailsProductActivity.this,this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_SUBCATEGORY, Constants.USER_SUBCATEGORY,map, Request.Method.POST);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_SUBCATEGORY.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

                progressbar.setVisibility(View.GONE);

                DetailsModel finalArray = new Gson().fromJson(response, new TypeToken<DetailsModel>(){}.getType());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("true")) {

                    modelList= (ArrayList<Details_DataModel>) finalArray.getResult();

                    setAdapter(modelList);

               /*     Details_DataModel detailsModel = (Details_DataModel) finalArray.getResult();
                    name= detailsModel.getName().toString();
                    image = detailsModel.getImage();
                    subId = detailsModel.getSubId().toString();


                    detailsModel.setName(detailsModel.getName());*/
                } else {

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("null response");

        progressbar.setVisibility(View.GONE);

        Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
    }
}
