package shubham.com.foursale.Offerfragment.NotificationFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import shubham.com.foursale.MessageFragment.MessageFragment;
import shubham.com.foursale.Offerfragment.CommercialAdsFragment.CommercialFragment;
import shubham.com.foursale.R;


public class NotificationFragment extends android.support.v4.app.Fragment {

    private View rootView;



    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        rootView = inflater.inflate(R.layout.activity_notificationfragment, container, false);

        //setAdapter_Schdule();
        return  rootView;
    }

}
