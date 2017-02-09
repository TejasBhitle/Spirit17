package com.spit.spirit17.HelperClasses;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spit.spirit17.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 13/01/2017.
 */

public class SpiritContentProvider extends ContentProvider {

    //////////Database strings//////////
    private static final String LOG ="SpiritContentProvider";
    private static final String DB_NAME = "SpiritDB.sqlite";
    private static final int DB_VERSION = 1;

    //////////Strings to help create the events table//////////
    private static final String TABLE_EVENTS = "events";

    private static final String COL_EVENT_ID = "_id";
    private static final String COL_EVENT_NAME = "name";
    private static final String COL_EVENT_TYPE = "type";
    private static final String COL_EVENT_CATEGORY = "category";
    private static final String COL_EVENT_VENUE = "venue";
    private static final String COL_EVENT_TIME = "time";
    private static final String COL_EVENT_REGISTRATION = "registration";
    private static final String COL_EVENT_PRIZES = "prizes";
    private static final String COL_EVENT_CONTACT1_NAME = "contact1_name";
    private static final String COL_EVENT_CONTACT1_NO = "contact1_no";
    private static final String COL_EVENT_CONTACT2_NAME = "contact2_name";
    private static final String COL_EVENT_CONTACT2_NO = "contact2_no";
    private static final String COL_EVENT_FAVORITE = "favorite";
    private static final String COL_EVENT_REMINDER = "reminder";
    private static final String COL_COLOR="color";

    private static final String CREATE_TABLE_EVENTS_QUERY = String.format
            ("CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT NOT NULL ,%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL, %s INTEGER NOT NULL, %s TEXT NOT NULL);",
                    TABLE_EVENTS, COL_EVENT_ID, COL_EVENT_NAME,COL_EVENT_TYPE ,COL_EVENT_CATEGORY, COL_EVENT_VENUE, COL_EVENT_TIME, COL_EVENT_REGISTRATION, COL_EVENT_PRIZES, COL_EVENT_CONTACT1_NAME, COL_EVENT_CONTACT1_NO, COL_EVENT_CONTACT2_NAME, COL_EVENT_CONTACT2_NO, COL_EVENT_FAVORITE, COL_EVENT_REMINDER,COL_COLOR);

    //////////Data of each event//////////
    private final Event[] events = new Event[]{
            new Event("Cricket ",
                    "Inter",
                    "Outdoor",
                    "Rs 4000/-", "11+4",
                    "regiration link",
                    "15-16,19-24 Feb 2017\n Bhavans Ground",
                    "Prajwal", "9405980778",
                    "Hansraj", "8806828259",
                    "#08C55A"),

            new Event("Football ",
                    "Inter",
                    "Outdoor",
                    "\u20B92000/- per team", "11+5",
                    "https://docs.google.com/forms/d/e/1FAIpQLSfrIiYcld7Ac2G5QhbOqRCgRDb-4zkzdQBs2Pk-VVpkcGTyAA/viewform?c=0&w=1",
                    "17,18,20th Feb'17\n Bhavans Ground",
                    "Parth","8767326335", "Shubham","9619332150",
                    "#08C55A"),

            new Event("Volleyball ",
                    "Inter",
                    "Outdoor",
                    "\u20B9900/- per team", "6+6",
                    "https://docs.google.com/forms/d/e/1FAIpQLScIXkjdkwXhXdQP6hktwFIajAflEvNo85nxihoDhCjHvNp5bA/viewform?c=0&w=1",
                    "8-19th Feb'17\n Volleyball court",
                    "Atul","8793552829", "Abhishek","8275107343",
                    "#08C55A"),

            new Event("Throwball ",
                    "Inter",
                    "Outdoor",
                    "\u20B9700/- per team", "7+2",
                    "https://docs.google.com/forms/d/e/1FAIpQLScfAXfL5E15U7Gq2drqLCSzmgGTcvNQg2tumkgVqBDSRMJNjQ/viewform",
                    "19th Feb'17\n Volleyball court",
                    "Divita","998717333", "","",
                    "#08C55A"),

            new Event("Basketball ",
                    "Inter",
                    "Outdoor",
                    "\u20B9900/- per team", "5+7",
                    "https://docs.google.com/forms/d/e/1FAIpQLSf925aDR9o3WZWmBFezBeT_dnLXlsAMbjL32IgfM7EKVE62Dg/viewform",
                    "16th-18th Feb'17\n Basketball court",
                    "Shubham","9833351724", "","",
                    "#08C55A"),

            new Event("Chess ",
                    "Inter",
                    "Indoor",
                    "\u20B980/- per player", "1",
                    "https://docs.google.com/forms/d/e/1FAIpQLSfjV-G7OcFG6aToBK-AFP5T3586XCEEm0ybIzAfMZbieN1MPg/viewform",
                    "15th Feb'17\n Gymkhana",
                    "Amey","9930088105",
                    "","",
                    "#08C55A"),

            new Event("Carrom ",
                    "Inter",
                    "Indoor",
                    "\u20B950/-Singles\n100/- Doubles", "1 or 2",
                    "https://docs.google.com/forms/d/e/1FAIpQLSfLDM3kLh5Bf3i7C39p7blKw6YVY0-QvvVixbWoNQOUJuB-Sw/viewform",
                    "19th-20th Feb'17\n Gymkhana",
                    "Jeet","9920896046",
                    "Aashish","9619641945",
                    "#08C55A"),

            new Event("Table Tennis",
                    "Inter",
                    "Indoor",
                    "\u20B950/- Singles\n100/- Doubles", "1 or 2",
                    "https://docs.google.com/forms/d/e/1FAIpQLSewMC7ScnVPvU55gzlc2l03WuHkLfKN-RaGWs5ftFBw2KQCtw/viewform",
                    "19th-20th Feb'17\n Gymkhana",
                    "Gagan","9405468028",
                    "Nikhil","9158996129",
                    "#08C55A")

    };

