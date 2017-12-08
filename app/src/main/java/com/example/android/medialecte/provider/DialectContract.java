package com.example.android.medialecte.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dmidma on 12/8/17.
 */

public class DialectContract {

    // content provider authority
    public static final String AUTHORITY = "com.example.android.medialecte";
    // base of all URIs
    public static Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);




    // path to the table
    public static final String PATH_DIALECT = "dialect";

    public static final class dialectEntry implements BaseColumns {

        // content uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DIALECT).build();


        // table name
        public static final String TABLE_NAME = "dialect";

        // columns
        public static final String COLUMN_FRENCH_WORD = "french_dialect";
        public static final String COLUMN_ARABIC_WORD = "arabic_dialect";
    }



}
