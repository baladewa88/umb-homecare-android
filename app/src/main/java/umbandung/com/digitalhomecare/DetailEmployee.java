package umbandung.com.digitalhomecare;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import umbandung.com.digitalhomecare.Model.employee.Content;
import umbandung.com.digitalhomecare.Model.employee.Employee;

public class DetailEmployee extends AppCompatActivity {

    private TextView tvName, tvKode, tvAddress, tvTelp, tvLicense, tvEmail ,
                        tvTTL, tvGender, tvReligion, tvStatus, tvClinic;
    private MySharedPrefernce mySharedPrefernce;
    private Gson gson;
    private ProgressDialog progressDialog;
    private Button btnPilih;
    private DetailEmployee mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_employee);
        tvName = (TextView) findViewById(R.id.employee_name);
        tvKode = (TextView) findViewById(R.id.employee_code);
        tvAddress = (TextView) findViewById(R.id.employee_address);
        tvTelp = (TextView) findViewById(R.id.employee_telp);
        tvLicense = (TextView) findViewById(R.id.employee_license);
        tvEmail = (TextView) findViewById(R.id.employee_email);
        tvTTL = (TextView) findViewById(R.id.employee_ttl);
        tvGender = (TextView) findViewById(R.id.employee_gender);
        tvReligion = (TextView) findViewById(R.id.employee_religion);
        tvStatus = (TextView) findViewById(R.id.employee_status);
        tvClinic = (TextView) findViewById(R.id.employee_clinic);
        btnPilih = (Button) findViewById(R.id.btn_pilih);

        mContext = this;
        mySharedPrefernce = new MySharedPrefernce();
        gson = new GsonBuilder().create();
        progressDialog = new ProgressDialog(this);

        getEmployeeDetail();
        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assign();
            }
        });

    }

    private void assign() {
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        final String employeeId = mySharedPrefernce.getValueByKey(this, "EMPLOYEE_ID");
        final String searchType = mySharedPrefernce.getValueByKey(this, "SEARCH_TYPE");
        final String trxID = mySharedPrefernce.getValueByKey(this, "TRX_ID");

        String nurseParam = "assignNurse";
        String doctorParam = "assignDoctor";
        String param1 = searchType.equals("0") ? doctorParam : nurseParam;
        final String endpoint = "http://167.205.7.227:9028/api/transaction/"+param1+"/";
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("acceptanceStatus", false);
            json.put("rateStatus", false);
            String text = searchType.equals("0") ? "idDoctor":"idNurse";
            json.put(text, new JSONObject().put("id", employeeId));
            json.put("idTransaction", new JSONObject().put("id", trxID));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = json.toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("assign", response);
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);
                    Intent intent = new Intent(mContext, FormAssign.class);
                    mContext.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Log.e("assign", error.toString());
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

        mRequestQueue.add(stringRequest);

    }

    private void getEmployeeDetail() {
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        final String employeeId = mySharedPrefernce.getValueByKey(this, "EMPLOYEE_ID");
        final String searchType = mySharedPrefernce.getValueByKey(this, "SEARCH_TYPE");

        String nurseParam = "nursesWithPaginationByField?";
        String doctorParam = "doctorsWithPaginationByField?";
        String param1 = searchType.equals("0") ? doctorParam : nurseParam;
        StringBuilder param2 = new StringBuilder();
        param2.append("id&value="+employeeId);
        final String endpoint = "http://167.205.7.227:9028/api/"+param1+"page=0&size=10&sort=ASC&sortField=id&searchField="+param2;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("employee", response);
                try {
                    Employee employee = gson.fromJson(new JSONObject(response).toString(), Employee.class);
                    if(employee.getContent().size() > 0){
                            Content content = employee.getContent().get(0);
                            tvName.setText(content.getFullName());
                            tvKode.setText(content.getDoctorCode() != null ? content.getDoctorCode() : content.getNurseCode());
                            tvAddress.setText(content.getAddress());
                            tvTelp.setText(content.getPhoneNumber());
                            tvLicense.setText(content.getRegisterNumber() != null ? content.getRegisterNumber() : content.getSipp());
                            tvEmail.setText(content.getEmail());
                            tvTTL.setText(content.getPlaceBirth()+", "+DateUtil.dateFormatting(content.getDateBirth()));
                            tvGender.setText(content.getGender());
                            tvReligion.setText(content.getReligion());
                            tvStatus.setText(content.getStatus().getStatus());
                            tvClinic.setText(content.getClinic().getNameOfClinic());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", accesstoken);
                return params;
            }
        };

        mRequestQueue.add(request);
    }
}
