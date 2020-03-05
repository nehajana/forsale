package shubham.com.foursale.SignupScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import shubham.com.foursale.R;

public class SignUpActivity extends AppCompatActivity {

    EditText edt_firstname;
    EditText edt_lastname;
    EditText edt_email;
    EditText edt_password;
    EditText edt_contnum;
    EditText edt_shippingaddress;
    EditText edt_city;
    EditText edt_country;
    EditText edt_zipcode;
    Button btn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findview();
    }

    private void findview()
    {
        edt_firstname = (EditText) findViewById(R.id.edt_firstname);
        edt_lastname = (EditText) findViewById(R.id.edt_lastname);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_contnum = (EditText) findViewById(R.id.edt_contnum);
        edt_shippingaddress = (EditText) findViewById(R.id.edt_shippingaddress);
        edt_city = (EditText) findViewById(R.id.edt_city);
        edt_country = (EditText) findViewById(R.id.edt_country);
        edt_zipcode = (EditText) findViewById(R.id.edt_zipcode);
        btn_signup = (Button) findViewById(R.id.btn_signup);
    }
}
