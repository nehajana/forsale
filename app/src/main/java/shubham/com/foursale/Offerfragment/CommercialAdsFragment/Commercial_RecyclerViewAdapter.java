package shubham.com.foursale.Offerfragment.CommercialAdsFragment;

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

import shubham.com.foursale.Offerfragment.ApiModel.OfferDataModel;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.Constants;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class Commercial_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<OfferDataModel> modelList;

    private OnItemClickListener mItemClickListener;


    public Commercial_RecyclerViewAdapter(Context context, ArrayList<OfferDataModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<OfferDataModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_comercial_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final OfferDataModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

          // genericViewHolder.itemTxtTitle.setText(model.getTitle());
           // genericViewHolder.itemTxtMessage.setText(model.getMessage());

            String imageurl= model.getImage().toString();

            Picasso.with(mContext).load(Constants.IMAGE_URL+imageurl)
                    .placeholder(R.drawable.ad1)
                    .into(genericViewHolder.img_offer);

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private OfferDataModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, OfferDataModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_offer;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;


        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

          this.img_offer = (ImageView) itemView.findViewById(R.id.img_offer);
          //this.itemTxtTitle = (TextView) itemView.findViewById(R.id.item_txt_title);
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

