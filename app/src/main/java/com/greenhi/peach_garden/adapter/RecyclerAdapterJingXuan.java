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
import com.greenhi.peach_garden.item.RecordsDTO;
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

public class RecyclerAdapterJingXuan extends RecyclerView.Adapter<RecyclerAdapterJingXuan.ViewHolder> {
    private List<ItemDynamic> jxList;
    private int id;  //当前用户id
    private Context context;

    public RecyclerAdapterJingXuan(Context context,List<ItemDynamic> jxList, int id) {
        this.context = context;
        this.jxList = jxList;
        this.id = id;
    }

    public void addAllData(List<ItemDynamic> jxListUpdate) {
        if (jxListUpdate != null && jxListUpdate.size() > 0) {
            jxList.addAll(jxListUpdate);
            notifyItemRangeChanged(jxList.size() - jxListUpdate.size(), jxListUpdate.size());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterJingXuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shizhai_jingxuan, parent, false);
        RecyclerAdapterJingXuan.ViewHolder viewHolder = new RecyclerAdapterJingXuan.ViewHolder(itemView);
//        viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterJingXuan.ViewHolder holder, int position) {
        ItemDynamic data = jxList.get(position);
        //加载动态图片
        Integer did = data.getId();
        Integer imgCount = data.getImgCount();
        if(imgCount>0) {
            List<String> urls = new ArrayList<>();
            String baseUrl = "http://47.108.176.163:7777/img_dynamic/";
            for (int i = 1; i < imgCount + 1; i++) {
                urls.add(baseUrl + did + '_' + i + ".png");
            }
            System.out.println("urls-------------------urls: " + urls.toString());
            holder.nineGrid.setImagesData(urls);
//            holder.nineGrid.setVisibility(View.GONE);
//            holder.nineGrid.setVisibility(View.VISIBLE);
        }
        holder.username.setText(data.getUserName());
        holder.comments.setText("" + data.getCommentNumber());
        holder.likes.setText("" + data.getLoveNumber());
        holder.text.setText(data.getDynamicContent());
        if (data.getCreateTime() != null) {
            holder.time.setText(data.getCreateTime().substring(0, 10) + " " + data.getCreateTime().substring(11, 19));
        }
        String url = "http://47.108.176.163:7777/img_user_head/"+data.getUid()+".png";
        Picasso.with(context).load(url).placeholder(R.drawable.default_circle_head).into(holder.head);
        holder.btnPinlun.setOnClickListener(new View.OnClickListener() {
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
                int position = holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/dynamic/addLikeById?id=" + jxList.get(position).getId() + "&add=" + checked;
                AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try {
                            if (checked) {
                                jxList.get(position).setLoveNumber(jxList.get(position).getLoveNumber() + 1);
                            } else {
                                jxList.get(position).setLoveNumber(jxList.get(position).getLoveNumber() - 1);
                            }
                            notifyItemChanged(position, 0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("点赞+1", "失败");
                    }
                });
            }
        });
        holder.btnGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/focus/add?uid=" + RecyclerAdapterJingXuan.this.id + "&fid=" + jxList.get(position).getUid();
                AsyncHttpClient client = new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try {
                            //点击关注按钮，变为已关注按钮
                            holder.btnGuanzhu.setImageResource(R.drawable.ic_yiguanzhu);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("error", "关注失败");

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return jxList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username, time, likes, comments;
        private ExpandableTextView text;
        private ImageView head, btnPinlun, btnGuanzhu;
        NineGridImageView nineGrid;

        private ShineButton btLike;

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
            time = itemView.findViewById(R.id.tv_sz_jx_time);
            username = itemView.findViewById(R.id.tv_sz_jx_name);
            head = itemView.findViewById(R.id.iv_sz_jx_head);
            text = itemView.findViewById(R.id.tv_sz_jx_expand);
            likes = itemView.findViewById(R.id.tv_sz_jx_like);
            comments = itemView.findViewById(R.id.tv_sz_jx_comment);
            btLike = itemView.findViewById(R.id.jx_bt_heart);
            btnPinlun = itemView.findViewById(R.id.iv_sz_jx_comment);
            btnGuanzhu = itemView.findViewById(R.id.iv_sz_jx_guanzhu);
            this.nineGrid = itemView.findViewById(R.id.sz_jx_ninegrid);
            nineGrid.setAdapter(mAdapter);

        }
    }
}
