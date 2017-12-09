package com.example.android.medialecte.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.android.medialecte.data.ARow;

import java.util.List;

/**
 * Created by dmidma on 12/9/17.
 */

public class WordsListAdapter extends BaseAdapter {


    private Context mContext;
    private List<ARow> mData;


    public WordsListAdapter(Context context, List<ARow> data) {
        mContext = context;
        mData = data;
    }


    public void setData(List<ARow> data) {
        mData = data;
        notifyDataSetChanged();
    }



    @Override
    public long getItemId(int i) {
        return mData.get(i).getId();
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
