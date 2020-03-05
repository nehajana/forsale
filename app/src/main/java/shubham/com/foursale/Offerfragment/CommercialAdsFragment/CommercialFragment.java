package shubham.com.foursale.Offerfragment.CommercialAdsFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import shubham.com.foursale.DetailProductActivity.ApiModel.DetailsModel;
import shubham.com.foursale.DetailProductActivity.ApiModel.Details_DataModel;
import shubham.com.foursale.DetailProductActivity.DetailsProductActivity;
import shubham.com.foursale.MessageFragment.MessageFragment;
import shubham.com.foursale.Offerfragment.ApiModel.OfferDataModel;
import shubham.com.foursale.Offerfragment.ApiModel.OfferModel;
import shubham.com.foursale.Offerfragment.OfferFragment;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;


public class CommercialFragment extends Fragment implements IApiResponse {

    private View rootView;
    private RecyclerView recyclerView;
    private Commercial_RecyclerViewAdapter mAdapter;
    private ArrayList<OfferDataModel> modelList = new ArrayList<>();
    ProgressBar progressbar;
    String id;

    public static CommercialFragment newInstance(String param1, String param2) {
        CommercialFragment fragment = new CommercialFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_commercialfragment, container, false);

        //setAdapter_Schdule();
        findViews();

        commercialdata(id);
        return  rootView;
    }



    private void findViews() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        progressbar=(ProgressBar) rootView.findViewById(R.id.progressbar);
    }

    private void setAdapter(ArrayList<OfferDataModel> modelList) {

        mAdapter = new Commercial_RecyclerViewAdapter(getActivity(), modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new Commercial_RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, OfferDataModel model) {

            }
        });

    }

    private void commercialdata(String id)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("id","1");
        map.put("language","english");

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_GETOFFERS, Constants.USER_GETOFFERS,map, Request.Method.POST);

    }
    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_GETOFFERS.equalsIgnoreCase(tag_json_obj)){

            if (response !=null) {

             progressbar.setVisibility(View.GONE);

                OfferModel finalArray = new Gson().fromJson(response, new TypeToken<OfferModel>(){}.getType());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("true")) {

                    modelList= (ArrayList<OfferDataModel>) finalArray.getResult();

                    setAdapter(modelList);

                } else {

                }
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("null response");

       // progressbar.setVisibility(View.GONE);

        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
    }
}
