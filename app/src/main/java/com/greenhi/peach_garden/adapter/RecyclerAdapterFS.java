package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataFS;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterFS extends RecyclerView.Adapter<RecyclerAdapterFS.ViewHolder> {

    private List<ItemDataFS> fsList;

    public RecyclerAdapterFS(List<ItemDataFS> fsList) {
        this.fsList = fsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_fensi,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemDataFS data = fsList.get(position);
        holder.username.setText(data.getUsername());
        holder.intro.setText(data.getIntro());
        holder.head.setImageResource(data.getHeadID());

    }

    @Override
    public int getItemCount() {
        return fsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username,intro;
        CircleImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.tv_fs_name);
            this.intro = itemView.findViewById(R.id.tv_fs_intro);
            this.head = itemView.findViewById(R.id.iv_fs_head);
        }
    }
}
