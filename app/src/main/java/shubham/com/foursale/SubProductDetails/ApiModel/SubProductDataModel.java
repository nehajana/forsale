package shubham.com.foursale.SubProductDetails.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubProductDataModel
{
    @SerializedName("sub_sub_id")
    @Expose
    private String subSubId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;

    public String getSubSubId() {
        return subSubId;
    }

    public void setSubSubId(String subSubId) {
        this.subSubId = subSubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
