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
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

        //加载动态图片
        Integer did = data.getId();
        Integer imgCount = data.getImgCount();
        if(imgCount>0){
            List<String> urls = new ArrayList<>();
            String baseUrl = "http://47.108.176.163:7777/img_dynamic/";
            for (int i = 1; i < imgCount + 1; i++) {
                urls.add(baseUrl + did + '_' + i + ".png");
            }
            System.out.println("urls-------------------urls: " + urls.toString());
            holder.nineGrid.setImagesData(urls);
            holder.nineGrid.setVisibility(View.GONE);
            holder.nineGrid.setVisibility(View.VISIBLE);
        }

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

        private NineGridImageView nineGrid;

        private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String url) {
             Picasso.with(context).load(url).placeholder(R.drawable.placeholder).into(imageView);
            }

            @Override
            protected ImageView generateImageView(Context context) {
             return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List<String> list) {
             super.onItemImageClick(context, imageView, index, list);
             //Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<String> list) {
             super.onItemImageLongClick(context, imageView, index, list);
             //Toast.makeText(context, "image long click position is " + index, Toast.LENGTH_SHORT).show();
             return true;
            }
        };

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

            this.nineGrid = itemView.findViewById(R.id.sz_gz_ninegrid);
            nineGrid.setAdapter(mAdapter);
        }
    }
}

