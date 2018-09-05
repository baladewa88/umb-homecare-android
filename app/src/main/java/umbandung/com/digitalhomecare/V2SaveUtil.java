package umbandung.com.digitalhomecare;

public class V2SaveUtil {

	// Golongan
	public String nama, rm, harga;

	public int id;
	public String tanggal;

	public V2SaveUtil(int id, String tanggal, String nama, String harga, String rm) {
		

		this.tanggal = tanggal;
		this.nama = nama;
		this.rm = rm;
		this.harga = harga;
		this.id = id;

	}

	public V2SaveUtil(String tanggal, String nama, String harga, String rm) {


		this.tanggal = tanggal;
		this.nama = nama;
		this.rm = rm;
		this.harga = harga;

	}


	public V2SaveUtil(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
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

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}
}
