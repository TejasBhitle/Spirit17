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
            new Event("Rink Football","Inter" ,"Outdoor", "Volleyball court", "7am to 9am\n5pm onwards", "\u20B9120 per team\nTeam of 4+1(rolling sub)", "Prizes worth \u20B910000!", "Ameya","9920666361", "Rishi","9967009530", "#EEE618"),
            new Event("Table Tennis","Inter" ,"Indoor", "Gymkhana", "5pm onwards", "\u20B950 per person", "\u2022 1st Place: \u20B9300\n\u2022 2nd Place: \u20B9200", "Gagan Holani", "9405468028", "", "", "#29A4D5"),
            new Event("Carrom","Inter" ,"Indoor", "Gymkhana", "9am to 1pm\n2pm to 5pm", "\u20B930 for Singles\n\u20B950 for a team of 2", "Team:\n\u2022 1st Place: \u20B250\n\u2022 2nd Place: \u20B9150\n\nIndividual:\n\u2022 1st Place: \u20B9200\n\u2022 2nd Place: \u20B9100", "Jeet Mehta", "9920896046", "", "", "#08C55A"),

            /*Delete this Later*/
            new Event("Test Event ","Intra","Indoor", "Gymkhana", "9am to 1pm\n2pm to 5pm", "\u20B930 for Singles\n\u20B950 for a team of 2", "Team:\n\u2022 1st Place: \u20B250\n\u2022 2nd Place: \u20B9150\n\nIndividual:\n\u2022 1st Place: \u20B9200\n\u2022 2nd Place: \u20B9100", "Jeet Mehta", "9920896046", "", "", "#08C55A")
            /* new Event("Laser Tag", "An action-packed game where participants wear electronic vests and tag each other with phasors to score points.", "Mega", "Room 202 (ED Lab)", "9am to 1pm\n2pm to 6pm", "\u20B9100 per person (pre-registration)\n\u20B9120 per person (on the spot registration)", "\u2022 1st Place: \u20B91000\n\u2022 2nd Place: \u20B9500", "Gurpreet Kaur Saimy", "9029553799", "Madhura Gore", "7208450172", "#F6FF00"),
    */};

    private static final int[] images = {
            R.drawable.viewpager_1,
            R.drawable.viewpager_1,
            R.drawable.viewpager_1,
            R.drawable.viewpager_1
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
