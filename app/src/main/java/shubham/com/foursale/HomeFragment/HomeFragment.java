package shubham.com.foursale.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import shubham.com.foursale.AdvanceSearchScreen.AdvanceSearchActivity;
import shubham.com.foursale.DetailProductActivity.DetailsProductActivity;
import shubham.com.foursale.HomeFragment.ApiModel.AdvertisementDataModel;
import shubham.com.foursale.HomeFragment.ApiModel.AdvertisementModel;
import shubham.com.foursale.HomeFragment.ApiModel.Featured;
import shubham.com.foursale.HomeFragment.ApiModel.HomeMainModel;
import shubham.com.foursale.HomeFragment.ApiModel.Service;
import shubham.com.foursale.HomeFragment.featuring.FeaturingRecyclerViewAdapter;
import shubham.com.foursale.HomeFragment.featuring.ServiceRecyclerViewAdapter;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;


public class HomeFragment extends Fragment implements IApiResponse{

    private View rootView;
    private static ViewPager mPager;
    ArrayList<String> image_list;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    Timer swipeTimer;
    ImageView img_search;

    String value1 ="Featured";
    String value2 ="Service";
    String value3 ="Family";
    String value4 ="Furniture";
    String value5 ="Animals $ Pets";
    String value6 ="Camping Equipment";
    String value7 ="Jobs";
    String value8 ="Teaching $ Traning";
    String value9 ="Your Health";
    String value10 ="Foods";
    String value11 ="Miscellaneous";


    private RecyclerView recycler_view_featured;
    private RecyclerView recycler_view_services;
    private RecyclerView recycler_view_family;
    private RecyclerView recycler_view_furniture;
    private RecyclerView recycler_view_add;
    private RecyclerView recycler_view_add_one;

    private RecyclerView recycler_view_annimals_pets;
    private RecyclerView recycler_view_camping_equipments;
    private RecyclerView recycler_view_jobs;
    private RecyclerView recycler_view_teaching_traning;
    private RecyclerView recycler_view_your_health;
    private RecyclerView recycler_view_foods;
    private RecyclerView recycler_view_miscellaneous;

    private RecyclerView recycler_view_teaching_traning_add;
    private RecyclerView recycler_view_food_add;
    private RecyclerView recycler_view_camping_equipments_add;

    private FeaturingRecyclerViewAdapter mAdapter;
    private ServiceRecyclerViewAdapter mServiceAdapter;
    private AddRecyclerViewAdapter mAdapter_add;
    private SlidingImage_Adapter mSlidingimageAdapter;

    RelativeLayout rll_imgsearch_featured;
    RelativeLayout rll_imgsearch_services;
    RelativeLayout rll_imgsearch_family;
    RelativeLayout rll_imgsearch_furniture;
    RelativeLayout rll_imgsearch_animals;
    RelativeLayout rll_imgsearch_camping;
    RelativeLayout rll_imgsearch_jobs;
    RelativeLayout rll_imgsearch_teaching;
    RelativeLayout rll_imgsearch_health;
    RelativeLayout rll_imgsearch_foods;
    RelativeLayout rll_imgsearch_miscellaneou;

    private ArrayList<Featured>  modelList_annimals = new ArrayList<>();
    private ArrayList<Featured> modelList_camping_equipments = new ArrayList<>();
    private ArrayList<Featured> modelList_family = new ArrayList<>();
    private ArrayList<Featured> modelList_featured = new ArrayList<>();
    private ArrayList<Featured> modelList_foods = new ArrayList<>();
    private ArrayList<Featured> modelList_furniture = new ArrayList<>();
    private ArrayList<Featured> modelList_jobs = new ArrayList<>();
    private ArrayList<Featured> modelList_miscellaneous = new ArrayList<>();
    private ArrayList<Featured> modelList_services = new ArrayList<>();
    private ArrayList<Featured> modelList_teaching_traning = new ArrayList<>();
    private ArrayList<Featured> modelList_your_health = new ArrayList<>();

    private ArrayList<AdvertisementDataModel> modelList_advertisement_images = new ArrayList<>();

    private ArrayList<AddAbstractModel> modelList_add = new ArrayList<>();
    private ArrayList<AddAbstractModel> modelList_add_one = new ArrayList<>();
    private ArrayList<AddAbstractModel> modelList_teaching_traning_add = new ArrayList<>();
    private ArrayList<AddAbstractModel> modelList_food_add = new ArrayList<>();

