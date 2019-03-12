package com.yyp.demo.main.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.yyp.demo.R;
import com.yyp.demo.main.MainTitleManager;
import com.yyp.demo.main.adapter.MainAdapter;
import com.yyp.demo.main.bean.MainItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    //存储需要有标题栏的position-title
    private LinkedHashMap<Integer, String> mHeaderList=new LinkedHashMap<>();
    private MainAdapter mAdapter;
    private ArrayList<MainItem> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mList= (ArrayList<MainItem>) MainTitleManager.getMaintitleTestData();
        setHeaderList();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.share_add_contact_recyclerview);
        mRecyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(
                new FloatingBarItemDecoration(this, mHeaderList));

        mAdapter = new MainAdapter(mList, LayoutInflater.from(this));
        mAdapter.setRecyItemClickListener(new MainAdapter.OnRecyItemClickListener() {
            @Override
            public void onRecyClick(MainItem item) {
                Toast.makeText(MainActivity.this, item.title, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setHeaderList() {
        mHeaderList.clear();
        if (null == mList || mList.size() == 0) {
            return;
        }

        mHeaderList.put(0, mList.get(0).group);
        String lastGroup = mList.get(0).group;
        if (mList.size() > 1) {
            for (int i = 1; i < mList.size(); i++) {
                String curGroup = mList.get(i).group;
                if (!lastGroup.equals(curGroup)) {
                    mHeaderList.put(i, mList.get(i).group);
                    lastGroup=curGroup;
                }
            }
        }

    }
}
