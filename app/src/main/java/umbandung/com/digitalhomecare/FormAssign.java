package umbandung.com.digitalhomecare;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import umbandung.com.digitalhomecare.Model.doctor.Doctor;
import umbandung.com.digitalhomecare.Model.employee.Content;
import umbandung.com.digitalhomecare.Model.employee.Employee;
import umbandung.com.digitalhomecare.Model.nurse.Nurse;

public class FormAssign extends AppCompatActivity {
    String[] nurseFieldArray = new String[]{
            "Nama",
            "Kode",
            "Nomor Telepon",
            "Email",
            "SIPP"
    };

    private static HashMap<String, String> createNurseFieldMap()
    {
        HashMap<String,String> myMap = new HashMap<String,String>();
        myMap.put("Nama", "fullName");
        myMap.put("Kode", "nurseCode");
        myMap.put("Email", "email");
        myMap.put("Nomor Telepon", "phoneNumber");
        myMap.put("SIPP", "sipp");
        return myMap;
    }

    String[] doctorFieldArray = new String[]{
            "Nama",
            "Kode",
            "Nomor Telepon",
            "Email",
            "Nomor Registrasi"
    };

    private static HashMap<String, String> createDoctorFieldMap()
    {
        HashMap<String,String> myMap = new HashMap<String,String>();
        myMap.put("Nama", "fullName");
        myMap.put("Kode", "doctorCode");
        myMap.put("Email", "email");
        myMap.put("Nomor Telepon", "phoneNumber");
        myMap.put("Nomor Registrasi", "registerNumber");
        return myMap;
    }

