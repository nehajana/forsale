package shubham.com.foursale.SubProductDetails;

import java.util.ArrayList;

public class SubProductDetailsAbstractModel {

    private String title;

    private String message;


    public SubProductDetailsAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public SubProductDetailsAbstractModel() {

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
