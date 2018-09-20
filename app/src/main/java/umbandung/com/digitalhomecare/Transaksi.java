package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umbandung.com.digitalhomecare.Adapter.AdapterLayanan;
import umbandung.com.digitalhomecare.Adapter.AdapterOrder;
import umbandung.com.digitalhomecare.Adapter.AdapterTransaksi;
import umbandung.com.digitalhomecare.Model.Content;
import umbandung.com.digitalhomecare.Model.TransaksiGet;
import umbandung.com.digitalhomecare.Model.TransaksiHasil;
import umbandung.com.digitalhomecare.Rest.ApiClient;
import umbandung.com.digitalhomecare.Rest.ApiInterface;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class Transaksi extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRVFishPrice;
    private AdapterOrder mAdapter;
    private List<LayananUtil> list = new ArrayList<LayananUtil>();
    private SearchView mSearchView;
    ArrayList<TransaksiGet> data;
    private boolean berubahQuery = false;
    private String cekHasilKlik = "";
    ArrayList<LayananUtil> filteredModelList;
    private ArrayList<OrderUtil> dataTransaksi = new ArrayList<OrderUtil>();

    MySharedPrefernce mSettings;


    public ApiInterface mApiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi);

        mRVFishPrice = (RecyclerView) findViewById(R.id.fishPriceList);
        mSearchView = (SearchView) findViewById(R.id.searchView1);

        mRVFishPrice.addItemDecoration(new DividerItemDecoration(
                Transaksi.this,
                LinearLayoutManager.VERTICAL));

        mSettings = new MySharedPrefernce();
        String[] datas = mSettings.getValue(Transaksi.this);

//        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

//        Call<TransaksiGet> client = mApiInterface.historyTrx(datas[7], datas[8]);
        Log.e("TOKEN", datas[7]);
        Log.e("ID PASIEN", datas[8]);

        Call<TransaksiHasil> client = mApiInterface.historyTrxNew(datas[7], datas[8]);

        client.enqueue(new Callback<TransaksiHasil>() {
            @Override
            public void onResponse(Call<TransaksiHasil> call, Response<TransaksiHasil> response) {

//                List<TransaksiGet> dataTransaksiGet = response.body();

                Log.e("HASIL JSON mesasage", ""+response.message());
                Log.e("HASIL JSON body", ""+response.body());
                Log.e("HASIL JSON raw", ""+response.raw());
                Log.e("HASIL JSON code", ""+response.code());

                TransaksiHasil a = response.body();
                Log.e("Transaksi NumberID", a.getContent().get(0).getId().toString());


//                try {
//                    JSONObject oo = new JSONObject();
//                    JSONArray aa = oo.getJSONArray("content");
//                    Log.e("HASIL JSON", ""+aa);
//
//                    for (int i=0; i<aa.length(); i++){
//                        JSONObject contentItem = aa.getJSONObject(i);
//                        JSONObject contentClinic = contentItem.getJSONObject("clinic");
//                        JSONObject contentTipe = contentItem.getJSONObject("transactionTypeId");
//                        JSONObject contentStatus = contentItem.getJSONObject("transactionStatusId");
//
//                        OrderUtil data = new OrderUtil();
//                        data.setId(contentItem.getString("id"));
//                        data.setTanggal(contentItem.getString("dateOrderIn"));
//                        data.setHarga(contentItem.getString("fixedPrice"));
//                        data.setKlinik(contentClinic.getString("nameOfClinic"));
//                        data.setTipe(contentTipe.getString("type"));
//                        data.setStatus(contentStatus.getString("status"));
//                        data.setOrderNumber(contentItem.getString("orderNumber"));
//                        dataTransaksi.add(data);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.e("JSON ERROR", e.getMessage());
//                }

                List<Content> hasil = a.getContent();


                Log.e("DATA TRANSAKSI", dataTransaksi.size()+"");
                mAdapter = new AdapterOrder(dataTransaksi);
                mRVFishPrice.setAdapter(mAdapter);
                setupSearchView();
                mRVFishPrice.setLayoutManager(new LinearLayoutManager(
                        Transaksi.this));

                mRVFishPrice.addOnItemTouchListener(new RecyclerTouchListener(
                        getApplicationContext(), mRVFishPrice,
                        new RecyclerTouchListener.ClickListener() {

                            @Override
                            public void onLongClick(View view, int position) {
                                // TODO Auto-generated method stub
                                // Toast.makeText(getApplicationContext(),
                                // "Click Selected", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onClick(View view, int position) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(
                                        getApplicationContext(),
                                        TransaksiDetail.class);
                                Bundle b = new Bundle();
                                if (berubahQuery == false) {
                                    b.putString("id", data.get(position)
                                            .getIdPasien());
                                    cekHasilKlik = data.get(position)
                                            .getIdPasien().toString();

                                } else if (berubahQuery == true) {
                                    b.putString("id", filteredModelList.get(position)
                                            .getId().toString());
                                    cekHasilKlik = filteredModelList.get(position)
                                            .getId().toString();
                                }
                                intent.putExtras(b);
                                Log.e("Transaksi.java",
                                        "ID Pasien => "
                                                + data.get(position).getIdPasien());

                                startActivity(intent);
                            }
                        }));


            }

            @Override
            public void onFailure(Call<TransaksiHasil> call, Throwable t) {
                Log.e("History Trx Failure", t.getMessage().toString());

            }
        });


    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint(Html.fromHtml("<font color = #000000>Cari data</font>"));
        mSearchView.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
