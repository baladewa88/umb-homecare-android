package umbandung.com.digitalhomecare.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import umbandung.com.digitalhomecare.Model.TransaksiGet;
import umbandung.com.digitalhomecare.OrderUtil;
import umbandung.com.digitalhomecare.R;

/**
 * Created by Arkhan on 9/8/2018.
 */

public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHolder>  {

private List<OrderUtil> moviesList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView id, tanggal, harga, klinik, tipe, status, orderNumber;

    public MyViewHolder(View view) {
        super(view);
        tanggal = (TextView) view.findViewById(R.id.order_genre);
        orderNumber = (TextView) view.findViewById(R.id.order_title);
        harga = (TextView) view.findViewById(R.id.order_year);
    }
}


    public AdapterOrder(List<OrderUtil> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public AdapterOrder.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order, parent, false);

        return new AdapterOrder.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterOrder.MyViewHolder holder, int position) {
        OrderUtil movie = moviesList.get(position);
        holder.tanggal.setText(movie.getTanggal());
        holder.orderNumber.setText(movie.getOrderNumber());
        holder.harga.setText(movie.getHarga());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
