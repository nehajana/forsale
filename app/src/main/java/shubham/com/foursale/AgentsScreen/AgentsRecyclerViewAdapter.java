package shubham.com.foursale.AgentsScreen;

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
import shubham.com.foursale.AgentsScreen.Apimodel.AgentsData_model;
import shubham.com.foursale.R;
import shubham.com.foursale.Volley.Constants;

import static shubham.com.foursale.Volley.Constants.IMAGE_URL;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class AgentsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<AgentsData_model> modelList;

    private OnItemClickListener mItemClickListener;


    public AgentsRecyclerViewAdapter(Context context, ArrayList<AgentsData_model> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<AgentsData_model> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_agents, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {

            final AgentsData_model model = getItem(position);

            ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_name.setText(model.getName());


            String Img_Url=model.getImage().toString();

            Picasso.with(mContext).load(Constants.IMAGE_URL+Img_Url)
                    .placeholder(R.drawable.ad1)
                    .into(genericViewHolder.profile_image);

          // genericViewHolder.itemTxtMessage.setText(model.getMessage());

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private AgentsData_model getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, AgentsData_model model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_image;
        private TextView txt_name;
        private TextView itemTxtMessage;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.profile_image = (CircleImageView) itemView.findViewById(R.id.profile_image);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }

}

