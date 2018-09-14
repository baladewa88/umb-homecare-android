package umbandung.com.digitalhomecare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umbandung.com.digitalhomecare.Model.transaksi.Content;
import umbandung.com.digitalhomecare.Model.transaksi.Transaksi;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class OrderMedis extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TransaksiRecyclerView mAdapter;
    private ProgressDialog progressDialog;
    private MySharedPrefernce mySharedPrefernce;
    private Gson gson;

    private List<String> orderNumbers = new ArrayList<>();
    private List<String> statuss = new ArrayList<>();
    private List<String> dateOrders = new ArrayList<>();
    private List<String> patientsName = new ArrayList<>();
    private List<String> transactions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pasien_medis);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mySharedPrefernce = new MySharedPrefernce();
        gson = new GsonBuilder().create();
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_order_pasien);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        String[] mySharedPrefernceValue = mySharedPrefernce.getValue(this);

        getListTransaction(mySharedPrefernceValue[7]);


    }

    private void getListTransaction(final String accesstoken) {
        Log.d("accesstoken: ", accesstoken);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        try {
            final String clinicID = mySharedPrefernce.getValueByKey(this, "CLINIC_ID");
            Log.d("Order, clinic_id", mySharedPrefernce.getValueByKey(this, "CLINIC_ID"));
            //final String clinicID = "1"; //dev
            final String endpoint = "http://167.205.7.227:9028/api/transactionWithPaginationByIdClinic?page=0&size=10&sort=ASC&sortField=id&clinicId=";
            RequestQueue mRequestQueue = Volley.newRequestQueue(this);

            StringRequest request = new StringRequest(Request.Method.GET, endpoint + clinicID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        orderNumbers.clear();
                        dateOrders.clear();
                        patientsName.clear();
                        statuss.clear();
                        transactions.clear();
                        Log.d("order: ",response);
                        Transaksi transaksi = gson.fromJson(new JSONObject(response).toString(),Transaksi.class);
                        if(transaksi.getContent().size() > 0){
                            for(int i=0; i<transaksi.getContent().size();i++){
                                Content content = transaksi.getContent().get(i);
                                orderNumbers.add(content.getOrderNumber());
                                dateOrders.add(content.getDateOrderIn());
                                statuss.add(content.getTransactionStatusId().getStatus());
                                patientsName.add(content.getUserPatient().getFullName());
                                transactions.add(String.valueOf(content.getId()));
                            }

                            mAdapter = new TransaksiRecyclerView(orderNumbers, dateOrders, patientsName, statuss, transactions);
                            mAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(mAdapter);
                        }
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
                    HashMap<String, String>  params = new HashMap<String, String>();
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