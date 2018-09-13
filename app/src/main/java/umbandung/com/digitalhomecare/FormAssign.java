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
import android.widget.Toast;

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

import umbandung.com.digitalhomecare.Model.employee.Content;
import umbandung.com.digitalhomecare.Model.employee.Employee;

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
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_assign);

        spinnerCategorySearch = (Spinner) findViewById(R.id.spinner_kategori);
        btnSerch = (Button) findViewById(R.id.btn_cari);
        etKeyword = (EditText) findViewById(R.id.et_keyword);
        radioGroup = (RadioGroup) findViewById(R.id.role_job);
        lyResult = (LinearLayout) findViewById(R.id.ly_result);

        //inisiasi
        nurseFieldMap = createNurseFieldMap();
        doctorFieldMap = createDoctorFieldMap();
        mySharedPrefernce = new MySharedPrefernce();
        gson = new GsonBuilder().create();
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.list_results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

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
    }

    private void doSearch() {
        //final String clinicID = mySharedPrefernce.getValueByKey(this, "CLINIC_ID");
        final String clinicID = "1"; //dev
        final String accesstoken = mySharedPrefernce.getValueByKey(this, mySharedPrefernce.PREFS_KEY);
        progressDialog.setMessage("Mohon Tunggu");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.show();

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
                    }


                    //Toast.makeText(FormAssign.this, obj.getString("address"), Toast.LENGTH_SHORT).show();
                    mAdapter = new EmployeeRecyclerView(employeeID,names,codes,license,phoneNumbers,
                            addresss,emails,placeBirths,dateBirths,
                            genders,religions,clinicsID,clinicsNames, roles);
                    mAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mAdapter);
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