    //inizialize
    private HashMap<String, String> nurseFieldMap;
    private HashMap<String, String> doctorFieldMap;
    private MySharedPrefernce mySharedPrefernce;
    private Gson gson;
    private ProgressDialog progressDialog;
    private Spinner spinnerCategorySearch;
    private Button btnSerch;
    private EditText etKeyword;
    private RadioGroup radioGroup;
    private int searchType = 0;
    private String searchField = "";
    private LinearLayout lyResult;
    private List<String> employeeID = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> codes = new ArrayList<>();
    private List<String> license = new ArrayList<>();
    private List<String> phoneNumbers = new ArrayList<>();
    private List<String> addresss = new ArrayList<>();
    private List<String> emails = new ArrayList<>();
    private List<String> placeBirths = new ArrayList<>();
    private List<String> dateBirths = new ArrayList<>();
    private List<String> genders = new ArrayList<>();
    private List<String>  religions = new ArrayList<>();
    private List<String>  clinicsID = new ArrayList<>();
    private List<String>  clinicsNames = new ArrayList<>();
    private List<String>  roles = new ArrayList<>();
    private EmployeeRecyclerView mAdapter;
    private EmployeeRecyclerView mAdapter1;
    private EmployeeRecyclerView mAdapter2;
    private RecyclerView recyclerView;
    private LinearLayout lyNurseAssigned;
    private LinearLayout lyDoctorAssigned;
    private RecyclerView nurseRecyclerView;
    private RecyclerView doctorRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_assign);

        spinnerCategorySearch = (Spinner) findViewById(R.id.spinner_kategori);
        btnSerch = (Button) findViewById(R.id.btn_cari);
        etKeyword = (EditText) findViewById(R.id.et_keyword);
        radioGroup = (RadioGroup) findViewById(R.id.role_job);
        lyResult = (LinearLayout) findViewById(R.id.ly_result);
        lyNurseAssigned = (LinearLayout) findViewById(R.id.ly_nurse_assigned);
        lyDoctorAssigned = (LinearLayout) findViewById(R.id.ly_doctor_assigned);
        TextView tvOrderNumber = (TextView) findViewById(R.id.nomor_order);



        //inisiasi
        nurseFieldMap = createNurseFieldMap();
        doctorFieldMap = createDoctorFieldMap();
        mySharedPrefernce = new MySharedPrefernce();
        gson = new GsonBuilder().create();
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        nurseRecyclerView = (RecyclerView) findViewById(R.id.list_nurse_assigned);
        nurseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        doctorRecyclerView = (RecyclerView) findViewById(R.id.list_doctor_assigned);
        doctorRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, doctorFieldArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<String> adapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nurseFieldArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorySearch.setAdapter(adapter1);
        spinnerCategorySearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(searchType == 0){
                    searchField = doctorFieldMap.get(doctorFieldArray[position]);
                }else{
                    searchField = nurseFieldMap.get(nurseFieldArray[position]);
                }
                Toast.makeText(FormAssign.this, searchField, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    //Toast.makeText(FormAssign.this, rb.getText(), Toast.LENGTH_SHORT).show();
                    Log.d("chekedID", String.valueOf(checkedId));
                    switch (checkedId){
                        case R.id.radioDokter:
                            searchType = 0;
                            spinnerCategorySearch.setAdapter(adapter1);
                            break;
                        case R.id.radioPerawat:
                            searchType = 1;
                            spinnerCategorySearch.setAdapter(adapter2);
                            break;
                    }
                }
            }
        });

        btnSerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etKeyword.getText().toString() != ""){
                    doSearch();
                }
                else {
                    Toast.makeText(FormAssign.this, "Mohon isi keyword", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String orderid = mySharedPrefernce.getValueByKey(this, "ORDER_ID");
        tvOrderNumber.setText(orderid);

        getNurseAssignedList();
    }

    private void getNurseAssignedList() {
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        final String trxID = mySharedPrefernce.getValueByKey(this, "TRX_ID");

        final String endpoint = "http://167.205.7.227:9028/api/transaction/getNurseList/"+trxID;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("nurse_assigned", response);
                try {
                    employeeID.clear();
                    names.clear();
                    codes.clear();
                    license.clear();
                    phoneNumbers.clear();
                    addresss.clear();
                    emails.clear();
                    placeBirths.clear();
                    dateBirths.clear();
                    genders.clear();
                    religions.clear();
                    clinicsID.clear();
                    clinicsNames.clear();
                    roles.clear();
                    Nurse nurse= gson.fromJson(new JSONObject(response).toString(), Nurse.class);
                    if(nurse.getContent().size() > 0){
                        lyNurseAssigned.setVisibility(View.VISIBLE);
                        for(int i = 0; i<nurse.getContent().size();i++){
                            umbandung.com.digitalhomecare.Model.nurse.Content content = nurse.getContent().get(i);
                            employeeID.add(content.getIdNurse().getId().toString());
                            names.add(content.getIdNurse().getFullName());
                            codes.add(content.getIdNurse().getNurseCode());
                            license.add(content.getIdNurse().getSipp());
                            roles.add("perawat");
                            phoneNumbers.add(content.getIdNurse().getPhoneNumber());
                            addresss.add(content.getIdNurse().getAddress());
                            emails.add(content.getIdNurse().getEmail());
                            placeBirths.add(content.getIdNurse().getPlaceBirth());
                            dateBirths.add(content.getIdNurse().getDateBirth());
                            genders.add(content.getIdNurse().getGender());
                            religions.add(content.getIdNurse().getReligion());
                            clinicsID.add(content.getIdNurse().getClinic().getId().toString());
                            clinicsNames.add(content.getIdNurse().getClinic().getNameOfClinic());
                        }

                        mAdapter1 = new EmployeeRecyclerView(employeeID,names,codes,license,phoneNumbers,
                                addresss,emails,placeBirths,dateBirths,
                                genders,religions,clinicsID,clinicsNames, roles, employeeID.size());
                        mAdapter1.notifyDataSetChanged();
                        nurseRecyclerView.setAdapter(mAdapter1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getDoctorAssignedList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                getDoctorAssignedList();
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

    private void getDoctorAssignedList() {
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        final String trxID = mySharedPrefernce.getValueByKey(this, "TRX_ID");

        final String endpoint = "http://167.205.7.227:9028/api/transaction/getDoctorList/"+trxID;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.d("doctor_assigned", response);
                try {
                    employeeID.clear();
                    names.clear();
                    codes.clear();
                    license.clear();
                    phoneNumbers.clear();
                    addresss.clear();
                    emails.clear();
                    placeBirths.clear();
                    dateBirths.clear();
                    genders.clear();
                    religions.clear();
                    clinicsID.clear();
                    clinicsNames.clear();
                    roles.clear();
                    Doctor doctor = gson.fromJson(new JSONObject(response).toString(), Doctor.class);
                    if(doctor.getContent().size() > 0){
                        lyDoctorAssigned.setVisibility(View.VISIBLE);
                        for(int i = 0; i<doctor.getContent().size();i++){
                            umbandung.com.digitalhomecare.Model.doctor.Content content = doctor.getContent().get(i);
                            employeeID.add(content.getIdDoctor().getId().toString());
                            names.add(content.getIdDoctor().getFullName());
                            codes.add(content.getIdDoctor().getDoctorCode());
                            license.add(content.getIdDoctor().getRegisterNumber());
                            roles.add("dokter");
                            phoneNumbers.add(content.getIdDoctor().getPhoneNumber());
                            addresss.add(content.getIdDoctor().getAddress());
                            emails.add(content.getIdDoctor().getEmail());
                            placeBirths.add(content.getIdDoctor().getPlaceBirth());
                            dateBirths.add(content.getIdDoctor().getDateBirth());
                            genders.add(content.getIdDoctor().getGender());
                            religions.add(content.getIdDoctor().getReligion());
                            clinicsID.add(content.getIdDoctor().getClinic().getId().toString());
                            clinicsNames.add(content.getIdDoctor().getClinic().getNameOfClinic());
                        }

                        mAdapter2 = new EmployeeRecyclerView(employeeID,names,codes,license,phoneNumbers,
                                addresss,emails,placeBirths,dateBirths,
                                genders,religions,clinicsID,clinicsNames, roles, employeeID.size());
                        mAdapter2.notifyDataSetChanged();
                        doctorRecyclerView.setAdapter(mAdapter2);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    private void doSearch() {
        final String clinicID = mySharedPrefernce.getValueByKey(this, "CLINIC_ID");
        //final String clinicID = "1"; //dev
        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

        mySharedPrefernce.store(this, "SEARCH_TYPE", String.valueOf(searchType));
        String nurseParam = "nursesWithPaginationByFieldByIdClinic?";
        String doctorParam = "doctorsWithPaginationByFieldByIdClinic?";
        String param1 = searchType == 0 ? doctorParam : nurseParam;
        StringBuilder param2 = new StringBuilder();
        param2.append(searchField+"&");
        param2.append("value="+etKeyword.getText().toString()+"&");
        param2.append("clinicId="+clinicID);


        final String endpoint = "http://167.205.7.227:9028/api/"+param1+"page=0&size=10&sort=ASC&sortField=id&searchField="+param2;
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, endpoint, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("members", response);
                try {
                    employeeID.clear();
                    names.clear();
                    codes.clear();
                    license.clear();
                    phoneNumbers.clear();
                    addresss.clear();
                    emails.clear();
                    placeBirths.clear();
                    dateBirths.clear();
                    genders.clear();
                    religions.clear();
                    clinicsID.clear();
                    clinicsNames.clear();
                    roles.clear();
                    Employee employee = gson.fromJson(new JSONObject(response).toString(), Employee.class);
                    if(employee.getContent().size() > 0){
                        lyResult.setVisibility(View.VISIBLE);
                        for(int i = 0; i<employee.getContent().size();i++){
                            Content content = employee.getContent().get(i);
                            employeeID.add(content.getId().toString());
                            names.add(content.getFullName());
                            codes.add((searchType ==0 ? content.getDoctorCode() : content.getNurseCode() ));
                            license.add((searchType == 0? content.getRegisterNumber(): content.getSipp()));
                            roles.add((searchType == 0? "dokter" : "perawat"));
                            phoneNumbers.add(content.getPhoneNumber());
                            addresss.add(content.getAddress());
                            emails.add(content.getEmail());
                            placeBirths.add(content.getPlaceBirth());
                            dateBirths.add(content.getDateBirth());
                            genders.add(content.getGender());
                            religions.add(content.getReligion());
                            clinicsID.add(content.getClinic().getId().toString());
                            clinicsNames.add(content.getClinic().getNameOfClinic());
                        }

                        mAdapter = new EmployeeRecyclerView(employeeID,names,codes,license,phoneNumbers,
                                addresss,emails,placeBirths,dateBirths,
                                genders,religions,clinicsID,clinicsNames, roles, employeeID.size());
                        mAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(mAdapter);
                    }else{
                        Toast.makeText(FormAssign.this, "Hasil Tidak ditemukan", Toast.LENGTH_SHORT).show();
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
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("Authorization", accesstoken);
                return params;
            }
        };

        mRequestQueue.add(request);
    }
}
