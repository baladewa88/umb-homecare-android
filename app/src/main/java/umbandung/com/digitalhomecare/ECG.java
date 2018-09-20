package umbandung.com.digitalhomecare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umbandung.com.digitalhomecare.Model.ContentEcg;
import umbandung.com.digitalhomecare.Model.EcgHasil;
import umbandung.com.digitalhomecare.Rest.ApiClient;
import umbandung.com.digitalhomecare.Rest.ApiInterface;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class ECG extends AppCompatActivity{

    //hubungan sama retrofit
    public ApiInterface mApiInterface;
    public String token, ecgCode;
    GraphView graph;

    String sT, sb, sTh;

    Button submit;
    Spinner spTanggal, spBulan, spTahun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecg);

        //get Login Credential
        MySharedPrefernce mSettings = new MySharedPrefernce();
        final String[] datas = mSettings.getValue(ECG.this);
        token = datas[7];
        ecgCode = datas[9];

        spTanggal = (Spinner)findViewById(R.id.sp_tanggal);
        spBulan = (Spinner)findViewById(R.id.sp_bulan);
        spTahun = (Spinner)findViewById(R.id.sp_tahun);

        mApiInterface = ApiClient.getClient(token).create(ApiInterface.class);

        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScrollable(true);

        ArrayAdapter<CharSequence> adapterTanggal = ArrayAdapter.createFromResource(this,
                R.array.list_tanggal, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTanggal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spTanggal.setAdapter(adapterTanggal);

        ArrayAdapter<CharSequence> adapterBulan = ArrayAdapter.createFromResource(this,
                R.array.list_bulan, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTanggal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spBulan.setAdapter(adapterBulan);

        int thn = Calendar.getInstance().get(Calendar.YEAR);
        int lengthTahun = thn - 2017;
        ArrayList<String> tahun = new ArrayList<>();

        for(int t=0; t<lengthTahun; t++){
            tahun.add(String.valueOf(2017+t+1));
        }

        ArrayAdapter<String> adapterTahun = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        tahun); //selected item will look like a spinner set from XML
        adapterTahun.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spTahun.setAdapter(adapterTahun);

        spTanggal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sT = adapterView.getItemAtPosition(i).toString();
                Log.e("PILIH TANGGAL", sT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sT = "1";
                Log.e("PILIH TANGGAL", sT);

            }
        });

        spBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sb = adapterView.getItemAtPosition(i).toString();
                Log.e("PILIH BULAN", sb);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sb = "1";
                Log.e("PILIH BULAN", sb);

            }
        });

        spTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sTh = adapterView.getItemAtPosition(i).toString();
                Log.e("PILIH TAHUN", sTh);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sTh = "2018";
                Log.e("PILIH TAHUN", sTh);

            }
        });



        submit = (Button)findViewById(R.id.submit_ecg);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ECG CODE", ecgCode);
                Log.e("ECG TANGGAL", sTh+"-"+sb+"-"+sT);
//        DataPoint dp = new DataPoint(0,0);
//       getEcg();
                tampilGrafis(ecgCode, sTh+"-"+sb+"-"+sT);
            }
        });


    }

    public void getEcg(){

        Date date = Calendar.getInstance().getTime();

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(date);

        Log.e("getECG", today);
        Log.e("TOKEN", token);

        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
//                String day = "Day = " + simpleDatePicker.getDayOfMonth();
//                String month = "Month = " + (simpleDatePicker.getMonth() + 1);
//                String year = "Year = " + simpleDatePicker.getYear();
                // display the values by using a toast
//                Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();
            }
        });


    }

    public void tampilGrafis(String kode, String date){
        Call<EcgHasil> layananEcg = mApiInterface.getEcgData(kode, date);
        layananEcg.enqueue(new Callback<EcgHasil>() {
            @Override
            public void onResponse(Call<EcgHasil> call, Response<EcgHasil> response) {

                if(response.body().getContent().size()==0){
                    Toast.makeText(getApplicationContext(), "Tidak terdapat data EKG pada hari tersebut", Toast.LENGTH_LONG);
                }else{
                    Log.e("ECG", response.code()+"");
                    Log.e("ECG", response.message()+"");
                    Log.e("ECG", response.body()+"");
                    EcgHasil ecg = response.body();

                    DataPoint[] dp = new DataPoint[200];
                    double vX, vY;

                    Log.e("ECG SIZE", ""+ecg.getContent().size());
                    for(int e=0; e<ecg.getContent().size(); e++){
                        ContentEcg kontenEcg = new ContentEcg();
                        kontenEcg.setId(ecg.getContent().get(e).getId());
                        kontenEcg.setAnalog(ecg.getContent().get(e).getAnalog());
                        kontenEcg.setEcgDate(ecg.getContent().get(e).getEcgDate());
                        kontenEcg.setEcgCode(ecg.getContent().get(e).getEcgCode());
                        kontenEcg.setOrderAnalog(ecg.getContent().get(e).getOrderAnalog());

                        vX = e;
                        vY = ecg.getContent().get(e).getAnalog();

                        Log.e("Loop Ecg X => ", vX+"");
                        Log.e("Loop Ecg Y => ", vY+"");

                        dp[e] = new DataPoint(vX, vY);


                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dp);
                    graph.addSeries(series);

                }


            }

            @Override
            public void onFailure(Call<EcgHasil> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Terdapat masalah koneksi. Harap periksa kembali koneksi internet anda", Toast.LENGTH_LONG).show();

            }
        });
    }
}
