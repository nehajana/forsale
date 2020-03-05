package shubham.com.foursale.Offerfragment.CommercialAdsFragment;

import java.util.ArrayList;

public class CommercialAbstractModel {

    private String title;

    private String message;


    public CommercialAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public CommercialAbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
