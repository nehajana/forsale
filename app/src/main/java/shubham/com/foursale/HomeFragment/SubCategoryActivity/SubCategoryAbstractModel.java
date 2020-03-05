package shubham.com.foursale.HomeFragment.SubCategoryActivity;

import java.util.ArrayList;

public class SubCategoryAbstractModel {

    private String title;

    private String message;


    public SubCategoryAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public SubCategoryAbstractModel() {

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
