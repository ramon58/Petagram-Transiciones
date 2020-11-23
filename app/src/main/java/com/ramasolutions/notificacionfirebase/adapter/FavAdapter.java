package com.ramasolutions.notificacionfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ramasolutions.notificacionfirebase.R;
import com.ramasolutions.notificacionfirebase.db.Database;
import com.ramasolutions.notificacionfirebase.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<Mascota> favItemsList;
    private Database database;

    public FavAdapter(Context context, List<Mascota> favItemsList) {
        this.context = context;
        this.favItemsList = favItemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_favoritos, parent, false);
        database = new Database(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvFavPetName.setText(favItemsList.get(position).getFavName());
        //holder.ivFavPetPic.setImageResource(favItemsList.get(position).getFavPic());
        Picasso.with(context)
                .load(favItemsList.get(position).getFavPic())
                .resize(100, 100)
                .centerCrop()
                .into(holder.ivFavPetPic);
    }

    @Override
    public int getItemCount() {
        return favItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFavPetPic;
        private TextView tvFavPetName;
        private Button favBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            ivFavPetPic = itemView.findViewById(R.id.ivFavPetPic);
            tvFavPetName = itemView.findViewById(R.id.tvFavPetName);
            favBtn = itemView.findViewById(R.id.favBtn);

            //remove from fav after click
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Mascota favItem = favItemsList.get(position);
                    database.remove_fav(favItem.getKey_id());
                    removeItem(position);
                }
            });
        }

        private void removeItem(int position) {
            favItemsList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favItemsList.size());
        }
    }
}
