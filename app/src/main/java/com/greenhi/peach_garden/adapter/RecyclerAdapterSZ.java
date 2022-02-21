package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

public class RecyclerAdapterSZ extends RecyclerView.Adapter<RecyclerAdapterSZ.ViewHolder> {

    private List<ItemDataSZ> szList;

    public RecyclerAdapterSZ(List<ItemDataSZ> szList) {
        this.szList = szList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_shizuo,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemDataSZ data = szList.get(position);
        holder.comments.setText(""+data.getComments());
        holder.likes.setText(""+data.getLikes());
        holder.text.setText(data.getText());
        holder.time.setText(data.getTime());
        holder.username.setText(data.getUsername());
        holder.head.setImageResource(data.getHeadID());
    }

    @Override
    public int getItemCount() {
        return szList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, time, likes, comments;
        ExpandableTextView text;
        ImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.tv_sz_time);
            this.username = itemView.findViewById(R.id.tv_sz_name);
            this.head = itemView.findViewById(R.id.iv_sz_head);
            this.text = itemView.findViewById(R.id.tv_sz_expand);
            this.likes = itemView.findViewById(R.id.tv_sz_like);
            this.comments = itemView.findViewById(R.id.tv_sz_comment);
        }

    }
}
