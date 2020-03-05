
package shubham.com.foursale.HomeFragment.ApiModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeMainModel {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Featured")
    @Expose
    private List<Featured> featured = null;
    @SerializedName("Automotive")
    @Expose
    private List<Featured> automotive = null;
    @SerializedName("Services")
    @Expose
    private List<Featured> services = null;
    @SerializedName("Family")
    @Expose
    private List<Featured> family = null;
    @SerializedName("Furniture")
    @Expose
    private List<Featured> furniture = null;
    @SerializedName("Annimals $ Pets")
    @Expose
    private List<Featured> annimals$Pets = null;
    @SerializedName("Camping Equipment")
    @Expose
    private List<Featured> campingEquipment = null;
    @SerializedName("jobs")
    @Expose
    private List<Featured> jobs = null;
    @SerializedName("Teaching Traning")
    @Expose
    private List<Featured> teachingTraning = null;
    @SerializedName("Your Health")
    @Expose
    private List<Featured> yourHealth = null;
    @SerializedName("Miscellaneous")
    @Expose
    private List<Featured> miscellaneous = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Featured> getFeatured() {
        return featured;
    }

    public void setFeatured(List<Featured> featured) {
        this.featured = featured;
    }

    public List<Featured> getAutomotive() {
        return automotive;
    }

    public void setAutomotive(List<Featured> automotive) {
        this.automotive = automotive;
    }

    public List<Featured> getServices() {
        return services;
    }

    public void setServices(List<Featured> services) {
        this.services = services;
    }

    public List<Featured> getFamily() {
        return family;
    }

    public void setFamily(List<Featured> family) {
        this.family = family;
    }

    public List<Featured> getFurniture() {
        return furniture;
    }

    public void setFurniture(List<Featured> furniture) {
        this.furniture = furniture;
    }

    public List<Featured> getAnnimals$Pets() {
        return annimals$Pets;
    }

    public void setAnnimals$Pets(List<Featured> annimals$Pets) {
        this.annimals$Pets = annimals$Pets;
    }

    public List<Featured> getCampingEquipment() {
        return campingEquipment;
    }

    public void setCampingEquipment(List<Featured> campingEquipment) {
        this.campingEquipment = campingEquipment;
    }

    public List<Featured> getJobs() {
        return jobs;
    }

    public void setJobs(List<Featured> jobs) {
        this.jobs = jobs;
    }

    public List<Featured> getTeachingTraning() {
        return teachingTraning;
    }

    public void setTeachingTraning(List<Featured> teachingTraning) {
        this.teachingTraning = teachingTraning;
    }

    public List<Featured> getYourHealth() {
        return yourHealth;
    }

    public void setYourHealth(List<Featured> yourHealth) {
        this.yourHealth = yourHealth;
    }

    public List<Featured> getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(List<Featured> miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

}
