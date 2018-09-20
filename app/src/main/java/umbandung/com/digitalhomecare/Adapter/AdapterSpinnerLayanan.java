package umbandung.com.digitalhomecare.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import umbandung.com.digitalhomecare.KlinikUtil;
import umbandung.com.digitalhomecare.LayananUtil;
import umbandung.com.digitalhomecare.R;

/**
 * Created by Arkhan on 9/10/2018.
 */

public class AdapterSpinnerLayanan extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<LayananUtil> items;
    private final int mResource;

    public AdapterSpinnerLayanan(@NonNull Context context, @LayoutRes int resource,
                         @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.layanan_nama);
        TextView harga = (TextView) view.findViewById(R.id.layanan_harga);

        LayananUtil offerData = items.get(position);

        offTypeTv.setText(offerData.getNameOfservices());
        harga.setText(offerData.getPrice());

        return view;
    }
}