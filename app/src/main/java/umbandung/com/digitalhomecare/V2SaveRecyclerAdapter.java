package umbandung.com.digitalhomecare;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class V2SaveRecyclerAdapter extends
		RecyclerView.Adapter<V2SaveRecyclerAdapter.ViewHolder> {

	private ArrayList<V2SaveUtil> mDataset;
	RecyclerView.LayoutManager mLayoutManager, mLayoutManagerr;
	Context context;
    ViewHolder viewHolderPool[];
	
    // Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		public TextView txtGolBtp;
		public TextView txtIdBtp;
		public TextView txtKategori;
//		public TextView txtNoKatPan;
//		public TextView txtKatPan;
//		public RecyclerView recJenis, recPangan;

		public ViewHolder(View v) {
			super(v);
			txtGolBtp = (TextView) v.findViewById(R.id.subnama);
			txtIdBtp = (TextView) v.findViewById(R.id.nama);
			txtKategori = (TextView) v.findViewById(R.id.subnama2);
//			txtNoKatPan = (TextView) v.findViewById(R.id.rec_katpang);
//			txtKatPan = (TextView) v.findViewById(R.id.rec_jenispangan);
//			recJenis = (RecyclerView) v.findViewById(R.id.recycler_jenis);
//			recPangan = (RecyclerView) v.findViewById(R.id.recycler_pangan);
		}
	}

	public void add(int position, V2SaveUtil item) {
		mDataset.add(position, item);
		notifyItemInserted(position);
	}

	public void remove(String item) {
		int position = mDataset.indexOf(item);
		mDataset.remove(position);
		notifyItemRemoved(position);
	}

	// Provide a suitable constructor (depends on the kind of dataset)
	public V2SaveRecyclerAdapter(ArrayList<V2SaveUtil> myDataset, Context ctx) {
		mDataset = myDataset;
		this.context = ctx;
		viewHolderPool = new ViewHolder[mDataset.size()];
	}

	// Create new views (invoked by the layout manager)
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.v2_row_save_mainsearch, parent, false);
		// set the view's size, margins, paddings and layout parameters
		ViewHolder vh = new ViewHolder(v);
//		mLayoutManager = new LinearLayoutManager(context);
//		vh.recJenis.setLayoutManager(mLayoutManager);
//
//		mLayoutManagerr = new LinearLayoutManager(context);
//		vh.recPangan.setLayoutManager(mLayoutManagerr);
		//vh.recPangan.setLayoutManager(mLayoutManager);

		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		V2SaveUtil name = mDataset.get(position);
		holder.txtGolBtp.setText(Html.fromHtml(name.getNama()));
//		holder.txtJenisBTP.setText("Sub Golongan : " + name.getSubgol());
//		holder.txtNoKatPan.setText("Induk Jenis Golongan : " + name.getIndex());
		holder.txtIdBtp.setText(name.getRm());
		holder.txtKategori.setText(name.getHarga());
//		Log.e("MyRecyclerAdapter", ""+new SearchClass().dataJenis);
//		holder.recJenis.setAdapter(new JenisBTPRecyclerAdapter(new SearchClass().dataJenis, context));
//		holder.recPangan.setAdapter(new KatpanRecyclerAdapter(new SearchClass().dataPangan, context));

	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.size();
	}

}
