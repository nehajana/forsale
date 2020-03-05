package shubham.com.foursale.PostAdFragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import shubham.com.foursale.AdvanceSearchScreen.AdvanceSearchActivity;
import shubham.com.foursale.LoginActivity.ApiModel.LoginDataModel;
import shubham.com.foursale.LoginActivity.LoginActivity;
import shubham.com.foursale.Offerfragment.NotificationFragment.NotificationFragment;
import shubham.com.foursale.Preference;
import shubham.com.foursale.R;
import shubham.com.foursale.SigninScreen.SignInActivity;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

import static android.app.Activity.RESULT_OK;


public class PostAdFragment extends Fragment implements IApiResponse {

    private View rootView;
    RelativeLayout rll_category;
    RelativeLayout rll_third_level;
    String str;
    TextView txt_userName_etxt;
    EditText edt_mobilenumber;
    String mobile;
    RelativeLayout RR_image_select;
    ImageView img_set;

    private static final int REQUEST_PERMISSIONS = 100;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA= 7;
    Bitmap bitmap;

    public static PostAdFragment newInstance(String param1, String param2) {
        PostAdFragment fragment = new PostAdFragment();
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
        rootView = inflater.inflate(R.layout.activity_postadfragment, container, false);

        //setAdapter_Schdule();

        findview();

        RR_image_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE))) {

                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_PERMISSIONS);
                    }
                }else {

                    showPictureDialog();
                }

            }
        });

        rll_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),ExpandablelistViewActivity.class);
                startActivityForResult(i, 1);

            }
        });


        return  rootView;
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {

        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }


    private void takePhotoFromCamera() {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)
                    getActivity(), Manifest.permission.CAMERA)) {

            } else {

                ActivityCompat.requestPermissions((Activity) getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }

        }else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null)
                startActivityForResult(cameraIntent, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    bitmap = (Bitmap) data.getExtras().get("data");

                    img_set.setImageBitmap(bitmap);

                    //progressbar.setVisibility(View.VISIBLE);
                   // UpdateTopNesImageUpload(bitmap);

                    File f = new File(String.valueOf(selectedImage));

                    String imageName = f.getName();

                    //txt_upload.setText(imageName);

                    // isSelectedImage =true;
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();

                    try {

                        //   isImage=true;
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);

                        File f = new File(String.valueOf(selectedImage));

                        String imageName = f.getName();

                        img_set.setImageBitmap(bitmap);

                       // txt_upload.setText(imageName);

                        //  isSelectedImage =true;

                    } catch (IOException e) {

                        Log.i("TAG", "Some exception " + e); }

                }
        }
    }










    private void validation()
    {
        mobile=edt_mobilenumber.getText().toString();


    }

    private void findview()
    {
        RR_image_select=(RelativeLayout) rootView.findViewById(R.id.RR_image_select);
        rll_category = (RelativeLayout) rootView.findViewById(R.id.rll_category);
        txt_userName_etxt = (TextView) rootView.findViewById(R.id.txt_userName_etxt);
        edt_mobilenumber = (EditText) rootView.findViewById(R.id.edt_mobilenumber);
        img_set = (ImageView) rootView.findViewById(R.id.img_set);
    }


   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {

                    String newText = data.getStringExtra("Second");
                    txt_userName_etxt.setText(newText);
                }
                break;
            }
        }
    }*/

    private void PostAddmethod(String choosecategory, String title,String price,String image)
    {
        HashMap<String, String> map = new HashMap<>();

        map.put("choosecategory",choosecategory);
        map.put("title",title);
        map.put("price",price);
        map.put("image",image);

        ApiRequest apiRequest = new ApiRequest(getActivity(),this);

        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_POSt_AD, Constants.USER_POSt_AD,map, Request.Method.POST);

    }


    @Override
    public void onResultReceived(String response, String tag_json_obj) {

        if (Constants.USER_POSt_AD.equalsIgnoreCase(tag_json_obj)){

            if (!response.equalsIgnoreCase(null)) {

                LoginDataModel finalArray = new Gson().fromJson(response,new TypeToken<LoginDataModel>(){}.getType());

                String status= String.valueOf(finalArray.getStatus());
                if (status.equalsIgnoreCase("Success")){

                    Intent intent=new Intent(getActivity(), SignInActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Login UnSuccess", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

}
