package shubham.com.foursale.Notification;

import java.util.ArrayList;

public class NotificationAbstractModel {

    private String title;

    private String message;


    public NotificationAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public NotificationAbstractModel() {

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
