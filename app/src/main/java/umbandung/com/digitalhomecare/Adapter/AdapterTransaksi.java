package umbandung.com.digitalhomecare.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import umbandung.com.digitalhomecare.LayananUtil;
import umbandung.com.digitalhomecare.Model.TransaksiGet;
import umbandung.com.digitalhomecare.R;

/**
 * Created by Arkhan on 7/25/2018.
 */

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.MyViewHolder>  {

    private List<TransaksiGet> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tanggal, harga, orderNumber;

        public MyViewHolder(View view) {
            super(view);
            tanggal = (TextView) view.findViewById(R.id.genre);
            orderNumber = (TextView) view.findViewById(R.id.title);
            harga = (TextView) view.findViewById(R.id.year);
        }
    }


    public AdapterTransaksi(List<TransaksiGet> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_transaksi, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TransaksiGet movie = moviesList.get(position);
        holder.tanggal.setText(movie.getTanggalTransaksi());
        holder.orderNumber.setText(movie.getOrderNumber());
        holder.harga.setText(movie.getHarga());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
