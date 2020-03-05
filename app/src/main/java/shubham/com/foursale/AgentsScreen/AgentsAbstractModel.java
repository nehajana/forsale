package shubham.com.foursale.AgentsScreen;

import java.util.ArrayList;

public class AgentsAbstractModel {

    private String title;

    private String message;


    public AgentsAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public AgentsAbstractModel() {

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
