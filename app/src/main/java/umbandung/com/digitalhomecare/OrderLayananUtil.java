package umbandung.com.digitalhomecare;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class OrderLayananUtil {

    private String nama, kode, harga;

    public OrderLayananUtil(String nama, String kode, String harga){

        this.nama = nama;
        this.kode = kode;
        this.harga = harga;

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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("nama", nama);
            obj.put("kode", kode);
            obj.put("harga", harga);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
