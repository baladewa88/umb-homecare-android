package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arkhan on 9/8/2018.
 */

public class GetOrder {

    @SerializedName("status")
    String status;

    @SerializedName("result")
    List<Order> listDataKontak;

    @SerializedName("message")
    String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getListDataKontak() {
        return listDataKontak;
    }

    public void setListDataKontak(List<Order> listDataKontak) {
        this.listDataKontak = listDataKontak;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
