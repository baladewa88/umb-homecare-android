package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import umbandung.com.digitalhomecare.Model.Auth;
import umbandung.com.digitalhomecare.Rest.ApiClient;
import umbandung.com.digitalhomecare.Rest.ApiInterface;

/**
 * Created by Arkhan on 7/23/2018.
 */

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //buat shared preference
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private MySharedPrefernce mySharedPrefernce;

    private Button btnLogin;
    private EditText etUsername, etPassword;
    private Spinner spinner;
    private String roleParam, email, password;
    private String globalUrl = "167.205.7.227:9028/authenticate/";
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 150000;

    ApiInterface mApiInterface;

    JSONObject json_obj, userData;

    //Hubungan sama volley
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mySharedPrefernce = new MySharedPrefernce();
        String[] data = mySharedPrefernce.getValue(Login.this);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
//        roleParam = "userAdmin";

        spinner = (Spinner) findViewById(R.id.spinner_role);
        // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.role, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()){
                    Toast.makeText(Login.this, "Email dan Password yang diberikan salah. Silahkan Ulangi", Toast.LENGTH_LONG).show();
//                    btnLogin.setEnabled(true);
                }else{
                    postReq();
//                authLogin();
                }


            }
        });

    }

    private void authLogin(){
        Call<Auth> loginUser = mApiInterface.getAuth(roleParam, email, password);
        loginUser.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, retrofit2.Response<Auth> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "LOGIN GAGAL", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                Log.e("GAGAL LOGIN", t.getMessage());
                Toast.makeText(getApplicationContext(), "LOGIN GAGAL "+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
    private void postReq(){
        final String urlLogin = "http://167.205.7.227:9028/authenticate/"+roleParam ;
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, urlLogin,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        // response
//                        Log.e("Response", response);
                        try {
                            json_obj = new JSONObject(response);
                            userData = new JSONObject(json_obj.getString("user"));
                            Log.e("TOKEN",json_obj.getString("token"));
                            Log.e("dateBirth",userData.getString("dateBirth"));
                            Log.e("nama",userData.getString("fullName"));
                            Log.e("roles atas",userData.getString("roles"));
                            Log.e("roles array",userData.getString("id"));

                            mySharedPrefernce.save(Login.this,
                                    userData.getString("fullName"),
                                    userData.getString("address"),
                                    userData.getString("email"),
                                    userData.getString("phoneNumber"),
                                    userData.getString("gender"),
                                    userData.getString("roles"),
                                    userData.getString("dateBirth"),
                                    json_obj.getString("token"),
                                    userData.getString("id"));

                            ifLogin(userData.getString("roles"));




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("Error.Response", "error response =>"+urlLogin+" ==> "+error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };
        mRequestQueue.add(postRequest);
    }

    public void ifLogin(String param){

        if(param.equals("[\"ROLE_PATIENT\"]")){
            Log.e("ROLES","PASIEN");

            Intent iPasien = new Intent(Login.this, DashboardMain.class);
            startActivity(iPasien);

        }else if(param.equals("[\"ROLE_ADMIN\"]")){
            Log.e("ROLES","ADMIN");
            Intent iAdmin = new Intent(Login.this, DashboardUtama.class);
            startActivity(iAdmin);

        }else if(param.equals("[\"ROLE_NURSE\"]")){
            Log.e("ROLES","NURSE");
            Intent iMedis = new Intent(Login.this, DashboardMedis.class);
            startActivity(iMedis);

        }else if(param.equals("[\"ROLE_DOCTOR\"]")){
            Log.e("ROLES","DOCTOR");
            Intent iMedis = new Intent(Login.this, DashboardMedis.class);
            startActivity(iMedis);

        }else if(param.equals("[\"ROLE_CLINIC\"]")){
            Log.e("ROLES","CLINIC");
            Intent iKlinik = new Intent(Login.this, DashboardKlinik.class);
            startActivity(iKlinik);
        }else {
            Log.e("ROLES","NONE");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

        if(adapterView.getItemAtPosition(i).equals("Admin")){
            roleParam = "userAdmin";
        }else if(adapterView.getItemAtPosition(i).equals("Pasien")){
            roleParam = "userPatient";
        }else if(adapterView.getItemAtPosition(i).equals("Klinik")){
            roleParam = "userClinic";
        }else if(adapterView.getItemAtPosition(i).equals("Perawat")){
            roleParam = "userNurse";
        }else if(adapterView.getItemAtPosition(i).equals("Dokter")){
            roleParam = "userDoctor";
        }

//        Toast.makeText(getApplicationContext(),"Changed Value to "+roleParam, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(getApplicationContext(), "Isikan Role Anda", Toast.LENGTH_LONG).show();

    }

    public boolean validate() {
        boolean valid = true;

        email = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etUsername.setError("Format email tidak sesuai");
            valid = false;
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("Password harus diisi");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }

}
