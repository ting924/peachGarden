package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataGZ;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterGZ extends RecyclerView.Adapter<RecyclerAdapterGZ.ViewHolder> {

    private List<ItemDataGZ> gzList;

    public RecyclerAdapterGZ(List<ItemDataGZ> gzList) {
        this.gzList = gzList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_guanzhu,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemDataGZ data = gzList.get(position);
        holder.username.setText(data.getUsername());
        holder.university.setText(data.getUniversity());
        holder.intro.setText(data.getIntro());
        holder.head.setImageResource(data.getHeadID());

    }

    @Override
    public int getItemCount() {
        return gzList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username,university,intro;
        CircleImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.tv_gz_name);
            this.university = itemView.findViewById(R.id.tv_gz_un);
            this.intro = itemView.findViewById(R.id.tv_gz_intro);
            this.head = itemView.findViewById(R.id.iv_gz_head);
        }
    }
}
