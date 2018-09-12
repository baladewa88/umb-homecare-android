package umbandung.com.digitalhomecare;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clinic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameOfClinic")
    @Expose
    private String nameOfClinic;
    @SerializedName("codeOfClinic")
    @Expose
    private String codeOfClinic;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfClinic() {
        return nameOfClinic;
    }

    public void setNameOfClinic(String nameOfClinic) {
        this.nameOfClinic = nameOfClinic;
    }

    public String getCodeOfClinic() {
        return codeOfClinic;
    }

    public void setCodeOfClinic(String codeOfClinic) {
        this.codeOfClinic = codeOfClinic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}