package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataGZ;
import com.greenhi.peach_garden.item.ItemUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterGZ extends RecyclerView.Adapter<RecyclerAdapterGZ.ViewHolder> {

    private List<ItemUser> gzList;

    public RecyclerAdapterGZ(List<ItemUser> gzList) {
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

        ItemUser data = gzList.get(position);
        holder.username.setText(data.getUserName());
        holder.university.setText(data.getOccupation());
        holder.intro.setText(data.getBriefIntroduction());
        holder.head.setImageResource(R.drawable.default_circle_head);

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
