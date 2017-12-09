package com.example.android.medialecte.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.medialecte.R;
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
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ConstraintLayout constraintLayout = (ConstraintLayout) layoutInflater.inflate(R.layout.list_item, null);

        TextView mTvFrencWord = (TextView) constraintLayout.findViewById(R.id.tv_lv_french_word);
        TextView mTvArabicWord = (TextView) constraintLayout.findViewById(R.id.tv_lv_arabic_word);

        mTvFrencWord.setText(mData.get(i).getFrenchWord());
        mTvArabicWord.setText(mData.get(i).getArabicWord());

        return constraintLayout;
    }
}
