package shubham.com.foursale.DetailProductActivity;

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

import shubham.com.foursale.DetailProductActivity.ApiModel.Details_DataModel;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.Constants;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Details_DataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context context, ArrayList<Details_DataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Details_DataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_details_product, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder){

            final Details_DataModel model = getItem(position);

            ViewHolder genericViewHolder = (ViewHolder) holder;

           /* genericViewHolder.txt_services_name.setText(model.getTitle());
            genericViewHolder.profile_image.setBackgroundResource(model.getServicesName());*/

           String imageurl= model.getImage().toString();

            Picasso.with(mContext).load(Constants.IMAGE_URL+imageurl)
                    .placeholder(R.drawable.ad1)
                    .into(genericViewHolder.profile_image);


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

    private Details_DataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Details_DataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_image;
        private TextView txt_services_name;
        private TextView itemTxtMessage;


        public ViewHolder(final View itemView) {
            super(itemView);


            this.profile_image = (ImageView) itemView.findViewById(R.id.profile_image);
            this.txt_services_name = (TextView) itemView.findViewById(R.id.txt_services_name);
           // this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });

        }
    }

}

