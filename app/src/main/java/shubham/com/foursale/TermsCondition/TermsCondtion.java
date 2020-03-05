package shubham.com.foursale.TermsCondition;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import shubham.com.foursale.DetailProductActivity.ApiModel.DetailsModel;
import shubham.com.foursale.DetailProductActivity.ApiModel.Details_DataModel;
import shubham.com.foursale.DetailProductActivity.DetailsProductActivity;
import shubham.com.foursale.R;
import shubham.com.foursale.TermsCondition.ApiModel.TermsDataModel;
import shubham.com.foursale.TermsCondition.ApiModel.TermsModel;
import shubham.com.foursale.Volley.ApiRequest;
import shubham.com.foursale.Volley.Constants;
import shubham.com.foursale.Volley.IApiResponse;

public class TermsCondtion extends AppCompatActivity implements IApiResponse {
    ProgressBar progressbar;
    WebView webview;
    String message;
    TextView txt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_condtion);

        findview();


      /*  webview.setWebViewClient(new AppWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setPluginState( WebSettings.PluginState.ON);
        // webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl("https://www.google.com");*/

        termsdata();

        progressbar.setVisibility(View.VISIBLE);
    }

    private void termsdata() {
        ApiRequest apiRequest = new ApiRequest(TermsCondtion.this,this);
        apiRequest.postRequest( Constants.BASE_URL + Constants.USER_TERMS, Constants.USER_TERMS, Request.Method.GET);
    }

    @Override
    public void onResultReceived(String response, String tag_json_obj) {
        if (Constants.USER_TERMS.equalsIgnoreCase(tag_json_obj)) {

            if (response != null) {

                progressbar.setVisibility(View.GONE);

                TermsModel finalArray = new Gson().fromJson(response, new TypeToken<TermsModel>() {
                }.getType());

                Boolean status = finalArray.getStatus();
                if (status) {

                    TermsDataModel termsDataModel = finalArray.getResult();
                    message = termsDataModel.getMessage();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        txt_view.setText(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        txt_view.setText(Html.fromHtml(message));
                    }

                } else {

                }
            }
        }
    }
        @Override
        public void onErrorResponse (VolleyError error){
            progressbar.setVisibility( View.GONE);
        }

        public class AppWebViewClient extends WebViewClient {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
            }
        }
    private void findview()
    {
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        txt_view = (TextView) findViewById(R.id.txt_view);
    }
}

