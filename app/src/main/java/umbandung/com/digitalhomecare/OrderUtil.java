package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 8/21/2018.
 */

public class OrderUtil {

    private String tanggal, harga;
    private String[] medis, layanan;

    public OrderUtil(){

    }

    public OrderUtil(String tanggal, String harga, String[] medis, String[] layanan){
        this.tanggal = tanggal;
        this.harga = harga;
        this.medis = medis;
        this.layanan = layanan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String[] getMedis() {
        return medis;
    }

    public void setMedis(String[] medis) {
        this.medis = medis;
    }

    public String[] getLayanan() {
        return layanan;
    }

    public void setLayanan(String[] layanan) {
        this.layanan = layanan;
    }
}