    private static final int[] images = {
            R.drawable.cricket,
            R.drawable.football,
            R.drawable.volleyball,
            R.drawable.throwball,
            R.drawable.viewpager_1,
            R.drawable.chess,
            R.drawable.carrom,
            R.drawable.table_tennis
    };

    SQLiteDatabase db;
    SpiritDBConnectionHelper helper;

    @Override
    public boolean onCreate() {
        helper = new SpiritDBConnectionHelper(getContext());
        db = helper.getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(TABLE_EVENTS, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        if (db.insert(TABLE_EVENTS, null, values) == -1)
            throw new RuntimeException("Error while adding event");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return db.delete(TABLE_EVENTS, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return db.update(TABLE_EVENTS, values, selection, selectionArgs);
    }

    //////////Connection helper//////////
    public class SpiritDBConnectionHelper extends SQLiteOpenHelper {
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_EVENTS_QUERY);

            ContentValues cv = new ContentValues();
            cv.put(COL_EVENT_FAVORITE, 0);
            cv.put(COL_EVENT_REMINDER, 0);
            for (Event event : events) {
                cv.put(COL_EVENT_NAME, event.getName());
                cv.put(COL_EVENT_TYPE, event.getType());
                cv.put(COL_EVENT_CATEGORY, event.getCategory());
                cv.put(COL_EVENT_VENUE, event.getVenue());
                cv.put(COL_EVENT_TIME, event.getTime());
                cv.put(COL_EVENT_REGISTRATION, event.getRegistration());
                cv.put(COL_EVENT_PRIZES, event.getPrizes());
                cv.put(COL_EVENT_CONTACT1_NAME, event.getContact1_name());
                cv.put(COL_EVENT_CONTACT1_NO, event.getContact1_no());
                cv.put(COL_EVENT_CONTACT2_NAME, event.getContact2_name());
                cv.put(COL_EVENT_CONTACT2_NO, event.getContact2_no());
                cv.put(COL_COLOR,event.getColor());
                db.insert(TABLE_EVENTS, null, cv);
                Log.e(LOG,event.getName()+" added");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public SpiritDBConnectionHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        private Event getEventFromCursor(Cursor cursor){
            String name_event,type, category, venue, time, registration, prizes, contact_name1, contact_name2, contact_no1, contact_no2, color;
            int img, fav, reminder;

            int index0 = cursor.getColumnIndex(COL_EVENT_ID);
            int index1 = cursor.getColumnIndex(COL_EVENT_NAME);
            int index2 = cursor.getColumnIndex(COL_EVENT_TYPE);
            int index3 = cursor.getColumnIndex(COL_EVENT_CATEGORY);
            int index4 = cursor.getColumnIndex(COL_EVENT_VENUE);
            int index5 = cursor.getColumnIndex(COL_EVENT_TIME);
            int index6 = cursor.getColumnIndex(COL_EVENT_REGISTRATION);
            int index7 = cursor.getColumnIndex(COL_EVENT_PRIZES);
            int index8 = cursor.getColumnIndex(COL_EVENT_CONTACT1_NAME);
            int index9 = cursor.getColumnIndex(COL_EVENT_CONTACT1_NO);
            int index10 = cursor.getColumnIndex(COL_EVENT_CONTACT2_NAME);
            int index11 = cursor.getColumnIndex(COL_EVENT_CONTACT2_NO);
            int index12 = cursor.getColumnIndex(COL_EVENT_FAVORITE);
            int index13 = cursor.getColumnIndex(COL_EVENT_REMINDER);
            int index14 = cursor.getColumnIndex(COL_COLOR);

            name_event = cursor.getString(index1);
            type = cursor.getString(index2);
            category = cursor.getString(index3);
            img = images[cursor.getInt(index0)-1];
            venue = cursor.getString(index4);
            time = cursor.getString(index5);
            registration = cursor.getString(index6);
            prizes = cursor.getString(index7);
            contact_name1 = cursor.getString(index8);
            contact_no1 = cursor.getString(index9);
            contact_name2 = cursor.getString(index10);
            contact_no2 = cursor.getString(index11);
            fav = cursor.getInt(index12);
            reminder = cursor.getInt(index13);
            color = cursor.getString(index14);

            return new Event(name_event,type,category, img, venue, time, registration, prizes, contact_name1,
                    contact_no1, contact_name2, contact_no2, fav, reminder, color);
        }

        public ArrayList<Event> getEventListByType(String type,String category){
            ArrayList<Event> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String query= "SELECT * FROM "+TABLE_EVENTS+" WHERE "+COL_EVENT_TYPE+" = '"+type+"' AND "
                    +COL_EVENT_CATEGORY+" = '"+category+"'";
            Log.e(LOG,query);
            Cursor cursor = db.rawQuery(query,null);

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                arrayList.add(getEventFromCursor(cursor));
                cursor.moveToNext();
            }
            cursor.close();
            return arrayList;
        }

        public ArrayList<Event> getFavorites(){
            ArrayList<Event> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();

            String query ="SELECT * FROM "+TABLE_EVENTS+" WHERE "+COL_EVENT_FAVORITE+" = '1'";
            Log.e(LOG,query);
            Cursor cursor = db.rawQuery(query,null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                arrayList.add(getEventFromCursor(cursor));
                cursor.moveToNext();
            }
            cursor.close();
            return arrayList;
        }

    }

}
