package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 9/6/2018.
 */

public class KlinikUtil {

    private String id, nama, kode, alamat, status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KlinikUtil(String id, String nama, String kode, String alamat, String status){
        this.id = id;
        this.nama = nama;
        this.kode = kode;
        this.alamat = alamat;
        this.status = status;

    }

    public KlinikUtil(){

    };
}
