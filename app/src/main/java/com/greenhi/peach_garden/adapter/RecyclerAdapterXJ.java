package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataXJ;

import java.util.List;

public class RecyclerAdapterXJ extends RecyclerView.Adapter<RecyclerAdapterXJ.ViewHolder> {

    private List<ItemDataXJ> xjList;

    public RecyclerAdapterXJ(List<ItemDataXJ> xjList) {
        this.xjList = xjList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_xinjian,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemDataXJ data = xjList.get(position);
        holder.head.setImageResource(data.getHeadID());
        holder.time.setText(data.getTime());
        holder.username.setText(data.getName());
        holder.content.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return xjList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username,time,content;
        ImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.tv_xj_time);
            this.username = itemView.findViewById(R.id.tv_xj_name);
            this.content = itemView.findViewById(R.id.tv_xj_message);
            this.head = itemView.findViewById(R.id.iv_xj_head);
        }

    }
}
