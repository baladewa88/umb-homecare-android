package umbandung.com.digitalhomecare;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by github/fiyyanputra on 9/12/2018.
 */

public class TransaksiRecyclerView extends RecyclerView.Adapter<TransaksiRecyclerView.ViewHolder> {
    public static String[] orderNumber, dateOrder, patientsName, statuss;

    public TransaksiRecyclerView(List<String> pOrderNumbers,
                                 List<String> pDateOrders,
                                 List<String> pPatientsName,
                                 List<String> pStatuss) {

        orderNumber = new String[pOrderNumbers.size()];
        dateOrder = new String[pDateOrders.size()];
        patientsName = new String[pPatientsName.size()];
        statuss = new String[pStatuss.size()];

        orderNumber = pOrderNumbers.toArray(orderNumber);
        dateOrder = pDateOrders.toArray(dateOrder);
        patientsName = pPatientsName.toArray(patientsName);
        statuss = pStatuss.toArray(statuss);
    }

    @Override
    public TransaksiRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaksi_content, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiRecyclerView.ViewHolder holder, int position) {
        holder.tvNomorOrder.setText(orderNumber[position]);
        holder.tvTanggalOrder.setText(dateFormatting(dateOrder[position]));
        holder.tvNamaPasien.setText(patientsName[position]);
        holder.tvStatus.setText(statuss[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailTransaksi.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private String dateFormatting(String s) {
        String date = null;
        try {
            String pattern = "dd MMM yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(pattern, new Locale("id", "ID"));
            date = simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("dateFormatting: ", date);
        return date;
    }

    @Override
    public int getItemCount() {
        return orderNumber.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNomorOrder, tvTanggalOrder, tvNamaPasien, tvStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNomorOrder = (TextView) itemView.findViewById(R.id.id_transaksi);
            tvTanggalOrder = (TextView) itemView.findViewById(R.id.tanggal_transaksi);
            tvNamaPasien = (TextView) itemView.findViewById(R.id.nama_pasien);
            tvStatus = (TextView) itemView.findViewById(R.id.status_transaksi);
        }
    }

}
