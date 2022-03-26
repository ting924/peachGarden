package com.greenhi.peach_garden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemComment;
import com.ms.square.android.expandabletextview.ExpandableTextView;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterComment extends RecyclerView.Adapter<RecyclerAdapterComment.ViewHolder>{
    private List<ItemComment> commentList;
    public RecyclerAdapterComment(List<ItemComment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemComment data = commentList.get(position);
        holder.username.setText(data.getUserName());
        holder.text.setText(data.getCommentContent());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }


     public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username,text;
        private CircleImageView profile;

        public ViewHolder( View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.comment_name);
            text = itemView.findViewById(R.id.comment_content);

        }
    }

    public void add(int position, ItemComment data) {
        commentList.add(position, data);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, 1);
    }



}
