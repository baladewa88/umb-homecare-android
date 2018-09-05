package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class OrderMedisUtil {

    private String nama, kode;

    public OrderMedisUtil(String nama, String kode){
        this.nama = nama;
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
