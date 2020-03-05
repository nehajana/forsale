package shubham.com.foursale.SubProductDetailsScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import shubham.com.foursale.R;

public class ProductDetailsActivity extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        findview();
    }

    private void findview()
    {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
