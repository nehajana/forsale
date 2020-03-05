package shubham.com.foursale.AdvanceSearchScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import shubham.com.foursale.Preference;
import shubham.com.foursale.R;

public class AdvanceSearchActivity extends AppCompatActivity {
RelativeLayout RR_back;
EditText edt_lastname;
String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);

        findview();
        value1=Preference.get(this,Preference.KEY_title);
        Intent myIntent = getIntent();

        if(myIntent !=null) {

            //value1 = myIntent.getStringExtra("Featured").toString();
            edt_lastname.setText(value1);

            System.out.println("value: "+value1);
        }


        RR_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void findview()
    {
        RR_back = (RelativeLayout) findViewById(R.id.RR_back);
        edt_lastname = (EditText) findViewById(R.id.edt_lastname);
    }
}
