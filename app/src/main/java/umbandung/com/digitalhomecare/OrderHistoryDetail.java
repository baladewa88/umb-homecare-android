package umbandung.com.digitalhomecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import umbandung.com.digitalhomecare.Model.transaksi.Content;
import umbandung.com.digitalhomecare.Model.transaksi.Transaksi;

public class OrderHistoryDetail extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private MySharedPrefernce mySharedPrefernce;
    private Gson gson;
    private TextView tvNomorOrder, tvNamaPasien, tvKodePasien,
                    tvTanggalOrder, tvAlamatVisit, tvNamaKlinik, tvStatus,
    tvTanggalTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_history);
        tvNomorOrder = (TextView) findViewById(R.id.nomor_order);
        tvNamaPasien = (TextView) findViewById(R.id.nama_pasien);
        tvKodePasien = (TextView) findViewById(R.id.kode_pasien);
        tvTanggalOrder = (TextView) findViewById(R.id.tanggal_order);
        tvAlamatVisit = (TextView) findViewById(R.id.alamat_visit);
        tvAlamatVisit = (TextView) findViewById(R.id.alamat_visit);
        tvNamaKlinik = (TextView) findViewById(R.id.nama_klinik);
        tvStatus = (TextView) findViewById(R.id.status_transaksi);
        tvTanggalTreatment = (TextView) findViewById(R.id.tanggal_treatment);

        mySharedPrefernce = new MySharedPrefernce();
        gson = new GsonBuilder().create();
        progressDialog = new ProgressDialog(this);
        String[] mySharedPrefernceValue = mySharedPrefernce.getValue(this);

        String orderid = mySharedPrefernce.getValueByKey(this, "ORDER_ID");
        Log.d("order_id detail", orderid);
        getDetailData(mySharedPrefernceValue[7], orderid);
    }

    private void getDetailData(final String accesstoken, final String pOrderID) {
        Log.d("accesstoken: ", accesstoken);
        Log.d("orderID: ", pOrderID);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        try {
            final String clinicID = mySharedPrefernce.getValueByKey(this, "CLINIC_ID");
            //final String clinicID = "1"; //dev
            final String orderID = pOrderID;
            //final String endpoint = "http://167.205.7.227:9028/api/transactionWithPaginationByFieldByIdClinic?page=0&size=10&sort=ASC&sortField=id&searchField=orderNumber&value="+pOrderID+"&clinicId="+clinicID;
            final String endpoint = "http://167.205.7.227:9028/api/transactionWithPaginationByField?page=0&size=10&sort=ASC&sortField=id&searchField=orderNumber&value="+pOrderID;
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);

            StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("detail trx: ",response);
                    Transaksi transaksi = null;
                    try {
                        transaksi = gson.fromJson(new JSONObject(response).toString(), Transaksi.class);
                        if(transaksi.getContent().size() > 0){
                            Content content = transaksi.getContent().get(0);
                            tvNomorOrder.setText(content.getOrderNumber());
                            tvNamaPasien.setText(content.getUserPatient().getFullName());
                            tvKodePasien.setText(content.getUserPatient().getPatientCode());
                            tvTanggalOrder.setText(DateUtil.dateFormatting(content.getDateOrderIn()));
                            tvAlamatVisit.setText(content.getAddressToVisit());
                            tvNamaKlinik.setText(content.getIdClinic().getNameOfClinic());
                            tvStatus.setText(content.getTransactionStatusId().getStatus());
                            tvTanggalTreatment.setText(content.getDateTreatementStart() == null ? "-" : DateUtil.dateFormatting(content.getDateTreatementStart()));

                        }
                    } catch (JSONException e) {
                       e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error: ",error.toString());
                    progressDialog.dismiss();
                }
            }){
                @Override
                public Map<String, String> getHeaders()
                {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", accesstoken);

                    return params;
                }
            };

            mRequestQueue.add(request);
        }catch (Error e){
            e.printStackTrace();
        }
    }
}
