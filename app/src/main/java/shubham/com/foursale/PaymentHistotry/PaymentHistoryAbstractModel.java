package shubham.com.foursale.PaymentHistotry;

import java.util.ArrayList;

public class PaymentHistoryAbstractModel {

    private String title;

    private String message;


    public PaymentHistoryAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public PaymentHistoryAbstractModel() {

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
