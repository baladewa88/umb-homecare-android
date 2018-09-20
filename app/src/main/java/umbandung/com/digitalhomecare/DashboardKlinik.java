package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Arkhan on 8/19/2018.
 */

public class DashboardKlinik extends AppCompatActivity{

    private MySharedPrefernce mySharedPrefernce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_medis);

        GridView gridview = (GridView) findViewById(R.id.gridview);


        mySharedPrefernce = new MySharedPrefernce();
        String[] datas = mySharedPrefernce.getValue(DashboardKlinik.this);
        for(int i=0; i<datas.length; i++){
            Log.e("DATAS "+i+" => ",datas[i]);
        }
        TextView nama = (TextView)findViewById(R.id.welcome_nama);
        nama.setText("Selamat datang, "+datas[0]);

        MedisAdapter adapterViewAndroid = new MedisAdapter(DashboardKlinik.this, gridViewString, mThumbIds);
        gridview.setAdapter(adapterViewAndroid);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DashboardKlinik.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                if(position==0){ //Halaman Profil
                    Intent iProfil = new Intent(DashboardKlinik.this, Profil.class);
                    startActivity(iProfil);
                }else if(position==1){ //Halaman Order
                    Intent iOrder = new Intent(DashboardKlinik.this, OrderMedis.class);
                    startActivity(iOrder);
                }else if(position==2){
                    mySharedPrefernce.clearSharedPreference(DashboardKlinik.this);
                    Intent iTransaksi = new Intent(DashboardKlinik.this, Login.class);
                    startActivity(iTransaksi);
                }
            }
        });
    }

    // references to our images
    private int[] mThumbIds = {
            R.mipmap.homecare_profil,
            R.mipmap.homecare_transaksi,
            R.mipmap.logout
    };

    String[] gridViewString = {

            "PETUGAS MEDIS", "TRANSAKSI", "LOG OUT",
    };
}