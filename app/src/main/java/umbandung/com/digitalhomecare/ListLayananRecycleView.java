package umbandung.com.digitalhomecare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import umbandung.com.digitalhomecare.Model.transaksi.ServiceList;


public class ListLayananRecycleView extends RecyclerView.Adapter<ListLayananRecycleView.ViewHolder>{

    private static String[] nameOfService, price;
    MySharedPrefernce mySharedPrefernce;

    public ListLayananRecycleView(List<ServiceList> serviceList) {
        mySharedPrefernce = new MySharedPrefernce();
        this.nameOfService = new String[serviceList.size()];
        this.price = new String[serviceList.size()];
        for(int i = 0; i < serviceList.size(); i++) {
            this.nameOfService[i] = serviceList.get(i).getServices().getNameOfservices();
            this.price[i] = String.format("Rp. %,.0f", serviceList.get(i).getServices().getPrice());
        }
    }

    @Override
    public ListLayananRecycleView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layanan_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListLayananRecycleView.ViewHolder holder, int position) {
        holder.nameTv.setText(this.nameOfService[position]);
        holder.priceTv.setText(this.price[position]);
    }

    @Override
    public int getItemCount() {
        return price.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv, priceTv;
        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.service_name);
            priceTv = itemView.findViewById(R.id.service_price);
        }
    }
}
