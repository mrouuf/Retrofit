package com.example.retrofit.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.DetailActivity;
import com.example.retrofit.R;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.PlayerViewHolder> {
    private Context context;
    private List<PlayerData> playerDataList;

    public CardViewAdapter(Context context, List<PlayerData> playerDataList) {
        this.context = context;
        this.playerDataList = playerDataList;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(context).inflate(R.layout.item_cardview, parent, false);
        return new PlayerViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        final PlayerData data = playerDataList.get(position);

        Glide.with(context)
                .load(data.getImgURL())
                .into(holder.imgPhoto);

        holder.playerName.setText(data.getName());
        holder.playerId.setText(data.getId());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailInten = new Intent(context, DetailActivity.class);
                detailInten.putExtra(DetailActivity.EXTRA_DETAIL, data);
                context.startActivities(new Intent[]{detailInten});
            }
        });

    }

    @Override
    public int getItemCount() {
        return playerDataList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerId, playerName;
        ImageView imgPhoto;
        CardView cardViewPlayer;
        Button btnDetail;
        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewPlayer = itemView.findViewById(R.id.cardViewId);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            playerId = itemView.findViewById(R.id.itemId);
            playerName = itemView.findViewById(R.id.itemName);
            btnDetail = itemView.findViewById(R.id.bntDetail);

        }
    }
}
