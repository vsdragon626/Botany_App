package com.pocketbotanist.contentprovider;

import java.util.Arrays;
import java.util.HashSet;

import com.pocketbotanist.database.EntryDatabaseHelper;
import com.pocketbotanist.database.EntryTable;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyEntryContentProvider extends ContentProvider{

	// database
	private EntryDatabaseHelper database;

	// Used for the UriMacher
	private static final int ENTRIES = 10;
	private static final int ENTRY_ID = 20;

	private static final String AUTHORITY = "edu.drake.pocketbotanist.contentprovider";

	private static final String BASE_PATH = "entries";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/entries";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/entry";

	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, ENTRIES);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", ENTRY_ID);
	}

	@Override
	public boolean onCreate() {
		database = new EntryDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Using SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// Check if the caller has requested a column which does not exists
		checkColumns(projection);

		// Set the table
		queryBuilder.setTables(EntryTable.TABLE_ENTRY);

		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case ENTRIES:
			break;
		case ENTRY_ID:
			// Adding the ID to the original query
			queryBuilder.appendWhere(EntryTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		// Make sure that potential listeners are getting notified
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		@SuppressWarnings("unused")
		int rowsDeleted = 0;
		long id = 0;
		switch (uriType) {
		case ENTRIES:
			id = sqlDB.insert(EntryTable.TABLE_ENTRY, null, values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch (uriType) {
		case ENTRIES:
			rowsDeleted = sqlDB.delete(EntryTable.TABLE_ENTRY, selection,
					selectionArgs);
			break;
		case ENTRY_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = sqlDB.delete(EntryTable.TABLE_ENTRY,
						EntryTable.COLUMN_ID + "=" + id, 
						null);
			} else {
				rowsDeleted = sqlDB.delete(EntryTable.TABLE_ENTRY,
						EntryTable.COLUMN_ID + "=" + id 
						+ " and " + selection,
						selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int uriType = sURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;
		switch (uriType) {
		case ENTRIES:
			rowsUpdated = sqlDB.update(EntryTable.TABLE_ENTRY, 
					values, 
					selection,
					selectionArgs);
			break;
		case ENTRY_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = sqlDB.update(EntryTable.TABLE_ENTRY, 
						values,
						EntryTable.COLUMN_ID + "=" + id, 
						null);
			} else {
				rowsUpdated = sqlDB.update(EntryTable.TABLE_ENTRY, 
						values,
						EntryTable.COLUMN_ID + "=" + id 
						+ " and " 
						+ selection,
						selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

	private void checkColumns(String[] projection) {
		String[] available = { EntryTable.COLUMN_SPECIES,
				EntryTable.COLUMN_COMMONNAME, EntryTable.COLUMN_DATE,
				EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTOS,
				EntryTable.COLUMN_PHOTO, EntryTable.COLUMN_LONGITUDE,
				EntryTable.COLUMN_LATITUDE, EntryTable.COLUMN_PLANTNOTES,
				EntryTable.COLUMN_HABITATNOTES, EntryTable.COLUMN_EXTRANOTES1,
				EntryTable.COLUMN_EXTRANOTES2, EntryTable.COLUMN_EXTRANOTES3,
				EntryTable.COLUMN_EXTRANOTES4, EntryTable.COLUMN_EXTRANOTES5,
				EntryTable.COLUMN_CUSTOMID,	EntryTable.COLUMN_ID };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
			// Check if all columns which are requested are available
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException("Unknown columns in projection");
			}
		}
	}


}
