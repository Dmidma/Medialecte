package com.example.android.medialecte;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements
        View.OnClickListener,
        TextWatcher {


    private static final String TAG = HomeActivity.class.getSimpleName();

    private Context mContext;

    private EditText mEtSearch;

    private FloatingActionButton mFabAddWord;

    private ListView mLvWordsList;

    private TextView mTvNothingFound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mContext = HomeActivity.this;

        mEtSearch = (EditText) findViewById(R.id.et_search_field);
        mEtSearch.addTextChangedListener(this);

        mFabAddWord = (FloatingActionButton) findViewById(R.id.fab_add_word);
        mFabAddWord.setOnClickListener(this);

        mLvWordsList = (ListView) findViewById(R.id.lv_words);

        mTvNothingFound = (TextView) findViewById(R.id.tv_nothing_found);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_word:
                goAddWordActivity();
                break;
        }
    }


    private void goAddWordActivity() {
        startActivity(new Intent(mContext, AddWordActivity.class));
    }



    /**
     * To Handle Active Search
     */


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String currentText = mEtSearch.getText().toString().toLowerCase(Locale.getDefault());
    }


    // method to hide listview and display message or vice versa
    private void hideListView(boolean doHide) {
        if (doHide) {
            mLvWordsList.setVisibility(View.GONE);
            mTvNothingFound.setVisibility(View.VISIBLE);
        } {
            mLvWordsList.setVisibility(View.VISIBLE);
            mTvNothingFound.setVisibility(View.GONE);
        }
    }


}
