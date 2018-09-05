package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 7/24/2018.
 */

public class LayananUtil {

    private String id;
    private String code;
    private String name;
    private String price;

    public LayananUtil(){

    }

    public LayananUtil(String id, String code, String nama, String harga){
        this.id = id;
        this.code = code;
        this.name = nama;
        this.price = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
