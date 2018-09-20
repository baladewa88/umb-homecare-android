package umbandung.com.digitalhomecare;

/**
 * Created by Arkhan on 8/21/2018.
 */

public class OrderUtil {

    private String id, tanggal, harga, klinik, tipe, status, orderNumber;

    public OrderUtil() {

    }

    public OrderUtil(String id, String tanggal, String harga, String klinik, String tipe, String status, String orderNumber) {
        this.id = id;

        this.tanggal = tanggal;
        this.harga = harga;
        this.klinik = klinik;
        this.tipe = tipe;
        this.status = status;
        this.orderNumber = orderNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKlinik() {
        return klinik;
    }

    public void setKlinik(String klinik) {
        this.klinik = klinik;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

}