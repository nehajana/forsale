package shubham.com.foursale.DetailProductActivity.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsModel
{
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Details_DataModel> result = null;
    @SerializedName("code")
    @Expose
    private Integer code;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Details_DataModel> getResult() {
        return result;
    }

    public void setResult(List<Details_DataModel> result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
