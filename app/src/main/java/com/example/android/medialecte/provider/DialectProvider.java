package com.example.android.medialecte.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.android.medialecte.provider.DialectContract.DialectEntry;
/**
 * Created by dmidma on 12/8/17.
 */

public class DialectProvider extends ContentProvider {

    // get instance of db helper
    private DialectDbHelper mDialectDbHelper;

    // define codes to match with URI
    public static final int CODE_DIALECT = 100;

    // set URI matcher
    private static final UriMatcher sUriMatcher = buildUriMatcher();


    @Override
    public boolean onCreate() {
        Context context = getContext();
        mDialectDbHelper = new DialectDbHelper(context);
        return true;
    }

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DialectContract.AUTHORITY, DialectContract.PATH_DIALECT, CODE_DIALECT);

        return uriMatcher;
    }


    @Override
    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder) {

        final SQLiteDatabase db = mDialectDbHelper.getReadableDatabase();

        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CODE_DIALECT:
                retCursor = db.query(DialectEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return retCursor;
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mDialectDbHelper.getWritableDatabase();

        Uri retUri;

        switch (sUriMatcher.match(uri)) {
            case CODE_DIALECT:
                long id = db.insert(DialectEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    retUri = ContentUris.withAppendedId(DialectEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return retUri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw new RuntimeException("This method is not implemented yet");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        throw new RuntimeException("This method is not implemented yet");
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("This method is not implemented yet");
    }
}
