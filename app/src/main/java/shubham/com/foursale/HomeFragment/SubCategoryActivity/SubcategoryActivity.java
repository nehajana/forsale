package shubham.com.foursale.HomeFragment.SubCategoryActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import shubham.com.foursale.R;

import android.widget.Toast;
import android.os.Handler;


public class SubcategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    private SubCategoryRecyclerViewAdapter mAdapter;

    private ArrayList<SubCategoryAbstractModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        // ButterKnife.bind(this);
        findViews();
        setAdapter();


    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }


    private void setAdapter() {


        modelList.add(new SubCategoryAbstractModel("Android", "Hello " + " Android"));
        modelList.add(new SubCategoryAbstractModel("Beta", "Hello " + " Beta"));
        modelList.add(new SubCategoryAbstractModel("Cupcake", "Hello " + " Cupcake"));

        mAdapter = new SubCategoryRecyclerViewAdapter(SubcategoryActivity.this, modelList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(new SubCategoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, SubCategoryAbstractModel model) {
                //handle item click events here
                Toast.makeText(SubcategoryActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
