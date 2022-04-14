package com.greenhi.peach_garden.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greenhi.peach_garden.R;
import com.greenhi.peach_garden.activity.MainActivity;
import com.greenhi.peach_garden.item.ItemDataFS;
import com.greenhi.peach_garden.item.ItemUser;
import com.greenhi.peach_garden.utils.UserMessage;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapterFS extends RecyclerView.Adapter<RecyclerAdapterFS.ViewHolder> {

    private List<ItemUser> fsList;
    private int id;
    private Context context;

    public RecyclerAdapterFS(Context context, List<ItemUser> fsList, int id) {
        this.context=context;
        this.fsList = fsList;
        this.id=id;
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
        ItemUser data = fsList.get(position);
        holder.username.setText(data.getUserName());
        holder.intro.setText(data.getBriefIntroduction());
        String url = "http://47.108.176.163:7777/img_user_head/"+data.getId()+".png";
        Picasso.with(context).load(url).placeholder(R.drawable.default_circle_head).into(holder.head);
        holder.guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                String url = "http://47.108.176.163:7777/focus/add?uid="+id+"&fid="+fsList.get(position).getId();
                AsyncHttpClient client=new AsyncHttpClient();
                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                        try{
                            //点击关注按钮，变为已关注按钮
                          holder.guanzhu.setImageResource(R.drawable.ic_yiguanzhu);
                            //刷新关注list
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
        return fsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView username,intro;
        ImageView guanzhu;
        CircleImageView head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.tv_fs_name);
            this.intro = itemView.findViewById(R.id.tv_fs_intro);
            this.head = itemView.findViewById(R.id.iv_fs_head);
            this.guanzhu=itemView.findViewById(R.id.fensi_guanzhu);
        }
    }
}
