package umbandung.com.digitalhomecare.Model;

/**
 * Created by Arkhan on 9/12/2018.
 */

public class TransaksiGet {

    private String idTransaksi;
    private String idPasien;
    private String tanggalTransaksi;
    private String orderNumber;
    private String serviceList[];
    private String harga;

    public TransaksiGet(String[] serviceList, String idTransaksi, String idPasien, String tanggalTransaksi, String orderNumber, String harga){
        this.idTransaksi = idTransaksi;
        this.idPasien = idPasien;
        this.tanggalTransaksi = tanggalTransaksi;
        this.orderNumber = orderNumber;
        this.serviceList = serviceList;
        this.harga = harga;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String[] getServiceList() {
        return serviceList;
    }

    public void setServiceList(String[] serviceList) {
        this.serviceList = serviceList;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
