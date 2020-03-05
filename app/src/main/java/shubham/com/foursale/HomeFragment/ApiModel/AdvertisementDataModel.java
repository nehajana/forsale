package shubham.com.foursale.HomeFragment.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvertisementDataModel
{
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("image")
    @Expose
    private String image;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
