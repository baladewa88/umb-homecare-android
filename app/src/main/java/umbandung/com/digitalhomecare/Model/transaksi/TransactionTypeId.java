
package umbandung.com.digitalhomecare.Model.transaksi;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class TransactionTypeId {

    @SerializedName("id")
    private Long mId;
    @SerializedName("type")
    private String mType;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
