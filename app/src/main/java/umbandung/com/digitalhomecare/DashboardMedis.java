package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Arkhan on 8/19/2018.
 */

public class DashboardMedis extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_medis);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        MedisAdapter adapterViewAndroid = new MedisAdapter(DashboardMedis.this, gridViewString, mThumbIds);
        gridview.setAdapter(adapterViewAndroid);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DashboardMedis.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                if(position==0){ //Halaman Profil
                    Intent iProfil = new Intent(DashboardMedis.this, Profil.class);
                    startActivity(iProfil);
                }else if(position==1){ //Halaman Order
                    Intent iOrder = new Intent(DashboardMedis.this, OrderMedis.class);
                    startActivity(iOrder);
                }else if(position==2){ //Halaman Transaksi
                    Intent iTransaksi = new Intent(DashboardMedis.this, Transaksi.class);
                    startActivity(iTransaksi);
                }
            }
        });
    }

    // references to our images
    private int[] mThumbIds = {
            R.mipmap.homecare_profil,
            R.mipmap.homecare_order,
            R.mipmap.homecare_transaksi,
            R.mipmap.logout
    };

    String[] gridViewString = {

            "PROFIL", "ORDER", "HISTORY", "LOG OUT"
    };
}
