package com.greenhi.peach_garden.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.greenhi.peach_garden.activity.CommentActivity;
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

public class RecyclerAdapterGuanZhu extends RecyclerView.Adapter<RecyclerAdapterGuanZhu.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ItemDynamic> gzList;

    // 接口回调
    private OnMyItemClickListener listener;
    private RecyclerView recyclerView;

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    // 当它连接到一个RecyclerView调用的方法
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }
    // 当它与RecyclerView解除连接调用的方法
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }


    public RecyclerAdapterGuanZhu(Context context, List<ItemDynamic> gzList) {
        this.context = context;
        this.gzList = gzList;
    }

    @NonNull
    @Override
    public RecyclerAdapterGuanZhu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shizhai_guanzhu, parent, false);
        itemView.setOnClickListener(this);
        RecyclerAdapterGuanZhu.ViewHolder viewHolder = new RecyclerAdapterGuanZhu.ViewHolder(itemView);
//        viewHolder.setIsRecyclable(true);
        return viewHolder;
    }

    // 上面 view.setOnClickListener(this);的点击实现方法
    @Override
    public void onClick(View view) {
        if (recyclerView != null && listener != null) {
            int position = recyclerView.getChildAdapterPosition(view);
            listener.onMyItemClick(recyclerView,view,position,gzList.get(position)); // 接口回调
        }

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
        }
        System.out.println("RecyclerAdapterGuanZhu onBindViewHolder");

        holder.comments.setText("" + data.getCommentNumber());
        holder.likes.setText("" + data.getLoveNumber());
        holder.text.setText(data.getDynamicContent());
        if(data.getCreateTime()!=null){
            holder.time.setText(data.getCreateTime().substring(0,10)+" "+data.getCreateTime().substring(11,19));
        }
        holder.username.setText(data.getUserName());
        holder.head.setImageResource(R.drawable.default_circle_head);

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
                            notifyItemChanged(position,0 );
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("neterror","点赞+1失败");
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
//        return gzList.size();
        return 2;
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
//            nineGrid.setFocusable(false);
//            nineGrid.setVisibility(View.GONE);
//            nineGrid.setVisibility(View.VISIBLE);
//            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
//                nineGrid.setVisibility(View.INVISIBLE);
//            } else {
//                nineGrid.setVisibility(View.GONE);
//            }
        }
    }

    // 接口回调
    public interface OnMyItemClickListener{
        void onMyItemClick(RecyclerView parent, View view, int position, ItemDynamic data);
    }

    // 删除数据
    public void remove(int position){
        gzList.remove(position);
        //notifyDataSetChanged();// 提醒list刷新，没有动画效果
        notifyItemRemoved(position); // 提醒item删除指定数据，这里有RecyclerView的动画效果
    }
    // 添加数据
    public void add(int position, ItemDynamic data){
        gzList.add(position, data);
        notifyItemInserted(position);
    }
    // 改变数据
    public void change(int position, ItemDynamic data){
        gzList.remove(position);
        gzList.add(position, data);
        notifyItemChanged(position);
    }

}

