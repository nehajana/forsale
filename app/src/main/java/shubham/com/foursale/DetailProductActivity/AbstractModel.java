package shubham.com.foursale.DetailProductActivity;

import java.util.ArrayList;

public class AbstractModel {

    private String title;

    private int servicesName;


    public AbstractModel(String title, int servicesName) {
        this.title = title;
        this.servicesName = servicesName;
    }

    public AbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getServicesName() {
        return servicesName;
    }

    public void setServicesName(int servicesName) {
        this.servicesName = servicesName;
    }
}
