
package umbandung.com.digitalhomecare.Model.nurse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Services {

    @SerializedName("codeOfservices")
    private String mCodeOfservices;
    @SerializedName("id")
    private Long mId;
    @SerializedName("nameOfservices")
    private String mNameOfservices;
    @SerializedName("price")
    private Double mPrice;

    public String getCodeOfservices() {
        return mCodeOfservices;
    }

    public void setCodeOfservices(String codeOfservices) {
        mCodeOfservices = codeOfservices;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getNameOfservices() {
        return mNameOfservices;
    }

    public void setNameOfservices(String nameOfservices) {
        mNameOfservices = nameOfservices;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        mPrice = price;
    }

}
