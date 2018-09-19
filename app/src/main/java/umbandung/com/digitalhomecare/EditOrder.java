package umbandung.com.digitalhomecare;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umbandung.com.digitalhomecare.Model.transaksi.Content;
import umbandung.com.digitalhomecare.Model.transaksi.Transaksi;

public class EditOrder extends AppCompatActivity {

    private MySharedPrefernce mySharedPrefernce;
    private ProgressDialog progressDialog;
    private Gson gson;
    private Spinner spinnerStatusOrder, spinnerStatusBayar;
    private EditText etHargaPrediksi, etHargaFinal, etDiagnosa, etObat, etTindakan;
    private int statusOrderID;
    private int statusBayarID;
    private Long trxID;
    private TextView tvNomorOrder, tvNamaPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);
        mySharedPrefernce = new MySharedPrefernce();
        progressDialog = new ProgressDialog(this);
        gson = new GsonBuilder().create();
        final String[] mySharedPrefernceValue = mySharedPrefernce.getValue(this);
        final String orderid = mySharedPrefernce.getValueByKey(this, "ORDER_ID");
        Log.d("order_id detail", orderid);

        spinnerStatusOrder = (Spinner) findViewById(R.id.spinner_status);
        spinnerStatusBayar = (Spinner) findViewById(R.id.spinner_status_bayar);
        etHargaPrediksi = (EditText) findViewById(R.id.et_harga_prediksi);
        etHargaFinal = (EditText) findViewById(R.id.et_harga_final);
        etDiagnosa = (EditText) findViewById(R.id.et_diagnosa);
        etObat = (EditText) findViewById(R.id.et_obat);
        etTindakan = (EditText) findViewById(R.id.et_tindakan);
        tvNomorOrder = (TextView) findViewById(R.id.nomor_order);
        tvNamaPasien = (TextView) findViewById(R.id.nama_pasien);

        final ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, createStatusOrder);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, createStatusPayment);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusOrder.setAdapter(adapter1);
        spinnerStatusOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusOrderID = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerStatusBayar.setAdapter(adapter2);
        spinnerStatusBayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusBayarID = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getDetailData(mySharedPrefernceValue[7], orderid);
        Button btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUpdate(mySharedPrefernceValue[7], trxID);
            }
        });
    }

    private void doUpdate(final String accesstoken, Long pOrderID) {
        Log.d("accesstoken: ", accesstoken);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        final String trxid = String.valueOf(pOrderID);
        final String endpoint = "http://167.205.7.227:9028/api/transaction/"+trxid;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("transactionStatusId", new JSONObject().put("id", statusOrderID));
            json.put("paymentFixedPriceStatusId", new JSONObject().put("id", statusBayarID));
            json.put("predictionPrice", etHargaPrediksi.getText().toString());
            json.put("fixedPrice", Double.parseDouble(etHargaFinal.getText().toString()));
            json.put("medicine", etObat.getText().toString());
            json.put("diagnosis",etDiagnosa.getText().toString());
            json.put("nurseAction", etTindakan.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = json.toString();

        StringRequest request = new StringRequest(Request.Method.PUT, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("update order: ", response);
                Toast.makeText(EditOrder.this, "Update Berhasil", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("update order: ", error.getMessage());
                progressDialog.dismiss();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", accesstoken);
                return params;
            }
        };

        mRequestQueue.add(request);
    }


    private void getDetailData(final String accesstoken, final String pOrderID) {
        Log.d("accesstoken: ", accesstoken);
        Log.d("orderID: ", pOrderID);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        try {
            final String orderID = pOrderID;
            final String endpoint = "http://167.205.7.227:9028/api/transactionWithPaginationByField?page=0&size=10&sort=ASC&sortField=id&searchField=orderNumber&value="+pOrderID;
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);

            StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("detail trx: ", response);
                    umbandung.com.digitalhomecare.Model.transaksi.Transaksi transaksi = null;
                    try {
                        transaksi = gson.fromJson(new JSONObject(response).toString(), Transaksi.class);
                        if (transaksi.getContent().size() > 0) {
                            Content content = transaksi.getContent().get(0);
                            tvNomorOrder.setText(content.getOrderNumber());
                            tvNamaPasien.setText(content.getUserPatient().getFullName());
                            trxID = content.getId();
                            int statusTrx = content.getTransactionStatusId().getId().intValue();
                            statusOrderID = statusTrx;
                            spinnerStatusOrder.setSelection(statusTrx-1);
                            int statusBayar = content.getPaymentFixedPriceStatusId().getId().intValue();
                            statusBayarID = statusBayar;
                            spinnerStatusBayar.setSelection(statusBayar-1);
                            etHargaPrediksi.setText(content.getPredictionPrice());
                            etHargaFinal.setText(content.getFixedPrice().toString());
                            etObat.setText(content.getMedicine() == null ? "": String.valueOf(content.getMedicine()));
                            etDiagnosa.setText(content.getDiagnosis()==null? "": String.valueOf(content.getDiagnosis()));
                            etTindakan.setText(content.getNurseAction()==null? "":String.valueOf(content.getNurseAction()));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error: ", error.toString());
                    progressDialog.dismiss();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() {
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

    private static String[] createStatusOrder = new String[]{
            "On Validation",
            "On Process",
            "Failed",
            "Cancelled",
            "Expire",
            "Finish"
    };

    private static String[] createStatusPayment = new String[]{
            "Paid",
            "Unpaid"
    };
}
