package com.example.android.medialecte;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.medialecte.adapter.WordsListAdapter;
import com.example.android.medialecte.data.ARow;
import com.example.android.medialecte.data.FetchData;
import com.example.android.medialecte.provider.DialectContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements
        View.OnClickListener,
        TextWatcher,
        FetchData.HandleFetchingData {


    private static final String TAG = HomeActivity.class.getSimpleName();

    private Context mContext;

    private EditText mEtSearch;

    private FloatingActionButton mFabAddWord;

    private ListView mLvWordsList;
    private WordsListAdapter mWordsListAdapter;

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


        // set Adapter
        mWordsListAdapter = new WordsListAdapter(mContext, new ArrayList<ARow>());
        mLvWordsList.setAdapter(mWordsListAdapter);
        mLvWordsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int deleted = getContentResolver().delete(
                        ContentUris.withAppendedId(DialectContract.DialectEntry.CONTENT_URI, l),
                        null,
                        null);

                if (deleted > 0) {
                    Toast.makeText(mContext, "Supprimé", Toast.LENGTH_LONG).show();
                    onResume();
                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mEtSearch.getText().toString().isEmpty())
            new FetchData(mContext, this).execute();
        else
            new FetchData(mContext, this).execute(mEtSearch.getText().toString().toLowerCase(Locale.getDefault()));

        findViewById(R.id.cl_home_activity).requestFocus();

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
        new FetchData(mContext, this).execute(currentText);
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


    @Override
    public void finishFetchingData(List<ARow> data) {

        // check if we have data in for the list
        // hide and display accordingly
        boolean doHide = true;
        if (data.size() > 0)
            doHide = false;

        hideListView(doHide);

        // you can optimize here by only resetting data if we are still displaying
        // the list
        mWordsListAdapter.setData(data);
    }
}
