package shubham.com.foursale.HomeFragment.featuring;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import shubham.com.foursale.HomeFragment.ApiModel.Featured;
import shubham.com.foursale.HomeFragment.ApiModel.Job;
import shubham.com.foursale.R;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class JobRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Job> modelList;

    private OnItemClickListener mItemClickListener;


    public JobRecyclerViewAdapter(Context context, ArrayList<Job> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Job> modelList) {
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
            final Job model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

        //  genericViewHolder.img_services.setImageResource(model.getImg_services());
       //   genericViewHolder.txt_services_name.setText(model.getServicesName());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private Job getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Job model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_services;
        private TextView txt_services_name;

        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

            this.img_services = (ImageView) itemView.findViewById(R.id.img_services);
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

