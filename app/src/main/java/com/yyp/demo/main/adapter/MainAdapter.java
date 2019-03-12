package com.yyp.demo.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyp.demo.R;
import com.yyp.demo.main.bean.MainItem;

import java.util.ArrayList;

/**
 * Created by peng on 2019/3/12.
 */

public class MainAdapter extends RecyclerView.Adapter {

    private ArrayList<MainItem> mList;

    private LayoutInflater mLayoutInflater;

    private OnRecyItemClickListener mRecyItemClickListener;

    public MainAdapter(ArrayList<MainItem> mList, LayoutInflater mLayoutInflater) {
        this.mList = mList;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mLayoutInflater.inflate(R.layout.item_main_title, parent, false);
        return new MainTitleViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainItem itemBean = getItem(position);
        if (null != itemBean && holder instanceof MainTitleViewHolder) {
            ((MainTitleViewHolder) holder).bindData(itemBean);
        }
    }

    private MainItem getItem(int position) {
        if (null != mList && mList.size() > position) {
            return mList.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return null != mList ? mList.size() : 0;
    }

    private class MainTitleViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;

        public MainTitleViewHolder(View itemView) {
            super(itemView);
            titleTV = (TextView) itemView.findViewById(R.id.tv_main_item_title);
        }

        public void bindData(final MainItem item) {
            if (null != item && !TextUtils.isEmpty(item.title)) {
                titleTV.setText(item.title);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mRecyItemClickListener) {
                        mRecyItemClickListener.onRecyClick(item);
                    }
                }
            });
        }
    }

    public void setRecyItemClickListener(OnRecyItemClickListener mRecyItemClickListener) {
        this.mRecyItemClickListener = mRecyItemClickListener;
    }

    public interface OnRecyItemClickListener {
        void onRecyClick(MainItem item);
    }
}
