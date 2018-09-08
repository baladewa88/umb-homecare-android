package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Arkhan on 9/8/2018.
 */

public class PostPutDelOrder {

    @SerializedName("status")
    String status;

    @SerializedName("result")
    Order mOrder;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getmOrder() {
        return mOrder;
    }

    public void setmOrder(Order mOrder) {
        this.mOrder = mOrder;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
