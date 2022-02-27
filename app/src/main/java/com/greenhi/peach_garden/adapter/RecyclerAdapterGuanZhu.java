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

public class RecyclerAdapterGuanZhu extends RecyclerView.Adapter<RecyclerAdapterGuanZhu.ViewHolder>{
    private List<ItemDataSZ> gzList;

    public RecyclerAdapterGuanZhu(List<ItemDataSZ> gzList) {
        this.gzList = gzList;
    }

    @NonNull
    @Override
    public RecyclerAdapterGuanZhu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shizhai_guanzhu,parent,false);
        RecyclerAdapterGuanZhu.ViewHolder viewHolder = new RecyclerAdapterGuanZhu.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterGuanZhu.ViewHolder holder, int position) {
        ItemDataSZ data = gzList.get(position);
        holder.comments.setText(""+data.getComments());
        holder.likes.setText(""+data.getLikes());
        holder.text.setText(data.getText());
        holder.time.setText(data.getTime());
        holder.username.setText(data.getUsername());
        holder.head.setImageResource(data.getHeadID());
    }

    @Override
    public int getItemCount() {
        return gzList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, time, likes, comments;
        ExpandableTextView text;
        ImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.tv_sz_gz_time);
            this.username = itemView.findViewById(R.id.tv_sz_gz_name);
            this.head = itemView.findViewById(R.id.iv_sz_gz_head);
            this.text = itemView.findViewById(R.id.tv_sz_gz_expand);
            this.likes = itemView.findViewById(R.id.tv_sz_gz_like);
            this.comments = itemView.findViewById(R.id.tv_sz_gz_comment);
        }

    }
}
