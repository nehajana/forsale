package shubham.com.foursale.HomeFragment.featuring;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.support.v7.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import shubham.com.foursale.HomeFragment.ApiModel.Featured;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.Constants;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class FeaturingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Featured> modelList;

    private OnItemClickListener mItemClickListener;


    public FeaturingRecyclerViewAdapter(Context context, ArrayList<Featured> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Featured> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final Featured model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

        //  genericViewHolder.img_services.setImageResource(model.getImg_services());

            String Image_URl= Constants.IMAGE_URL+model.getImage().toString();

            Picasso.with(mContext).load(Image_URl)
                    .placeholder(R.drawable.ad1)
                    .into(genericViewHolder.img_services);

             genericViewHolder.txt_services_name.setText(model.getName());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private Featured getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Featured model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView img_services;
        private TextView txt_services_name;

        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.img_services = (CircleImageView) itemView.findViewById(R.id.img_services);
            this.txt_services_name = (TextView) itemView.findViewById(R.id.txt_services_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });

        }
    }

}

