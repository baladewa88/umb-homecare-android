package umbandung.com.digitalhomecare.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sort_ {

    @SerializedName("sorted")
    @Expose
    private Boolean sorted;
    @SerializedName("unsorted")
    @Expose
    private Boolean unsorted;

    public Boolean getSorted() {
        return sorted;
    }

    public void setSorted(Boolean sorted) {
        this.sorted = sorted;
    }

    public Boolean getUnsorted() {
        return unsorted;
    }

    public void setUnsorted(Boolean unsorted) {
        this.unsorted = unsorted;
    }

}