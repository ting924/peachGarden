package com.greenhi.peach_garden.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.CommentActivity;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.greenhi.peach_garden.item.ItemDynamic;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RecyclerAdapterSZ extends RecyclerView.Adapter<RecyclerAdapterSZ.ViewHolder> {

    private List<ItemDynamic> szList;
    private Context context;
    private int id;

    public RecyclerAdapterSZ(Context context,List<ItemDynamic> szList,int id) {
        this.context=context;
        this.szList = szList;
        this.id=id;
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
        String url = "http://47.108.176.163:7777/img_user_head/"+id+".png";
        ItemDynamic data = szList.get(position);
        holder.comments.setText(""+data.getCommentNumber());
        holder.likes.setText(""+data.getLoveNumber());
        holder.text.setText(data.getDynamicContent());
        if(data.getCreateTime()!=null){
            holder.time.setText(data.getCreateTime().substring(0,10)+" "+data.getCreateTime().substring(11,19));
        }
        holder.username.setText(data.getUserName());

        holder.head.setImageResource(R.drawable.default_circle_head);
        Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(holder.head);
        holder.pinlun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CommentActivity.class);
                intent.putExtra("dynamicId",data.getId());
                context.startActivity(intent);
            }
        });
        holder.btLike.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                int position= holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/dynamic/addLikeById?id="+szList.get(position).getId()+"&add="+checked;
                AsyncHttpClient client=new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try{
                            if(checked){
                                szList.get(position).setLoveNumber(szList.get(position).getLoveNumber()+1);
                            }else {
                                szList.get(position).setLoveNumber(szList.get(position).getLoveNumber()-1);
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
        return szList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, time, likes, comments;
        ExpandableTextView text;
        ImageView head,pinlun;
        ShineButton btLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.tv_sz_time);
            this.username = itemView.findViewById(R.id.tv_sz_name);
            this.head = itemView.findViewById(R.id.iv_sz_head);
            this.text = itemView.findViewById(R.id.tv_sz_expand);
            this.likes = itemView.findViewById(R.id.tv_sz_like);
            this.comments = itemView.findViewById(R.id.tv_sz_comment);
            this.pinlun=itemView.findViewById(R.id.iv_sz_comment);
            this.btLike=itemView.findViewById(R.id.wode_bt_heart);
        }

    }
}