    ProgressBar progressBar;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_homefragment, container, false);

        findViews();

        homedata("English");

        advertisement();

        rll_imgsearch_featured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                myIntent.putExtra("Featured",value1);

                Preference.save(getActivity(),Preference.KEY_title,value1);

                startActivity( myIntent );



            }
        });
         rll_imgsearch_services.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value2);
                 Preference.save(getActivity(),Preference.KEY_title,value2);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_family.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value3);
                 Preference.save(getActivity(),Preference.KEY_title,value3);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_furniture.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value4);
                 Preference.save(getActivity(),Preference.KEY_title,value4);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_animals.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value5);
                 Preference.save(getActivity(),Preference.KEY_title,value5);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_camping.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value6);
                 Preference.save(getActivity(),Preference.KEY_title,value6);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_jobs.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value7);
                 Preference.save(getActivity(),Preference.KEY_title,value7);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_teaching.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value8);
                 Preference.save(getActivity(),Preference.KEY_title,value8);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_health.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value9);
                 Preference.save(getActivity(),Preference.KEY_title,value9);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_foods.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value10);
                 Preference.save(getActivity(),Preference.KEY_title,value10);
                 startActivity( myIntent );
             }
         });
         rll_imgsearch_miscellaneou.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent myIntent = new Intent( getActivity(), AdvanceSearchActivity.class );
                 myIntent.putExtra("Featured",value11);
                 Preference.save(getActivity(),Preference.KEY_title,value11);
                 startActivity( myIntent );
             }
         });

        return  rootView;
    }


    private void findViews() {
        recycler_view_annimals_pets = (RecyclerView) rootView.findViewById(R.id.recycler_view_annimals_pets);
        recycler_view_camping_equipments = (RecyclerView) rootView.findViewById(R.id.recycler_view_camping_equipments);
        recycler_view_jobs = (RecyclerView) rootView.findViewById(R.id.recycler_view_jobs);
        recycler_view_teaching_traning = (RecyclerView) rootView.findViewById(R.id.recycler_view_teaching_traning);
        recycler_view_your_health = (RecyclerView) rootView.findViewById(R.id.recycler_view_your_health);
        recycler_view_foods = (RecyclerView) rootView.findViewById(R.id.recycler_view_foods);
        recycler_view_miscellaneous = (RecyclerView) rootView.findViewById(R.id.recycler_view_miscellaneous);
        recycler_view_teaching_traning_add = (RecyclerView) rootView.findViewById(R.id.recycler_view_teaching_traning_add);
        recycler_view_food_add = (RecyclerView) rootView.findViewById(R.id.recycler_view_food_add);
        recycler_view_camping_equipments_add = (RecyclerView) rootView.findViewById(R.id.recycler_view_camping_equipments_add);

        recycler_view_featured = (RecyclerView) rootView.findViewById(R.id.recycler_view_featured);
        recycler_view_services = (RecyclerView) rootView.findViewById(R.id.recycler_view_services);
        recycler_view_add = (RecyclerView) rootView.findViewById(R.id.recycler_view_add);
        recycler_view_family = (RecyclerView) rootView.findViewById(R.id.recycler_view_family );
        recycler_view_add_one = (RecyclerView) rootView.findViewById(R.id.recycler_view_add_one );
        recycler_view_furniture = (RecyclerView) rootView.findViewById(R.id.recycler_view_furniture );
        img_search = (ImageView) rootView.findViewById(R.id.img_search );
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar );
        rll_imgsearch_featured = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_featured );
        rll_imgsearch_services = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_services );
        rll_imgsearch_family = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_family );
        rll_imgsearch_furniture = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_furniture );
        rll_imgsearch_animals = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_animals );
        rll_imgsearch_camping = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_camping );
        rll_imgsearch_jobs = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_jobs );
        rll_imgsearch_teaching = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_teaching );
        rll_imgsearch_health = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_health );
        rll_imgsearch_foods = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_foods );
        rll_imgsearch_miscellaneou = (RelativeLayout) rootView.findViewById(R.id.rll_imgsearch_miscellaneou );


    }



    private void setAdapter_featured(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), this.modelList_featured);
        recycler_view_featured.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_featured.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_featured.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
            }
        });
    }

    private void setAdapter_Services(ArrayList<Featured> modelList_services) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), this.modelList_services);
        recycler_view_services.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_services.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_services.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
            }
        });
    }

    private void setAdapter_services(ArrayList<Service> modelList_services) {

       mServiceAdapter = new ServiceRecyclerViewAdapter(getActivity(), modelList_services);
        recycler_view_services.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_services.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_services.setAdapter(mServiceAdapter);
        mServiceAdapter.SetOnItemClickListener(new ServiceRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Service model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                        .putExtra("type","Property");
                startActivity(intent);
                //handle item click events here
                //   Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_family(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_family.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_family.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_family.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);

                //handle item click events here
                //  Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_furniture(ArrayList<Featured> modelList_featured) {

     mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_furniture.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_furniture.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_furniture.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //    Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_annimals_pets(ArrayList<Featured> modelList_annimals) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_annimals);
        recycler_view_annimals_pets.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_annimals_pets.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_annimals_pets.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);

            }
        });
    }

    private void setAdapter_camping_equipments(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_camping_equipments.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        recycler_view_camping_equipments.setLayoutManager(layoutManager);

        recycler_view_camping_equipments.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                // Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_jobs(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_jobs.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_jobs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_jobs.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //   Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_teaching_traning(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_teaching_traning.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_teaching_traning.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_teaching_traning.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {
                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);

                //handle item click events here
                // Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_your_health(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_your_health.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_your_health.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_your_health.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                        .putExtra("category_id",model.getId().toString());
                startActivity(intent);

            }
        });
    }

    private void setAdapter_foods(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_foods.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_foods.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_foods.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //  Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setAdapter_miscellaneous(ArrayList<Featured> modelList_featured) {

        mAdapter = new FeaturingRecyclerViewAdapter(getActivity(), modelList_featured);
        recycler_view_miscellaneous.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_miscellaneous.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recycler_view_miscellaneous.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new FeaturingRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //  Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void setAdapter_add(ArrayList<Featured> modelList_featured) {
      /*  modelList_add.clear();
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));*/


        mAdapter_add = new AddRecyclerViewAdapter(getActivity(), modelList_featured);

        recycler_view_add.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_add.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        recycler_view_add.setAdapter(mAdapter_add);

        mAdapter_add.SetOnItemClickListener(new AddRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                // Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_teaching_traning_add(ArrayList<Featured> modelList_featured) {
     /*   modelList_teaching_traning_add.clear();
        modelList_teaching_traning_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_teaching_traning_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_teaching_traning_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_teaching_traning_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_teaching_traning_add.add(new AddAbstractModel(R.drawable.ad1));*/
        mAdapter_add = new AddRecyclerViewAdapter(getActivity(), modelList_featured);

        recycler_view_teaching_traning_add.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_teaching_traning_add.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        recycler_view_teaching_traning_add.setAdapter(mAdapter_add);

        mAdapter_add.SetOnItemClickListener(new AddRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //  Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_add_one(ArrayList<Featured> modelList_featured) {
      /*  modelList_add_one.clear();
        modelList_add_one.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add_one.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add_one.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add_one.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add_one.add(new AddAbstractModel(R.drawable.ad1));*/

        mAdapter_add = new AddRecyclerViewAdapter(getActivity(), modelList_featured);

        recycler_view_teaching_traning_add.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_teaching_traning_add.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        recycler_view_teaching_traning_add.setAdapter(mAdapter_add);

        mAdapter_add.SetOnItemClickListener(new AddRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //  Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_food_add(ArrayList<Featured> modelList_featured) {
        /*modelList_add.clear();
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));*/

        mAdapter_add = new AddRecyclerViewAdapter(getActivity(), modelList_featured);

        recycler_view_food_add.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_food_add.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        recycler_view_food_add.setAdapter(mAdapter_add);

        mAdapter_add.SetOnItemClickListener(new AddRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                // Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter_camping_equipments_add(ArrayList<Featured> modelList_featured) {
 /*       modelList_add.clear();
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));
        modelList_add.add(new AddAbstractModel(R.drawable.ad1));*/

        mAdapter_add = new AddRecyclerViewAdapter(getActivity(), modelList_featured);

        recycler_view_camping_equipments_add.setHasFixedSize(true);
        // use a linear layout manager
        recycler_view_camping_equipments_add.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        recycler_view_camping_equipments_add.setAdapter(mAdapter_add);

        mAdapter_add.SetOnItemClickListener(new AddRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Featured model) {

                Intent intent=new Intent(getActivity(), DetailsProductActivity.class)
                      .putExtra("category_id",model.getId().toString());
                startActivity(intent);
                //handle item click events here
                //   Toast.makeText(getActivity(), "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void init(ArrayList<AdvertisementDataModel> modelList_advertisement_images) {

        CirclePageIndicator indicator = null;


        for(int i=0;i<modelList_advertisement_images.size() ;i++) {
            //ImagesArray.add
            // (IMAGES[i]);

            mPager = (ViewPager) rootView.findViewById(R.id.pager);

            mPager.setAdapter(new SlidingImage_Adapter(getActivity(), modelList_advertisement_images));

            indicator = (CirclePageIndicator) rootView.findViewById(R.id.indicator);
            indicator.setViewPager(mPager);
            final float density = getResources().getDisplayMetrics().density;
            //Set circle indicator radius
            indicator.setRadius(5 * density);
        }

        NUM_PAGES = modelList_advertisement_images.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {

            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 4000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (swipeTimer != null){
            swipeTimer.cancel();
        }
    }

    private void homedata(String english)
    {

        progressBar.setVisibility(View.VISIBLE);

        HashMap<String, String> map = new HashMap<>();
        map.put("language",english);
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_HOME_Data, Constants.USER_HOME_Data,map, Request.Method.POST);
    }

    private void advertisement()
    {
        ApiRequest apiRequest = new ApiRequest(getActivity(),this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_ADVERTISEMENT, Constants.USER_ADVERTISEMENT, Request.Method.GET);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_HOME_Data.equalsIgnoreCase(tag_json_obj)) {

            progressBar.setVisibility(View.GONE);

            if (response != null) {
                HomeMainModel finalArray = new Gson().fromJson(response, new TypeToken<HomeMainModel>() {
                }.getType());

                String status = finalArray.getStatus();

                if (status.equalsIgnoreCase("True")) {

                    modelList_annimals= (ArrayList<Featured>) finalArray.getAnnimals$Pets();
                    modelList_camping_equipments= (ArrayList<Featured>) finalArray.getCampingEquipment();
                    modelList_family= (ArrayList<Featured>) finalArray.getFamily();
                    modelList_featured= (ArrayList<Featured>) finalArray.getFeatured();
                   // modelList_foods= (ArrayList<Featured>) finalArray.get();
                    modelList_furniture= (ArrayList<Featured>) finalArray.getFurniture();
                    modelList_jobs= (ArrayList<Featured>) finalArray.getJobs();
                    modelList_miscellaneous= (ArrayList<Featured>) finalArray.getMiscellaneous();
                    modelList_services= (ArrayList<Featured>) finalArray.getServices();
                    modelList_teaching_traning= (ArrayList<Featured>) finalArray.getTeachingTraning();
                    modelList_your_health= (ArrayList<Featured>) finalArray.getYourHealth();

                    setAdapter_annimals_pets(modelList_annimals);
                    setAdapter_camping_equipments(modelList_camping_equipments);
                    setAdapter_family(modelList_family);
                    setAdapter_featured(modelList_featured);
                    //setAdapter_foods(modelList_foods);
                    setAdapter_furniture(modelList_furniture);
                    setAdapter_jobs(modelList_jobs);
                    setAdapter_miscellaneous(modelList_miscellaneous);
                    setAdapter_Services(modelList_services);
                    setAdapter_teaching_traning(modelList_teaching_traning);
                    setAdapter_your_health(modelList_your_health);

                   /*
                    setAdapter_add(modelList_featured);
                    setAdapter_add_one(modelList_featured);
                    setAdapter_food_add(modelList_featured);
                    setAdapter_camping_equipments_add(modelList_featured);
                     */

                } else {
                    Toast.makeText(getActivity(), "getting false" + finalArray.getStatus(), Toast.LENGTH_SHORT).show();
                }

            }
        }
        else if (Constants.USER_ADVERTISEMENT.equalsIgnoreCase(tag_json_obj)) {

            AdvertisementModel finalArray = new Gson().fromJson(response, new TypeToken<AdvertisementModel>() {
            }.getType());

            Boolean status = finalArray.getStatus();

            if (status) {

                modelList_advertisement_images = (ArrayList<AdvertisementDataModel>) finalArray.getResult();

                init(modelList_advertisement_images);

            }
            else
            {
                Toast.makeText(getActivity(), "false :::", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getActivity(), "Please check network", Toast.LENGTH_SHORT).show();
    }
}
