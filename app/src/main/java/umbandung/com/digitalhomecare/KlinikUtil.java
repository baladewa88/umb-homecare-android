package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 9/6/2018.
 */

public class KlinikUtil {

    private String id, nameOfClinic, codeOfClinic, address, status;

    public KlinikUtil(String id, String nameOfClinic, String codeOfClinic, String address, String status) {
        this.id = id;
        this.nameOfClinic = nameOfClinic;
        this.codeOfClinic = codeOfClinic;
        this.address = address;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String toString() {
        return this.nameOfClinic;
    }
}
