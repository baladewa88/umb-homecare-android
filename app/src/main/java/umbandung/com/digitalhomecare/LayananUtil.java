package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 7/24/2018.
 */

public class LayananUtil {

    private Integer id;
    private String codeOfservices;
    private String nameOfservices;
    private Integer price;

    public LayananUtil(Integer id, String codeOfservices, String nameOfservices, Integer price) {
        this.id = id;
        this.codeOfservices = codeOfservices;
        this.nameOfservices = nameOfservices;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeOfservices() {
        return codeOfservices;
    }

    public void setCodeOfservices(String codeOfservices) {
        this.codeOfservices = codeOfservices;
    }

    public String getNameOfservices() {
        return nameOfservices;
    }

    public void setNameOfservices(String nameOfservices) {
        this.nameOfservices = nameOfservices;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
