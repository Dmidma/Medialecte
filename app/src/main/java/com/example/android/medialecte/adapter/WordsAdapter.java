package com.example.android.medialecte.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.medialecte.R;
import com.example.android.medialecte.data.ARow;

import java.util.List;

/**
 * Created by dmidma on 12/10/17.
 */

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder> {

    private List<ARow> mData;

    public WordsAdapter(List<ARow> data) {
        mData = data;
    }



    public void resetData(List<ARow> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public WordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;
        View view = layoutInflater.inflate(R.layout.list_item, parent, shouldAttachToParentImmediately);

        WordsViewHolder viewHolder = new WordsViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(WordsViewHolder holder, int position) {

        holder.bind(mData.get(position));

    }

    public class WordsViewHolder extends RecyclerView.ViewHolder {


        private TextView mTvFrenchWord;
        private TextView mTvArabicWord;

        private ARow mARow;


        public WordsViewHolder(View itemView) {
            super(itemView);
            mTvArabicWord = (TextView) itemView.findViewById(R.id.tv_lv_arabic_word);
            mTvFrenchWord = (TextView) itemView.findViewById(R.id.tv_lv_french_word);
        }

        public void bind(ARow aRow) {
            mARow = aRow;
            mTvFrenchWord.setText(mARow.getFrenchWord());
            mTvArabicWord.setText(mARow.getArabicWord());
        }

    }

}
