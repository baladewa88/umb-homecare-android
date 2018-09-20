package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentEcg {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ecgDate")
    @Expose
    private String ecgDate;
    @SerializedName("ecgCode")
    @Expose
    private String ecgCode;
    @SerializedName("analog")
    @Expose
    private Integer analog;
    @SerializedName("orderAnalog")
    @Expose
    private Object orderAnalog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEcgDate() {
        return ecgDate;
    }

    public void setEcgDate(String ecgDate) {
        this.ecgDate = ecgDate;
    }

    public String getEcgCode() {
        return ecgCode;
    }

    public void setEcgCode(String ecgCode) {
        this.ecgCode = ecgCode;
    }

    public Integer getAnalog() {
        return analog;
    }

    public void setAnalog(Integer analog) {
        this.analog = analog;
    }

    public Object getOrderAnalog() {
        return orderAnalog;
    }

    public void setOrderAnalog(Object orderAnalog) {
        this.orderAnalog = orderAnalog;
    }

}