package umbandung.com.digitalhomecare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import umbandung.com.digitalhomecare.Model.PostPutDelOrder;
import umbandung.com.digitalhomecare.Rest.ApiClient;
import umbandung.com.digitalhomecare.Rest.ApiInterface;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class Order extends AppCompatActivity implements RecyclerAdapter.OnItemClickListener{

    TextView labelTanggal, labelHarga;
    Spinner spKlinik;
    AutoCompleteTextView jenisLayanan;
    Button btnRasioItem, btnOrder, btnCancel;
//    String[] listLayanan, listHarga;
    Integer harga = 0;
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
    private String urlLayanan = "http://167.205.7.227:9028/api/listOfservices/1";
    List<LayananUtil> dataLayanan;
    List<KlinikUtil> dataKlinik;
    List<Integer> selectedLayanan = new ArrayList<Integer>();

    //hubungan sama retrofit
    public ApiInterface mApiInterface;

    public V2DatabaseHandler db;

    JSONArray riArray = new JSONArray();
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderitem);


        MySharedPrefernce mSettings = new MySharedPrefernce();
        String[] datas = mSettings.getValue(Order.this);



        token = datas[7];
        Log.e("TOKEN ORDER",token);
        mApiInterface = ApiClient.getClient(token).create(ApiInterface.class);
//        sendAndRequestResponse();
        getKlinik();



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
        labelHarga.setText("Rp. " + harga +",-");
        final LinearLayout lLayoutLayanan = (LinearLayout) findViewById(R.id.layoutLayanan);

        spKlinik = (Spinner) findViewById(R.id.sp_klinik);
        // Create an ArrayAdapter using the string array and a default spinner layout
        Call<List<KlinikUtil>> getClinics = mApiInterface.getClinics();
        final Callback<List<LayananUtil>> getServicesCallback = new Callback<List<LayananUtil>>() {
            @Override
            public void onResponse(Call<List<LayananUtil>> call, retrofit2.Response<List<LayananUtil>> response) {
                selectedLayanan.removeAll(selectedLayanan);
                dataLayanan = response.body();
                harga = 0;
                labelHarga.setText("Rp. " + String.format("%d", harga) + ",-");
                for(int i = 0; i < dataLayanan.size(); i++) {
                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setId(i);
                    cb.setText(dataLayanan.get(i).getNameOfservices() + " (" + String.format("%,d", dataLayanan.get(i).getPrice()) + ")");
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked) {
                                harga += dataLayanan.get(buttonView.getId()).getPrice();
                                labelHarga.setText("Rp. " + String.format("%,d", harga) + ",-");
                                selectedLayanan.add(dataLayanan.get(buttonView.getId()).getId());
                            }
                            else {
                                harga -= dataLayanan.get(buttonView.getId()).getPrice();
                                labelHarga.setText("Rp. " + String.format("%,d", harga) + ",-");
                                selectedLayanan.remove(dataLayanan.get(buttonView.getId()).getId());
                            }
                        }
                    });
                    lLayoutLayanan.addView(cb);
                }
            }

            @Override
            public void onFailure(Call<List<LayananUtil>> call, Throwable t) {

            }
        };
        getClinics.enqueue(new Callback<List<KlinikUtil>>() {
            @Override
            public void onResponse(Call<List<KlinikUtil>> call, retrofit2.Response<List<KlinikUtil>> response) {
                ArrayAdapter<KlinikUtil> adapter = new ArrayAdapter<KlinikUtil> (getApplicationContext(), android.R.layout.simple_spinner_item, response.body());
                spKlinik.setAdapter(adapter);
                dataKlinik = response.body();
                spKlinik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        lLayoutLayanan.removeAllViews();
                        KlinikUtil selectedKlinik = dataKlinik.get(position);
                        Call <List<LayananUtil>> getServices = mApiInterface.getServices(selectedKlinik.getId());
                        getServices.enqueue(getServicesCallback);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<KlinikUtil>> call, Throwable t) {

            }
        });

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.klinik, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        listKlinik.setAdapter(adapter);

