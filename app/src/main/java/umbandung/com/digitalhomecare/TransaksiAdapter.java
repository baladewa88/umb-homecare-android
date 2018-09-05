package umbandung.com.digitalhomecare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arkhan on 8/23/2018.
 */

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.MyViewHolder> {

    private List<TransaksiUtil> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public TransaksiAdapter(List<TransaksiUtil> moviesList) {
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
        TransaksiUtil movie = moviesList.get(position);
        holder.title.setText(movie.getNama());
        holder.genre.setText(movie.getRm());
        holder.year.setText(movie.getTanggal());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}