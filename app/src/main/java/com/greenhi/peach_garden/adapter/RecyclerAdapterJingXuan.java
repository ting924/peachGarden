package com.greenhi.peach_garden.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.item.ItemDataSZ;
import com.greenhi.peach_garden.item.RecordsDTO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class RecyclerAdapterJingXuan extends RecyclerView.Adapter<RecyclerAdapterJingXuan.ViewHolder>{
    private List<RecordsDTO> jxList;
    private int id;

    public RecyclerAdapterJingXuan(List<RecordsDTO> jxList,int id) {
        this.jxList = jxList;
        this.id=id;
    }

    public void addAllData(List<RecordsDTO> jxListUpdate){
        if(jxListUpdate!=null&&jxListUpdate.size()>0){
            jxList.addAll(jxListUpdate);
            notifyItemRangeChanged(jxList.size()-jxListUpdate.size(),jxListUpdate.size());
        }

    }

    @NonNull
    @Override
    public RecyclerAdapterJingXuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shizhai_jingxuan,parent,false);
        RecyclerAdapterJingXuan.ViewHolder viewHolder = new RecyclerAdapterJingXuan.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterJingXuan.ViewHolder holder, int position) {
        RecordsDTO data = jxList.get(position);
        holder.comments.setText(""+data.getCommentNumber());
        holder.likes.setText(""+data.getLoveNumber());
        holder.text.setText(data.getDynamicContent());
        if(data.getCreateTime()!=null){
            holder.time.setText(data.getCreateTime().substring(0,10)+" "+data.getCreateTime().substring(11,19));
        }
        holder.head.setImageResource(R.drawable.default_circle_head);
        holder.btLike.setOnCheckStateChangeListener(new ShineButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View view, boolean checked) {
                int position= holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/dynamic/addLikeById?id="+jxList.get(position).getId()+"&add="+checked;
                AsyncHttpClient client=new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try{
                            if(checked){
                                jxList.get(position).setLoveNumber(jxList.get(position).getLoveNumber()+1);
                            }else {
                                jxList.get(position).setLoveNumber(jxList.get(position).getLoveNumber()-1);
                            }
                            notifyItemChanged(position,0);
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
        holder.btnGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/focus/add?uid="+id+"&fid="+jxList.get(position).getUid();
                AsyncHttpClient client=new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try{
                            //点击关注按钮，变为已关注按钮
                            holder.btnGuanzhu.setImageResource(R.drawable.ic_yiguanzhu);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("error","关注失败");

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
        private ImageView head,btnPinlun,btnGuanzhu;;
         private ShineButton btLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_sz_jx_time);
            username = itemView.findViewById(R.id.tv_sz_jx_name);
            head = itemView.findViewById(R.id.iv_sz_jx_head);
            text = itemView.findViewById(R.id.tv_sz_jx_expand);
            likes = itemView.findViewById(R.id.tv_sz_jx_like);
            comments = itemView.findViewById(R.id.tv_sz_jx_comment);
            btLike=itemView.findViewById(R.id.jx_bt_heart);
            btnPinlun=itemView.findViewById(R.id.iv_sz_jx_comment);
            btnGuanzhu=itemView.findViewById(R.id.iv_sz_jx_guanzhu);
        }
    }
}
