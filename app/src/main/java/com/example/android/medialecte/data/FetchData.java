package com.example.android.medialecte.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.android.medialecte.provider.DialectContract.DialectEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmidma on 12/9/17.
 */

public class FetchData extends AsyncTask<String, Void, Cursor> {


    private HandleFetchingData mHandleFetchingData;
    private Context mContext;

    public FetchData(Context context, HandleFetchingData handleFetchingData) {
        mHandleFetchingData = handleFetchingData;
        mContext = context;

    }


    @Override
    protected Cursor doInBackground(String... strings) {
        ContentResolver resolver = mContext.getContentResolver();
        Cursor retCursor;

        int stringLength = strings.length;
        if (stringLength > 0 && !strings[0].isEmpty()) {
            String searchString = "%" + strings[0] + "%";
            String selection = DialectEntry.COLUMN_FRENCH_WORD + " LIKE ?";
            retCursor = resolver.query(
                    DialectEntry.CONTENT_URI,
                    null,
                    selection,
                    new String[] {searchString},
                    null);
        } else {
            retCursor = resolver.query(
                    DialectEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        }
        return retCursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

        ArrayList<ARow> data = new ArrayList<ARow>();

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() >= 1) {
                try {
                    int indexId = cursor.getColumnIndex(DialectEntry._ID);
                    int indexFrenchWord = cursor.getColumnIndex(DialectEntry.COLUMN_FRENCH_WORD);
                    int indexArabicWord = cursor.getColumnIndex(DialectEntry.COLUMN_ARABIC_WORD);
                    do {
                        data.add(new ARow(
                                cursor.getInt(indexId),
                                cursor.getString(indexFrenchWord),
                                cursor.getString(indexArabicWord)));
                    } while (cursor.moveToNext());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cursor.close();
                }
            }
        }



        mHandleFetchingData.finishFetchingData(data);
    }

    public interface HandleFetchingData {
        void finishFetchingData(List<ARow> data);
    }
}
