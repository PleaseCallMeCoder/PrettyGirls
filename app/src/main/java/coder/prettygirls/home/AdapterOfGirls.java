package coder.prettygirls.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import coder.prettygirls.R;
import coder.prettygirls.data.bean.GirlsBean;

public class AdapterOfGirls extends RecyclerView.Adapter<AdapterOfGirls.ViewHolder> {

    private List<GirlsBean.ResultsEntity> mDataset;
    private Context mContext;

    private OnItemClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView girlImg;

        public ViewHolder(View v) {
            super(v);
            girlImg = (ImageView) v.findViewById(R.id.girl_img);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterOfGirls(Context context, List<GirlsBean.ResultsEntity> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterOfGirls.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(mContext).load(mDataset.get(position).getUrl()).into(holder.girlImg);

        if (listener != null) {
            holder.girlImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}