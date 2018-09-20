package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Arkhan on 8/19/2018.
 */

public class DashboardAdmin extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.dashboard_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        GridAdapter adapterViewAndroid = new GridAdapter(DashboardAdmin.this, gridViewString, mThumbIds);
        gridview.setAdapter(adapterViewAndroid);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DashboardAdmin.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                if(position==0){ //Halaman Profil
                    Intent iProfil = new Intent(DashboardAdmin.this, Profil.class);
                    startActivity(iProfil);
                }else if(position==1){ //Halaman ECG
                    Intent iECG = new Intent(DashboardAdmin.this, ECG.class);
                    startActivity(iECG);
                }else if(position==2){ //Halaman Order
                    Intent iOrder = new Intent(DashboardAdmin.this, Order.class);
                    startActivity(iOrder);
                }else if(position==3){ //Halaman OrderHistory
                    Intent iTransaksi = new Intent(DashboardAdmin.this, OrderHistory.class);
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
