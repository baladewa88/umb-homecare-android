package umbandung.com.digitalhomecare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class Profil extends AppCompatActivity {


    TextView tvNama, tvAlamat, tvEmail, tvPhone, tvSex, tvDob, tvRole;
    String tvToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        tvNama = (TextView)findViewById(R.id.profil_nama);
        tvAlamat = (TextView)findViewById(R.id.profil_address);
        tvEmail = (TextView)findViewById(R.id.profil_email);
        tvPhone = (TextView)findViewById(R.id.profil_phone);
        tvSex = (TextView)findViewById(R.id.profil_sex);
//        tvRole = (TextView)findViewById(R.id.profil_role);
//        tvDob = (TextView)findViewById(R.id.profil_dob);

        MySharedPrefernce mSettings = new MySharedPrefernce();
        String[] datas = mSettings.getValue(Profil.this);

        tvNama.setText(datas[0]);
        tvAlamat.setText(datas[1]);
        tvEmail.setText(datas[2]);
        tvPhone.setText(datas[3]);
        tvSex.setText(datas[4]);
//        tvRole.setText(datas[5]);
//        tvDob.setText(datas[6]);
        tvToken = datas[7];

    }
}
