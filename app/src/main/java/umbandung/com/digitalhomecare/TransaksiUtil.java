package umbandung.com.digitalhomecare;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class TransaksiUtil {
    private String idPasien, tipeTransaksi, statusTransaksi, statusPembayaran, idKlinik, alamat, tanggal;
    private String[] layanan;

    public TransaksiUtil(String idPasien, String tipeTransaksi, String statusTransaksi,String statusPembayaran,String idKlinik,String alamat,String tanggal, String[] layanan){

        this.idPasien = idPasien;
        this.tipeTransaksi = tipeTransaksi;
        this.statusTransaksi = statusTransaksi;
        this.statusPembayaran = statusPembayaran;
        this.idKlinik = idKlinik;
        this.alamat = alamat;
        this.tanggal = tanggal;
        this.layanan = layanan;

    }

    public String getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(String idPasien) {
        this.idPasien = idPasien;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getIdKlinik() {
        return idKlinik;
    }

    public void setIdKlinik(String idKlinik) {
        this.idKlinik = idKlinik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String[] getLayanan() {
        return layanan;
    }

    public void setLayanan(String[] layanan) {
        this.layanan = layanan;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("userPatient", idPasien);
            obj.put("transactionTypeId", tipeTransaksi);
            obj.put("transactionStatusId", statusTransaksi);
            obj.put("paymentFixedPriceStatusId", statusPembayaran);
            obj.put("idClinic", idKlinik);
            obj.put("addressToVisit", alamat);
            obj.put("date", tanggal);
            obj.put("serviceList", layanan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
