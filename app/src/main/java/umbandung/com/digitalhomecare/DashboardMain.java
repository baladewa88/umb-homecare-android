package umbandung.com.digitalhomecare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class DashboardMain extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);

        MySharedPrefernce mSettings = new MySharedPrefernce();
        String[] datas = mSettings.getValue(DashboardMain.this);
        for(int i=0; i<datas.length; i++){
            Log.e("DATAS "+i+" => ",datas[i]);
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);

        GridAdapter adapterViewAndroid = new GridAdapter(DashboardMain.this, gridViewString, mThumbIds);
        gridview.setAdapter(adapterViewAndroid);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DashboardMain.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                if(position==0){ //Halaman Profil
                    Intent iProfil = new Intent(DashboardMain.this, Profil.class);
                    startActivity(iProfil);
                }else if(position==1){ //Halaman ECG
                    Intent iECG = new Intent(DashboardMain.this, ECG.class);
                    startActivity(iECG);
                }else if(position==2){ //Halaman Order
                    Intent iOrder = new Intent(DashboardMain.this, Order.class);
                    startActivity(iOrder);
                }else if(position==3){ //Halaman Transaksi
                    Intent iTransaksi = new Intent(DashboardMain.this, Transaksi.class);
                    startActivity(iTransaksi);
                }
            }
        });
    }

    // references to our images
    private int[] mThumbIds = {
            R.mipmap.homecare_profil,
            R.mipmap.homecare_ecg,
            R.mipmap.homecare_order,
            R.mipmap.homecare_transaksi
    };

    String[] gridViewString = {

            "PROFIL", "ECG", "ORDER", "HISTORY"
    };

}
