package com.greenhi.peach_garden.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.greenhi.peach_garden.item.ItemDynamic;
import com.greenhi.peach_garden.utils.JsonParse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RecyclerAdapterGuanZhu extends RecyclerView.Adapter<RecyclerAdapterGuanZhu.ViewHolder> {

    private List<ItemDynamic> gzList;
    public RecyclerAdapterGuanZhu(List<ItemDynamic> gzList) {
        this.gzList = gzList;
    }

    @NonNull
    @Override
    public RecyclerAdapterGuanZhu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shizhai_guanzhu, parent, false);
        RecyclerAdapterGuanZhu.ViewHolder viewHolder = new RecyclerAdapterGuanZhu.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterGuanZhu.ViewHolder holder, int position) {
        ItemDynamic data = gzList.get(position);
        holder.comments.setText("" + data.getCommentNumber());
        holder.likes.setText("" + data.getLoveNumber());
        holder.text.setText(data.getDynamicContent());
        if(data.getCreateTime()!=null){
            holder.time.setText(data.getCreateTime().substring(0,10)+" "+data.getCreateTime().substring(11,19));
        }
        holder.username.setText(data.getUserName());
        holder.head.setImageResource(R.drawable.default_circle_head);

        holder.btnPinlun.setTag(data.getUid());

        holder.btLike.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                int position= holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/dynamic/addLikeById?id="+gzList.get(position).getId()+"&add="+checked;
                AsyncHttpClient client=new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try{
                            if(checked){
                                gzList.get(position).setLoveNumber(gzList.get(position).getLoveNumber()+1);
                            }else {
                                gzList.get(position).setLoveNumber(gzList.get(position).getLoveNumber()-1);
                            }
                            notifyItemChanged(position);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("点赞+1","失败");
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return gzList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username, time, likes, comments;
        private ExpandableTextView text;
        private ImageView head, btnPinlun;
        private ShineButton btLike;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_sz_gz_time);
            username = itemView.findViewById(R.id.tv_sz_gz_name);
            head = itemView.findViewById(R.id.iv_sz_gz_head);
            text = itemView.findViewById(R.id.tv_sz_gz_expand);
            likes = itemView.findViewById(R.id.tv_sz_gz_like);
            comments = itemView.findViewById(R.id.tv_sz_gz_comment);
            btnPinlun = itemView.findViewById(R.id.iv_sz_gz_comment);
            btLike = itemView.findViewById(R.id.gz_bt_heart);

            btLike.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(View view, boolean checked) {
                    Log.d("dianzan", "click2 " + checked);
                }
            });
        }
    }
}

