package com.lesson.spaceminer.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson.spaceminer.R;
import com.lesson.spaceminer.model.SpaceObj;

import java.text.DecimalFormat;
import java.util.List;

public class SpaceListAdapter extends  RecyclerView.Adapter<SpaceListAdapter.ViewHolder> {

    private final Context context;
    private List<SpaceObj> methodList;
    private OnItemClickListener listener;
    private DecimalFormat df;

    public SpaceListAdapter(Context context, List<SpaceObj> methodList, OnItemClickListener listener) {
        this.context = context;
        this.methodList = methodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_space, parent, false);
        ViewHolder holder = new ViewHolder(view);
        df = new DecimalFormat("#,##0.00");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //String oriPrice = methodList.get(position).getSellPrice();
        //String imgUrl = methodList.get(position).getImg();
//        if(imgUrl != null && imgUrl.length() > 0) {
//            Glide.with(context)
//                    .load(imgUrl.replace("\\", "/"))
//                    .placeholder(R.drawable.logo)
//                    .error(R.drawable.logo)
//                    .into(holder.ivMethod);
//        }

//        Glide.with(context)
//        .load(methodList.get(position).getIvMethod())
//        .placeholder(R.drawable.ic_person)
//        .error(R.drawable.ic_menu)
//        .into(holder.ivMethod);

        //holder.ivMethod.setImageResource(methodList.get(position).getId());

        holder.tvShortDet.setText(methodList.get(holder.getAdapterPosition()).getId());

        holder.rlMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition(), 2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return methodList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, int type);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final RelativeLayout rlMethod;
        final ImageView ivSpace, ivAddFav;
        TextView tvShortDet;

        ViewHolder(View itemView) {
            super(itemView);
            rlMethod = itemView.findViewById(R.id.rl_item);
            ivSpace = itemView.findViewById(R.id.iv_space);
            ivAddFav = itemView.findViewById(R.id.iv_addFav);
            tvShortDet = itemView.findViewById(R.id.tv_area);

        }
    }

}
