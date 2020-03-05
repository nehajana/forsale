package shubham.com.foursale.HomeScreen;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import shubham.com.foursale.HomeFragment.HomeFragment;
import shubham.com.foursale.MessageFragment.MessageFragment;
import shubham.com.foursale.MoreFragment.MoreFragment;
import shubham.com.foursale.Offerfragment.OfferFragment;
import shubham.com.foursale.PostAdFragment.PostAdFragment;
import shubham.com.foursale.R;

public class HomeActivity extends AppCompatActivity {

    Fragment fragment;
    RelativeLayout RR_home,RR_offer,RR_PostAd,RR_message,RR_more;
    ImageView img_offer,img_home,img_postad,img_message,img_more;
    boolean doubleBackToExitPressedOnce = false;
    TextView txt_categories,txt_offer,txt_postadd,txt_message,txt_more;


    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findview();

     /*   Intent intent = getIntent();
        if(intent != null) {
            value = intent.getStringExtra("postadd");
            System.out.println("value :- "+value);
            fragment = new PostAdFragment();
            loadFragment(fragment);
        }*/

        RR_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_home.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                txt_categories.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));

                img_offer.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_postad.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_message.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_more.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                txt_offer.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_postadd.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_message.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_more.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                // img_home.setBackgroundResource(R.drawable.home_icon);

                fragment = new HomeFragment();
                loadFragment(fragment);
            }
        });
        RR_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_offer.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                img_offer.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));

                img_home.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_postad.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_message.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_more.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                txt_categories.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_postadd.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_message.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_more.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                fragment = new OfferFragment();
                loadFragment(fragment);
            }
        });

        RR_PostAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_postadd.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                img_postad.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));

                img_home.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_offer.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_message.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_more.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                txt_categories.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_offer.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_message.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_more.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                fragment = new PostAdFragment();
                loadFragment(fragment);
            }
        });

        RR_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_message.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                txt_message.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));

                img_home.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_offer.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_postad.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_more.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                txt_categories.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_offer.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_postadd.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_more.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                fragment = new MessageFragment();
                loadFragment(fragment);
            }
        });

        RR_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img_more.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));
                txt_more.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.colorPrimary));

                img_home.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_offer.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_postad.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                img_message.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                txt_categories.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_offer.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_postadd.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));
                txt_message.setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.Gray));

                fragment = new MoreFragment();
                loadFragment(fragment);
            }
        });


        loadFragment(new HomeFragment());

    }

    private void findview() {
        txt_categories=(TextView) findViewById(R.id.txt_categories);
        txt_offer=(TextView) findViewById(R.id.txt_offer);
        txt_postadd=(TextView) findViewById(R.id.txt_postadd);
        txt_message=(TextView) findViewById(R.id.txt_message);
        txt_more=(TextView) findViewById(R.id.txt_more);

        RR_home=(RelativeLayout) findViewById(R.id.RR_home);
        RR_offer=(RelativeLayout) findViewById(R.id.RR_offer);
        RR_PostAd=(RelativeLayout) findViewById(R.id.RR_PostAd);
        RR_message=(RelativeLayout) findViewById(R.id.RR_message);
        RR_more=(RelativeLayout) findViewById(R.id.RR_more);

        img_offer=(ImageView) findViewById(R.id.img_offer);
        img_home=(ImageView) findViewById(R.id.img_home);
        img_postad=(ImageView) findViewById(R.id.img_postad);
        img_message=(ImageView) findViewById(R.id.img_message);
        img_more=(ImageView) findViewById(R.id.img_more);


    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        fragment = new HomeFragment();
        loadFragment(fragment);
        img_home.setImageResource(R.drawable.home_icon);
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
