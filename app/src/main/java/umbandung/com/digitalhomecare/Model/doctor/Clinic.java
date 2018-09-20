
package umbandung.com.digitalhomecare.Model.doctor;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Clinic {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("codeOfClinic")
    private String mCodeOfClinic;
    @SerializedName("id")
    private Long mId;
    @SerializedName("nameOfClinic")
    private String mNameOfClinic;
    @SerializedName("status")
    private String mStatus;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCodeOfClinic() {
        return mCodeOfClinic;
    }

    public void setCodeOfClinic(String codeOfClinic) {
        mCodeOfClinic = codeOfClinic;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getNameOfClinic() {
        return mNameOfClinic;
    }

    public void setNameOfClinic(String nameOfClinic) {
        mNameOfClinic = nameOfClinic;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
