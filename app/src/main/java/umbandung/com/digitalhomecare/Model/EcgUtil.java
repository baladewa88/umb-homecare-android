package umbandung.com.digitalhomecare.Model;

/**
 * Created by Arkhan on 9/13/2018.
 */

public class EcgUtil {

    private String id, ecgDate, ecgCode, analog, orderAnalog;

    public EcgUtil(String id, String ecgDate, String ecgCode, String analog, String orderAnalog){
        this.id = id;
        this.ecgDate = ecgDate;
        this.ecgCode = ecgCode;
        this.analog = analog;
        this.orderAnalog = orderAnalog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAnalog() {
        return analog;
    }

    public void setAnalog(String analog) {
        this.analog = analog;
    }

    public String getOrderAnalog() {
        return orderAnalog;
    }

    public void setOrderAnalog(String orderAnalog) {
        this.orderAnalog = orderAnalog;
    }
}
