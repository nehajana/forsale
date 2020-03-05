package shubham.com.foursale.SubProductDetails;

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
import shubham.com.foursale.DetailProductActivity.DetailsProductActivity;
import shubham.com.foursale.R;
import shubham.com.foursale.SubProductDetails.ApiModel.SubProductDataModel;
import shubham.com.foursale.SubProductDetails.ApiModel.SubProductModel;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class SubProductActivityDetails extends AppCompatActivity implements IApiResponse {

    private RecyclerView recyclerView;
    RelativeLayout rll_backarrow;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    private ArrayList<SubProductDataModel> productDataModels = new ArrayList<>();
    String sub_category_id;
    private SubProductDetailsRecyclerViewAdapter mAdapter;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_product_details);


        // ButterKnife.bind(this);
        findViews();

        Intent intent=getIntent();
        if(intent !=null)
        {
            sub_category_id=intent.getStringExtra("sub_category_id");
            System.out.println("sub_category_id :- "+sub_category_id);
        }
        productdata(sub_category_id);
        progressbar.setVisibility(View.VISIBLE);
        rll_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void findViews() {
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rll_backarrow = (RelativeLayout) findViewById(R.id.rll_backarrow);
    }

    private void setAdapter(ArrayList<SubProductDataModel> productDataModels) {

        mAdapter = new SubProductDetailsRecyclerViewAdapter(SubProductActivityDetails.this, this.productDataModels);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new SubProductDetailsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubProductDataModel model) {

            }
        });


    }
    private void productdata(String sub_id)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("sub_id",sub_id);
        map.put("language","english");

        ApiRequest apiRequest = new ApiRequest(SubProductActivityDetails.this,this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_SUB_SUBCATEGORY, Constants.USER_SUB_SUBCATEGORY,map, Request.Method.POST);

    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_SUB_SUBCATEGORY.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

               progressbar.setVisibility(View.GONE);

                SubProductModel finalArray = new Gson().fromJson(response, new TypeToken<SubProductModel>(){}.getType());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("true")) {

                    productDataModels= (ArrayList<SubProductDataModel>) finalArray.getResult();
                    setAdapter(productDataModels);

                } else {
                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressbar.setVisibility(View.GONE);
    }
}
