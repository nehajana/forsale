package shubham.com.foursale.HomeFragment.featuring;

import java.util.ArrayList;

public class FeaturedAbstractModel {

    private String servicesName;

    private int img_services;


    public FeaturedAbstractModel(String servicesName, int img_services) {
        this.servicesName = servicesName;
        this.img_services = img_services;
    }

    public FeaturedAbstractModel() {

    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public int getImg_services() {
        return img_services;
    }

    public void setImg_services(int img_services) {
        this.img_services = img_services;
    }
}
