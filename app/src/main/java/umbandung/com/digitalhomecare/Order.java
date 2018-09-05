package umbandung.com.digitalhomecare;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class Order extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener{

    TextView labelTanggal, labelHarga;
    Spinner listKlinik;
    AutoCompleteTextView jenisLayanan;
    Button btnRasioItem, btnOrder, btnCancel;
//    String[] listLayanan, listHarga;
    String harga ="0";
//    private String globalUrl = "http://standarpangan.pom.go.id/cekbtpservices/";
    //	private String globalUrl = "http://unitbisnismu.com/btp4/service/v2/";
    String txtJenisPangan;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter myRecyclerViewAdapter;
    private ArrayList<OrderLayananUtil> arrayLayanan = new ArrayList<>();

    //Hubungan sama volley
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String urlKlinik = "http://167.205.7.227:9028/api/clinics/";
    private String urlLayanan = "http://167.205.7.227:9028/api/listOfServices/1";
    ArrayList<LayananUtil> dataDevice;
    Spinner spLayanan;

    public V2DatabaseHandler db;

    JSONArray riArray = new JSONArray();
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderitem);


        MySharedPrefernce mSettings = new MySharedPrefernce();
        String[] datas = mSettings.getValue(Order.this);

        dataDevice = new ArrayList<LayananUtil>();

        token = datas[7];
        Log.e("TOKEN ORDER",token);
        sendAndRequestResponse();
        Log.e("Isi data device", ""+dataDevice.size());


//        Toast.makeText(getApplicationContext(),"Masuk ke order", Toast.LENGTH_SHORT).show();
        labelTanggal = (TextView)findViewById(R.id.label_tanggal);
        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String today = formatter.format(date);

        DateFormat formatter2 = new SimpleDateFormat("EEEE");
        String hari = formatter2.format(date);

        if (hari.equals("Monday")) {
            hari="Senin";
        }else if (hari.equals("Tuesday")) {
            hari="Selasa";
        }else if (hari.equals("Wednesday")) {
            hari="Rabu";
        }else if (hari.equals("Thursday")) {
            hari="Kamis";
        }else if (hari.equals("Friday")) {
            hari="Jum'at";
        }else if (hari.equals("Saturday")) {
            hari="Sabtu";
        }else if (hari.equals("Sunday")) {
            hari="Minggu";
        }

        labelTanggal.setText(hari+", "+today+" WIB");

        labelHarga = (TextView)findViewById(R.id.label_harga);
        labelHarga.setText("Rp. 0,-");

        listKlinik = (Spinner) findViewById(R.id.sp_klinik);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.klinik, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        listKlinik.setAdapter(adapter);

//        listLayanan = getResources().getStringArray(R.array.layanan);
//        listHarga = getResources().getStringArray(R.array.layananharga);

        linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        myRecyclerView = (RecyclerView) findViewById(R.id.listrasioitem);
        myRecyclerViewAdapter = new RecyclerAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

        jenisLayanan = (AutoCompleteTextView) findViewById(R.id.etNamaBtp);

//        ArrayAdapter<String> adapterGolBtp = new ArrayAdapter<String>(
//                getApplicationContext(), R.layout.rasio_list,
//                listLayanan);
//        jenisLayanan.setAdapter(adapterGolBtp);

        spLayanan = (Spinner)findViewById(R.id.spLayanan);
        ArrayAdapter<LayananUtil> adapterLayanan = new ArrayAdapter<LayananUtil>(this, android.R.layout.simple_spinner_dropdown_item, dataDevice);
        spLayanan.setAdapter(adapterLayanan);

//        spLayanan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });

//        jenisLayanan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),"Jenis Layanan Clicked", Toast.LENGTH_LONG).show();
//                int h = 0;
//                h=h+Integer.parseInt(listHarga[i]);
//                harga = ""+h;
//
//            }
//        });
        jenisLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"oncliklistener Clicked", Toast.LENGTH_LONG).show();

            }
        });


        btnRasioItem = (Button) findViewById(R.id.btnTambahJenisBtp);
        btnRasioItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String newName = jenisLayanan.getText().toString();


//				Toast.makeText(getApplicationContext(), "Counted Recyclerview Item => "+myRecyclerViewAdapter.getItemCount(), Toast.LENGTH_LONG).show();

                    if(!newName.equals("")){
                        if(myRecyclerViewAdapter.getItemCount()>1){
                            myRecyclerViewAdapter.add(1, newName);
                        }else{
                            myRecyclerViewAdapter.add(0, newName);
                        }

                        arrayLayanan.add(new OrderLayananUtil(newName, "", ""));
                        jenisLayanan.setText("");
                    }

                labelHarga.setText("Rp. "+harga+",-");


            }
        });


        btnOrder = (Button)findViewById(R.id.btnHitungRasio);
        btnOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db = new V2DatabaseHandler(Order.this);
                db.addData(new V2SaveUtil(getDateTime(),
                        "Sutadi Triputra",
                        harga,
                        "24543"
                        ));

                Toast.makeText(getApplicationContext(), "Data Disimpan", Toast.LENGTH_LONG).show();

//                for(int y =0; y<arrayLayanan.size(); y++){
//                    riArray.put(arrayLayanan.get(y).getJSONObject());

//					Toast.makeText(RasioItem.this, riArray.toString(), Toast.LENGTH_LONG).show();
//                }

//                new AsyncFetch().execute();

            }
        });
    }

    @Override
    public void onItemClick(RecyclerAdapter.ItemHolder item, int position) {

        Toast.makeText(this,
                "Data " + position + " : " + item.getItemName()+" telah dihapus",
                Toast.LENGTH_SHORT).show();
        myRecyclerViewAdapter.remove(position);

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void sendAndRequestResponse() {

        Log.e("Token Request", token);

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, urlLayanan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

                try {
//                    JSONObject json_obj = new JSONObject(response);
//                    Log.e("JSON RESPONSE",""+json_obj);
//                    JSONArray jsonResponse  = json_obj.
                    JSONArray jsonResponse = new JSONArray(response);

                    for (int i=0; i<jsonResponse.length(); i++){
                        JSONObject koordinat = jsonResponse.getJSONObject(i);
                        LayananUtil data = new LayananUtil();
                        data.setId(koordinat.getString("id"));
                        data.setName(koordinat.getString("nameOfservices"));
                        data.setCode(koordinat.getString("codeOfservices"));
                        data.setPrice(koordinat.getString("price"));

                        Log.e("Json Array Response",i+":"+koordinat.getString("long")+" - "+koordinat.getString("lat"));

                        dataDevice.add(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Send Request","Error :" + error.toString());
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("Authorization", token);
                return params;
            }
        };

        mRequestQueue.add(mStringRequest);
    }
}
