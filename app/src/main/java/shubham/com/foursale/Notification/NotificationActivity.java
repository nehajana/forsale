package shubham.com.foursale.Notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import shubham.com.foursale.R;

import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Handler;


public class NotificationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RelativeLayout rll_arrow;
    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;


    private NotificationRecyclerViewAdapter mAdapter;

    private ArrayList<NotificationAbstractModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // ButterKnife.bind(this);
        findViews();
        setAdapter();
        rll_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        rll_arrow = (RelativeLayout) findViewById(R.id.rll_arrow);
    }


    private void setAdapter() {


        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));
        modelList.add(new NotificationAbstractModel("This is notification screen", "This is notification screen"));

        mAdapter = new NotificationRecyclerViewAdapter(NotificationActivity.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new NotificationRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, NotificationAbstractModel model) {

                //handle item click events here
                Toast.makeText(NotificationActivity.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });


    }


}
