
package umbandung.com.digitalhomecare.Model.nurse;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ServiceList {

    @SerializedName("id")
    private Long mId;
    @SerializedName("services")
    private Services mServices;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Services getServices() {
        return mServices;
    }

    public void setServices(Services services) {
        mServices = services;
    }

}
