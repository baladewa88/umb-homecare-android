package umbandung.com.digitalhomecare;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class TransaksiUtil {

    private String nama, rm, tanggal;

    public TransaksiUtil(String nama, String rm, String tanggal){
        this.nama = nama;
        this.rm = rm;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
