package com.example.android.medialecte;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.medialecte.provider.DialectContract.DialectEntry;

public class AddWordActivity extends AppCompatActivity implements
        View.OnClickListener {


    private Context mContext;

    private EditText mEtFrenchWord;
    private EditText mEtArabicWord;

    private Button mBtnAddWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        mContext = AddWordActivity.this;

        mEtArabicWord = (EditText) findViewById(R.id.et_arabic_word);
        mEtFrenchWord = (EditText) findViewById(R.id.et_french_word);

        mBtnAddWord = (Button) findViewById(R.id.btn_add_word);
        mBtnAddWord.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_word:
                addRow();
                break;
        }
    }

    private void addRow() {
        String frenchWord = mEtFrenchWord.getText().toString();
        String arabicWord = mEtArabicWord.getText().toString();

        // check for invalid word
        if (frenchWord.isEmpty() || arabicWord.isEmpty()) {
            Toast.makeText(mContext, getString(R.string.two_words_constraint), Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(DialectEntry.COLUMN_ARABIC_WORD, arabicWord);
        values.put(DialectEntry.COLUMN_FRENCH_WORD, frenchWord);

        Uri uri = getContentResolver().insert(DialectEntry.CONTENT_URI, values);
        if (uri != null) {
            Toast.makeText(mContext, getString(R.string.done_adding_toast), Toast.LENGTH_LONG).show();
        }

        // clear the edit texts
        mEtFrenchWord.setText("");
        mEtArabicWord.setText("");
    }
}