//        listLayanan = getResources().getStringArray(R.array.layanan);
//        listHarga = getResources().getStringArray(R.array.layananharga);

        linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        myRecyclerView = (RecyclerView) findViewById(R.id.listrasioitem);
        myRecyclerViewAdapter = new RecyclerAdapter(this);
        myRecyclerViewAdapter.setOnItemClickListener(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);


//        ArrayAdapter<String> adapterGolBtp = new ArrayAdapter<String>(
//                getApplicationContext(), R.layout.rasio_list,
//                listLayanan);
//        jenisLayanan.setAdapter(adapterGolBtp);


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


        btnRasioItem = (Button) findViewById(R.id.btnTambahJenisBtp);

//        @Override
//        public void onLoad

        btnOrder = (Button)findViewById(R.id.btnHitungRasio);
        btnOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                final String jsonText = "{" +
                        "\"userPatient\" : {" +
                        "\"id\" : 6 // id patient" +
                        "}," +
                        "\"transactionTypeId\" :{" +
                        "\"id\" : 2  // type of transaction 1 for homecare and 2 for  laboratory" +
                        "}," +
                        "\"transactionStatusId\" : {" +
                        "\"id\" : 2 // On Process = 2, On Validation = 1, Failed = 3, Cancelled = 4, 5 = Expire, 6 = Finish" +
                        "}," +
                        "\"paymentFixedPriceStatusId\":{" +
                        "\"id\" : 2 // Unpaid = 2, Paid = 1" +
                        "}," +
                        "\"idClinic\" : {" +
                        "\"id\" : 1 // id Clinic, clinic that selected by patient." +
                        "}," +
                        "\"serviceList\" : [{" +
                        "\"id\" : 1t// id of services that selected by patient can be more than 0ne" +
                        "},{" +
                        "\"id\" : 2// id of services that selected by patient can be more than 0ne" +
                        "}" +
                        "]," +
                        "\"addressToVisit\" : \"Jalan Kangkung 21 Bogor\"" +
                        "\"date\" : "+System.currentTimeMillis()+" // date visit to home , this timestamp format in millisecond" +
                        "}";
//                db = new V2DatabaseHandler(Order.this);
//                db.addData(new V2SaveUtil(getDateTime(),
//                        "Sutadi Triputra",
//                        harga,
//                        "24543"
//                        ));
//
//                Toast.makeText(getApplicationContext(), "Data Disimpan", Toast.LENGTH_LONG).show();

                //Masukkan semua layanan yg dipilih ke dalam object json
//                for(int y =0; y<arrayLayanan.size(); y++){
//                    riArray.put(arrayLayanan.get(y).getJSONObject());
////					Toast.makeText(RasioItem.this, riArray.toString(), Toast.LENGTH_LONG).show();
//                }

                Call<PostPutDelOrder> postOrderCall = mApiInterface.postOrder("6","1");
                postOrderCall.enqueue(new Callback<PostPutDelOrder>() {
                    @Override
                    public void onResponse(Call<PostPutDelOrder> call, retrofit2.Response<PostPutDelOrder> response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelOrder> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Penambahan Order Gagal => "+t.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                });

                            }
        });
    }


    private void postReq(){
        final String urlOrder = "http://167.205.7.227:9028/api/transaction" ;

        //neededd parameter
//        userPatient.id;
//        transactionTypeId.id;
//        transactionStatusId.id =1;
//        paymentFixedPriceStatusId.id=2;
//        idClinic.id = 1;
//        serviceList[]= {id=1};

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, urlOrder,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        // response
//                        Log.e("Response", response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e("Error.Response", "error response =>"+urlOrder+" ==> "+error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("bodyjson", "");

                return params;
            }
        };
        mRequestQueue.add(postRequest);
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


    private void getKlinik() {

        Log.e("Klinik Request", token);

        StringRequest request = new StringRequest(Request.Method.GET, urlKlinik, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    Log.e("Get Clinic", response);
                } else {
                    Log.e("Get Clinic", "Data Null");
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("token", token);
                return params;
            }


        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}


