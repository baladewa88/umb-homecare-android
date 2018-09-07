package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class OrderMedis extends AppCompatActivity {
    private List<TransaksiUtil> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private V2SaveRecyclerAdapter mAdapter;

    V2DatabaseHandler db;
    ArrayList<V2SaveUtil> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pasien_medis);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        db = new V2DatabaseHandler(this);

        contacts = db.getAllContacts();

        Log.e("Contact", contacts.get(0).getNama());
        recyclerView = (RecyclerView) findViewById(R.id.list_order_pasien);

        mAdapter = new V2SaveRecyclerAdapter(contacts, OrderMedis.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                getApplicationContext(), recyclerView,
                new RecyclerTouchListener.ClickListener() {

                    @Override
                    public void onClick(View view, int position) {
                        Intent iMedis = new Intent(OrderMedis.this, OrderPasien.class);
                        startActivity(iMedis);
                    }

                    @Override
                    public void onLongClick(View view, final int position) {

                    }

                }));

    }

    private void prepareMovieData() {

        String[] a = new String[1];
        TransaksiUtil movie = new TransaksiUtil("Sutadi Triputra", "009865", "18 Agustus 2018", "Sutadi Triputra", "009865", "","18 Agustus 2018", a);
        movieList.add(movie);

        movie = new TransaksiUtil("Sutadi Triputra", "009865", "18 Agustus 2018", "Sutadi Triputra", "009865", "","18 Agustus 2018", a);
        movieList.add(movie);

        movie = new TransaksiUtil("Sutadi Triputra", "009865", "18 Agustus 2018", "Sutadi Triputra", "009865", "","18 Agustus 2018", a);
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}