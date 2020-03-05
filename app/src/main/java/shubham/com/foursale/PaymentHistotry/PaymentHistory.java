package shubham.com.foursale.PaymentHistotry;

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


public class PaymentHistory extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;


    private PaymentHistoryRecyclerViewAdapter mAdapter;

    private ArrayList<PaymentHistoryAbstractModel> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        // ButterKnife.bind(this);
        findViews();
        setAdapter();

    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void setAdapter() {

        modelList.add(new PaymentHistoryAbstractModel("Android", "Hello " + " Android"));
        modelList.add(new PaymentHistoryAbstractModel("Beta", "Hello " + " Beta"));
        modelList.add(new PaymentHistoryAbstractModel("Cupcake", "Hello " + " Cupcake"));
        modelList.add(new PaymentHistoryAbstractModel("Donut", "Hello " + " Donut"));

        mAdapter = new PaymentHistoryRecyclerViewAdapter(PaymentHistory.this, modelList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new PaymentHistoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, PaymentHistoryAbstractModel model) {
                //handle item click events here
                Toast.makeText(PaymentHistory.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
