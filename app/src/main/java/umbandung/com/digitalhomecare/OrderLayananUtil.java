package umbandung.com.digitalhomecare;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class OrderLayananUtil {

    private String id, nama, kode, harga;

    public OrderLayananUtil(String id, String nama, String kode, String harga){

        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);

            obj.put("nama", nama);
            obj.put("kode", kode);
            obj.put("harga", harga);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
