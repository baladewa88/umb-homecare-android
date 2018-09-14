
package umbandung.com.digitalhomecare.Model.transaksi;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Sort {

    @SerializedName("sorted")
    private Boolean mSorted;
    @SerializedName("unsorted")
    private Boolean mUnsorted;

    public Boolean getSorted() {
        return mSorted;
    }

    public void setSorted(Boolean sorted) {
        mSorted = sorted;
    }

    public Boolean getUnsorted() {
        return mUnsorted;
    }

    public void setUnsorted(Boolean unsorted) {
        mUnsorted = unsorted;
    }

}
