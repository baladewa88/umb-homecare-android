package umbandung.com.digitalhomecare.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import umbandung.com.digitalhomecare.LayananUtil;
import umbandung.com.digitalhomecare.R;

/**
 * Created by Arkhan on 7/25/2018.
 */

public class AdapterLayanan extends RecyclerView.Adapter<AdapterLayanan.MyViewHolder>  {

    private List<LayananUtil> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_layanan, harga, klinik;

        public MyViewHolder(View view) {
            super(view);
            nama_layanan = (TextView) view.findViewById(R.id.nama_layanan);
            klinik = (TextView) view.findViewById(R.id.nama_klinik);
            harga = (TextView) view.findViewById(R.id.harga);
        }
    }


    public AdapterLayanan(List<LayananUtil> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layanan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LayananUtil movie = moviesList.get(position);
        holder.nama_layanan.setText(movie.getNameOfservices());
        holder.klinik.setText(movie.getCodeOfservices());
        holder.harga.setText(movie.getPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
