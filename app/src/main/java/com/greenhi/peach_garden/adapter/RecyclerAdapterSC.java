package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataSC;

import java.util.List;

public class RecyclerAdapterSC extends RecyclerView.Adapter<RecyclerAdapterSC.ViewHolder> {

    private List<ItemDataSC> scList;

    public RecyclerAdapterSC(List<ItemDataSC> scList) {
        this.scList = scList;
    }

    @NonNull
    @Override
    public RecyclerAdapterSC.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_shoucang,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterSC.ViewHolder holder, int position) {
        ItemDataSC data = scList.get(position);
        holder.title.setText(data.getTitle());
        holder.username.setText(data.getUsername());
        holder.cover.setImageResource(data.getCoverID());
        holder.head.setImageResource(data.getHeadID());
    }

    @Override
    public int getItemCount() {
        return scList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,username;
        ImageView cover,head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.tv_sc_title);
            this.username=itemView.findViewById(R.id.tv_sc_name);
            this.head=itemView.findViewById(R.id.iv_sc_head);
            this.cover=itemView.findViewById(R.id.iv_sc_cover);
        }

    }
}
