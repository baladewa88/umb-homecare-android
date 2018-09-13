
package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PaymentFixedPriceStatusId {

    @SerializedName("id")
    private Long mId;
    @SerializedName("status")
    private String mStatus;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
